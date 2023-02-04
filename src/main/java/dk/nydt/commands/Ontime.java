package dk.nydt.commands;

import dk.nydt.Main;
import dk.nydt.utils.Chat;
import dk.nydt.utils.FormatTime;
import dk.nydt.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Ontime implements CommandExecutor {

    TimeUtils TimeUtils = new TimeUtils();
    FormatTime FormatTime = new FormatTime();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        String message = Main.configYML.getString("ontime-messages.ontime");
        if (args.length == 1) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            if (!player.hasPlayedBefore()) {
                String messageError = Main.configYML.getString("ontime-messages.ontime-error");
                messageError = messageError.replaceAll("%player%", player.getName());
                p.sendMessage(Chat.colored(messageError));
                return false;
            }
            if (player.isOnline()) {
                // ARGUMENT 1 IS ONLINE AND YOU RECIVE THE ACCURATE TIME
                int time = player.getPlayer().getStatistic(Statistic.PLAY_ONE_TICK) / 20;
                message = message.replaceAll("%player%", player.getName());
                message = message.replaceAll("%score%", FormatTime.calculateTime(time));
                p.sendMessage(Chat.colored(message));
            } else {
                // ARGUMENT 1 IS OFFLINE AND YOU RECIVE THE LATEST TIME SAVED
                int time = TimeUtils.getOntime(player);
                message = message.replaceAll("%player%", player.getName());
                message = message.replaceAll("%score%", FormatTime.calculateTime(time));
                p.sendMessage(Chat.colored(message));
            }

        } else {
            // RECIVES YOUR OWN ACCURATE TIME
            int time = p.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
            message = message.replaceAll("%player%", p.getName());
            message = message.replaceAll("%score%", FormatTime.calculateTime(time));
            p.sendMessage(Chat.colored(message));
        }
        return true;

    }
}
