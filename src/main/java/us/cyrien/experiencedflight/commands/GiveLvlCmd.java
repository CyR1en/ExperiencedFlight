package us.cyrien.experiencedflight.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.cyrien.experiencedflight.Messenger;
import us.cyrien.mcutils.annotations.Command;
import us.cyrien.mcutils.annotations.Permission;
import us.cyrien.mcutils.annotations.Sender;

public class GiveLvlCmd {
    @Command(aliases = "givelvl", usage = "/givelvl <player> <amount>", desc = "give lvl to someone")
    @Permission("expflight.givelvl")
    public void giveXP(@Sender CommandSender sender, String player, int amount) {
        if(player.isEmpty()) {
            Messenger.sendErr(sender,"Invalid arguments");
            return;
        }
        if(Bukkit.getPlayer(player) == null)  {
            Messenger.sendErr(sender, "Player " + player + " cannot be found.");
            return;
        }
        Player p = Bukkit.getPlayer(player);
        p.setLevel(p.getLevel() + amount);
        Messenger.sendMsg(p, "Given " + ChatColor.DARK_GREEN + amount + ChatColor.RESET + " levels to "  + ChatColor.DARK_AQUA + p.getName() + ChatColor.RESET);
    }
}
