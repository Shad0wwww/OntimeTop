package dk.nydt.commands;

import dk.nydt.utils.FormatTime;
import dk.nydt.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ontime implements CommandExecutor {

    TimeUtils TimeUtils = new TimeUtils();
    FormatTime FormatTime = new FormatTime();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length > 0) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(Bukkit.getPlayer(args[0]).getUniqueId());
            if (!player.hasPlayedBefore()) {
                p.sendMessage("spiller har ikke været inde før");
                return false;
            }
            if (player.isOnline()) {
                // ARGUMENT 1 IS ONLINE AND YOU RECIVE THE ACCURATE TIME
                int time = player.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK) / 20;
                p.sendMessage("Spilleren " + player.getName() + " har " + FormatTime.calculateTime(time) + " sekunder");
            } else {
                // ARGUMENT 1 IS OFFLINE AND YOU RECIVE THE LATEST TIME SAVED
                int time = TimeUtils.getOntime(player);
                p.sendMessage("Spilleren " + player.getName() + " har " + FormatTime.calculateTime(time) + " sekunder");
            }
        } else {
            int time = p.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
            // RECIVES YOUR OWN ACCURATE TIME
            p.sendMessage("Spilleren " + p.getName() + " har " + FormatTime.calculateTime(time) + " sekunder");
        }
        return true;

    }
}
