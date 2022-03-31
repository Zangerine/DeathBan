package net.minegard.deathban.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LangUtils {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String col(String msg) {
        if(Bukkit.getVersion().contains("1.17")) {
            Matcher match = pattern.matcher(msg);
            while(match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void msg(Player player, String msg) {
        if(Bukkit.getVersion().contains("1.17")) {
            Matcher match = pattern.matcher(msg);
            while(match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static List<String> col(List<String> lore) {
        List<String> cLore = new ArrayList<>();
        for(String s : lore) {
            cLore.add(col(s));
        }
        return cLore;
    }
}
