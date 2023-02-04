package dk.nydt.utils;

import dk.nydt.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeUtils {
    public static Map<UUID, Integer> ontime = new HashMap<>();
    static FileConfiguration ontimeYML = Main.ontimeYML;

    public int getOntime(OfflinePlayer p) {
        if (ontime.containsKey(p.getUniqueId())) {
            return (int) ontime.get(p.getUniqueId());
        }
        return 0;
    }

    public static void setOntime(OfflinePlayer p, int time) {
        ontimeYML.set("Accounts." + p.getUniqueId(), time);
        ontime.put(p.getUniqueId(), time);
    }
}
