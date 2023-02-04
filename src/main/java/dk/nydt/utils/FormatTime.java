package dk.nydt.utils;

import dk.nydt.Main;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class FormatTime {
    public String calculateTime(long seconds, String format) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds % 86400 / 3600;
        long days = seconds / 86400;

        String formatType = Main.configYML.getString(format+".score");
        formatType = formatType.replace("%d", String.valueOf(days));
        formatType = formatType.replace("%h", String.valueOf(hours));
        formatType = formatType.replace("%m", String.valueOf(minutes));
        formatType = formatType.replace("%s", String.valueOf(sec));
        return (formatType);
    }

}
