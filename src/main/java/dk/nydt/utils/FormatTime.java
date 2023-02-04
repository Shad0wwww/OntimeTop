package dk.nydt.utils;

import java.util.concurrent.TimeUnit;

public class FormatTime {
    public String calculateTime(long seconds) {
        long sec = seconds % 60;
        long minutes = seconds % 3600 / 60;
        long hours = seconds % 86400 / 3600;
        long days = seconds / 86400;
        return (days + " Dage " + hours + " Timer " + minutes + " Minutter " + sec + " Sekunder");
    }
}
