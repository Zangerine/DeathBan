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

        String reason = LangUtils.col(Config.getString("ban-reason"));
        String length = Config.getString("ban-length");

        if(!(p.hasPermission("deathban.exempt"))) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + p.getName() + " " + length + " " + reason);
        } else {
            if(debug) DeathBan.getInstance().getLogger().info(p.getName() + " is exempt from DeathBan.");
        }

    }
}
