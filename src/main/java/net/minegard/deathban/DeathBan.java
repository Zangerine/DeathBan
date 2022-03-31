package net.minegard.deathban;

import net.minegard.deathban.events.PlayerDeathHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathBan extends JavaPlugin {

    private static DeathBan instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getLogger().info("Plugin has enabled.");
        Bukkit.getPluginManager().registerEvents(new PlayerDeathHandler(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin has disabled.");
    }

    public static DeathBan getInstance() { return instance; }
}
