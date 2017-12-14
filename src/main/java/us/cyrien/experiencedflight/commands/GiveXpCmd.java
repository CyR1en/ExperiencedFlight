package us.cyrien.experiencedflight.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.cyrien.experiencedflight.Messenger;
import us.cyrien.experiencedflight.handle.ExperienceManager;
import us.cyrien.mcutils.annotations.Command;
import us.cyrien.mcutils.annotations.Permission;
import us.cyrien.mcutils.annotations.Sender;

public class GiveXpCmd {
    @Command(aliases = "givexp", usage = "/givexp <player> <amount>", desc = "give xp to someone")
    @Permission("expflight.givexp")
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
        if((ExperienceManager.getExp(p) + amount) < 0) {
            p.setLevel(0);
            p.setExp(0.0f);
        } else {
            ExperienceManager.changeExp(p, amount);
        }
    }
}
