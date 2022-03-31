package net.minegard.deathban.commands;

import net.minegard.deathban.managers.Config;
import net.minegard.deathban.utils.LangUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ReloadCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!(sender.hasPermission("deathban.reload"))) {
                p.sendMessage(LangUtils.col("&cYou do not have permission to use this command."));
            } else {
                try {
                    Config.reload();
                    sender.sendMessage("Config reloaded successfully.");
                } catch (IOException | InvalidConfigurationException exception) {
                    exception.printStackTrace();
                }
                return true;
            }
        } else {
            try {
                Config.reload();
                sender.sendMessage("Config reloaded successfully.");
            } catch (IOException | InvalidConfigurationException exception) {
                exception.printStackTrace();
            }
        }



        return false;
    }
}
