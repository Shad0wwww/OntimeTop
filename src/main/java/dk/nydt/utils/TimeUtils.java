package dk.nydt.utils;

import dk.nydt.Main;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeUtils {
    public static Map<UUID, Integer> ontime = new HashMap<>();

    public int getOntime(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        System.out.println(uuid);
        if (Main.ontimeYML.contains("Accounts." + uuid)) {
            return Main.ontimeYML.getInt("Accounts." + uuid);
        } else if (ontime.containsKey(uuid)) {
            return ontime.get(uuid);
        }
        return 0;
    }

    public static void setOntime(OfflinePlayer p, int time) {
        UUID uuid = p.getUniqueId();
        Main.ontimeYML.set("Accounts." + uuid, time);
        ontime.put(uuid, time);
    }

    public boolean hasAccount(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        return Main.ontimeYML.contains("Accounts." + uuid);
    }

    public void createAccount(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        Main.ontimeYML.set("Accounts." + uuid, 0);
        ontime.put(uuid, 0);
    }

    public void loadAccount(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        ontime.put(uuid, Main.ontimeYML.getInt("Accounts." + uuid));
    }

}
