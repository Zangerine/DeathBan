package net.minegard.deathban;

import net.minegard.deathban.commands.ReloadCommand;
import net.minegard.deathban.events.PlayerDeathHandler;
import net.minegard.deathban.events.PlayerLoginHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DeathBan extends JavaPlugin {

    private static DeathBan instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getLogger().info("Plugin has enabled.");
        Bukkit.getPluginManager().registerEvents(new PlayerDeathHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLoginHandler(), this);

        Objects.requireNonNull(getCommand("dbreload")).setExecutor(new ReloadCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin has disabled.");
    }

    public static DeathBan getInstance() { return instance; }
}
