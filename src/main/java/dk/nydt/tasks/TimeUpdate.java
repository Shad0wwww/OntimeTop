package dk.nydt.tasks;

import dk.nydt.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import static dk.nydt.Main.ontime;

public class TimeUpdate implements Runnable {

    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            int time = p.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
            TimeUtils.setOntime(p, time);
        }
        ontime.saveConfig();
    }
}
