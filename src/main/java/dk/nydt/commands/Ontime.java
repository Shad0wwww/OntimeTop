package dk.nydt.commands;

import dk.nydt.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ontime implements CommandExecutor {

    TimeUtils TimeUtils = new TimeUtils();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length > 0) {
            Player player = Bukkit.getPlayer(args[0]);
            if (!player.hasPlayedBefore()) {
                p.sendMessage("spiller har ikke været inde før");
                return false;
            }
            int time = TimeUtils.getOntime(player);
            p.sendMessage("Spilleren " + player.getName() + " har " + time + " sekunder");
        } else {
            int time = TimeUtils.getOntime(p);
            p.sendMessage("Spilleren " + p.getName() + " har " + time + " sekunder");
        }
        return true;

    }
}
