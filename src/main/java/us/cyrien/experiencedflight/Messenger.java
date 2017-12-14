package us.cyrien.experiencedflight;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Messenger {

    private static final String PREFIX =
            ChatColor.GOLD + "[" + ChatColor.AQUA + "ExperiencedFlight" + ChatColor.GOLD + "] " + ChatColor.RESET;

    public static void sendWarning(CommandSender p, String message) {
        message = ChatColor.GOLD + message;
        sendMsg(p, message);
    }

    public static void sendErr(CommandSender p, String message) {
        message = ChatColor.RED + message;
        sendMsg(p, message);
    }

    public static void sendMsg(CommandSender p, String message) {
        p.sendMessage(PREFIX + message);
    }

}
