package net.minegard.deathban.managers;

import net.minegard.deathban.DeathBan;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Config {

    private static CfgManager cfg = new CfgManager(DeathBan.getInstance(), "config.yml");

    public static void setString(String path, String value) {
        cfg.set(path, value);
        cfg.saveData();
    }
    public static void setInteger(String path, Integer value) {
        cfg.set(path, value);
        cfg.saveData();
    }
    public static void setBool(String path, Boolean value) {
        cfg.set(path, value);
        cfg.saveData();
    }

    public static void reload() throws IOException, InvalidConfigurationException {
        cfg = new CfgManager(DeathBan.getInstance(), "config.yml");
    }

    public static String getString(String path) { return cfg.getString(path); }
    public static Integer getInt(String path) { return cfg.getInt(path); }
    public static Boolean getBool(String path) { return cfg.getBoolean(path); }
}
