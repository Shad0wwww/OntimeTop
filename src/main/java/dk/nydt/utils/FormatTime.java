package dk.nydt.utils;

import dk.nydt.Main;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class FormatTime {

    public String calculateTime(int seconds) {


        int days = (int) Math.floor(seconds / 86400);
        int hours = (int) Math.floor((seconds - (days * 86400)) / 3600);
        int minutes = (int) Math.floor((seconds - ((days * 86400) + (hours * 3600))) / 60);
        int second = (int) Math.floor(seconds - ((days * 86400) + (hours * 3600) + (minutes * 60)));

        System.out.println(days + " days " + hours + " hours " + minutes + " minutes " + second + " seconds");


        String formatType = Main.configYML.getString(format+".score");
        formatType = formatType.replace("%d", String.valueOf(days));
        formatType = formatType.replace("%h", String.valueOf(hours));
        formatType = formatType.replace("%m", String.valueOf(minutes));
        formatType = formatType.replace("%s", String.valueOf(sec));
        return (formatType);
    }

}
