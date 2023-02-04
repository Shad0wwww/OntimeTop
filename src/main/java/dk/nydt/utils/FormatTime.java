package dk.nydt.utils;

import dk.nydt.Main;
import org.bukkit.Statistic;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class FormatTime {

    public String calculateTime(int seconds) {


        int days = (int) Math.floor(seconds / 86400);
        int hours = (int) Math.floor((seconds - (days * 86400)) / 3600);
        int minutes = (int) Math.floor((seconds - ((days * 86400) + (hours * 3600))) / 60);
        int second = (int) Math.floor(seconds - ((days * 86400) + (hours * 3600) + (minutes * 60)));

        String formatType = Main.configYML.getString("Settings.score");
        formatType = formatType.replaceAll("%d", String.valueOf(days));
        formatType = formatType.replaceAll("%h", String.valueOf(hours));
        formatType = formatType.replaceAll("%m", String.valueOf(minutes));
        formatType = formatType.replaceAll("%s", String.valueOf(second));
        return (Chat.colored(formatType));
    }

}
