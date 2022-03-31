package net.minegard.deathban.events;

import net.minegard.deathban.DeathBan;
import net.minegard.deathban.managers.Config;
import net.minegard.deathban.utils.LangUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import litebans.api.Database;

import java.util.UUID;
import java.util.logging.Level;

public class PlayerDeathHandler implements Listener {

    public PlayerDeathHandler() { }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        boolean debug = Config.getBool("console-log");

        Bukkit.getScheduler().runTaskAsynchronously(DeathBan.getInstance(), new Runnable() {
            public void run() {
                UUID uuid = p.getUniqueId();
                String ip;
                try {
                    ip = p.getAddress().getHostName();
                } catch (NullPointerException ex) {
                    ip = "127.0.0.1";
                }
                boolean isBanned = Database.get().isPlayerBanned(uuid, ip);
                if(isBanned) {
                    if(debug) DeathBan.getInstance().getLogger().info("Error! " + p.getName() + " appears to" +
                            "already be banned!");
                }
            }
        });



        String reason = LangUtils.col(Config.getString("ban-reason"));

        if(!(p.hasPermission("deathban.exempt"))) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " 18h " + reason);
        } else {
            if(debug) DeathBan.getInstance().getLogger().info(p.getName() + " is exempt from DeathBan.");
        }

    }
}
