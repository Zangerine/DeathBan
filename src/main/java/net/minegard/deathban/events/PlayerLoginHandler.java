package net.minegard.deathban.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLoginHandler implements Listener {

    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p.isDead()) {
            p.spigot().respawn();
        }
    }
}
