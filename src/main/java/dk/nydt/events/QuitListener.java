package dk.nydt.events;

import dk.nydt.utils.TimeUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.Statistic;

import static dk.nydt.Main.ontime;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();

        int time = p.getStatistic(Statistic.PLAY_ONE_TICK) / 20;
        TimeUtils.setOntime(p, time);

        ontime.saveConfig();

    }
}
