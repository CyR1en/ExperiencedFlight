package us.cyrien.experiencedflight.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import us.cyrien.experiencedflight.ExperiencedFlight;
import us.cyrien.experiencedflight.Messenger;
import us.cyrien.mcutils.annotations.Command;
import us.cyrien.mcutils.annotations.Sender;
import us.cyrien.mcutils.logger.Logger;

public class ExpFlightCmnd {

    @Command(aliases = {"expflight", "xpflight", "xflight"}, usage = "/expflight <help|reload|blacklist>", desc = "show all ExperiencedFlight commands.")
    public void command(@Sender CommandSender sender, String args) {
        args = args.trim();
        Logger.warn(args);
        if (args.isEmpty() || args.equals("help")) {
            sendHelp(sender);
            return;
        } else if (args.equals("reload")) {
            if (sender.hasPermission("expflight.reload")) {
                ExperiencedFlight.getInstance().getExperiencedFlightConfigManager().reloadAllConfig();
                ExperiencedFlight.getInstance().getExperiencedFlightConfigManager().setupConfigurations();
                Messenger.sendMsg(sender, "Configuration have been reloaded.");
                return;
            } else {
                Messenger.sendErr(sender, "You don't have permission to reload ExperiencedFlight config.");
                return;
            }
        } else {
            Messenger.sendErr(sender, "/expflight command only have \"help\", \"reload\",and \"blacklist\" as an argument");
        }
    }

    private void sendHelp(CommandSender cs) {
        cs.sendMessage(ChatColor.GOLD + "===== " + ChatColor.AQUA + "ExperiencedFlight Commands" + ChatColor.GOLD + " =====");
        cs.sendMessage(ChatColor.GREEN + "/expfly - Fly using experience.");
        cs.sendMessage(ChatColor.GREEN + "/givelvl <player> <amount> - Give level to a player so they could fly.");
        cs.sendMessage(ChatColor.GREEN + "/expflight <help|reload> - General ExperiencedFlight Command");
        cs.sendMessage(ChatColor.GREEN + "/blacklist <add|remove> [world] - Add or remove worlds where players can't use ExperiencedFlight");
    }

}
