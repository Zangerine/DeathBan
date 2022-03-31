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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.UUID;
import java.util.logging.Level;

public class PlayerDeathHandler implements Listener {

    public PlayerDeathHandler() { }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        boolean debug = Config.getBool("console-log");

        String reason = LangUtils.col(Config.getString("ban-reason"));
        String length = Config.getString("ban-length");
        double banDelay = Config.getDouble("ban-delay") * 20L;

        if(!(p.hasPermission("deathban.exempt"))) {
            BukkitScheduler scheduler = DeathBan.getInstance().getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(DeathBan.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " " + length + " " + reason);
                }
            }, (int) banDelay);

        } else {
            if(debug) DeathBan.getInstance().getLogger().info(p.getName() + " is exempt from DeathBan.");
        }

    }
}
