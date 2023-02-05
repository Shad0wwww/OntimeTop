package dk.nydt.commands;

import dk.nydt.Main;
import dk.nydt.utils.Chat;
import dk.nydt.utils.FormatTime;
import dk.nydt.utils.GUI;
import dk.nydt.utils.GlassColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class OntimeTop implements CommandExecutor {

    HashMap<String, Integer> topSeconds = new HashMap<String,Integer>();

    FormatTime FormatTime = new FormatTime();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if (args.length == 0) {

            String[] top = getTop5();
            int score1 = Main.ontimeYML.getInt("Accounts." + top[0]);
            int score2 = Main.ontimeYML.getInt("Accounts." + top[1]);
            int score3 = Main.ontimeYML.getInt("Accounts." + top[2]);
            int score4 = Main.ontimeYML.getInt("Accounts." + top[3]);
            int score5 = Main.ontimeYML.getInt("Accounts." + top[4]);

            boolean chat = Main.configYML.getBoolean("Settings.Chat");
            boolean gui = Main.configYML.getBoolean("Settings.GUI");

            String arrow = Chat.colored(Main.configYML.getString("Settings.arrow"));
            String name = Chat.colored(Main.configYML.getString("Settings.name-color"));
            String top_line = Chat.colored(Main.configYML.getString("Chat.top-line"));
            String bottom_line = Chat.colored(Main.configYML.getString("Chat.bottom-line"));


            if (chat) {
                p.sendMessage(top_line);
                if (!(top[0] == null) && !(score1 == 0)) p.sendMessage(getPlacement(1) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[0])).getName() + " " + arrow + " " + FormatTime.calculateTime(score1));
                if (!(top[1] == null) && !(score2 == 0)) p.sendMessage(getPlacement(2) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[1])).getName() + " " + arrow + " " + FormatTime.calculateTime(score2));
                if (!(top[2] == null) && !(score3 == 0)) p.sendMessage(getPlacement(3) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[2])).getName() + " " + arrow + " " + FormatTime.calculateTime(score3));
                if (!(top[3] == null) && !(score4 == 0)) p.sendMessage(getPlacement(4) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[3])).getName() + " " + arrow + " " + FormatTime.calculateTime(score4));
                if (!(top[4] == null) && !(score5 == 0)) p.sendMessage(getPlacement(5) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[4])).getName() + " " + arrow + " " + FormatTime.calculateTime(score5));
                p.sendMessage(bottom_line);
            }

            if (gui) {
                String inv_name = Chat.colored(Main.configYML.getString("GUI.GUI-name"));
                String top_row = Main.configYML.getString("GUI.top-row");
                String bottom_row = Main.configYML.getString("GUI.bottom-row");
                String gui_head = Main.configYML.getString("GUI.GUI-head");
                String head_name = Chat.colored(Main.configYML.getString("GUI.head-name"));
                String loreHeader = Main.configYML.getString("GUI.lore-header");
                String loreFooter = Main.configYML.getString("GUI.lore-footer");

                Inventory inv = Bukkit.createInventory(null, 9 * 5, inv_name);
                for (int i = 0; i < 9; i++) {
                    inv.setItem(i, GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(top_row), "&7"));
                }

                for (int i = 36; i < 45; i++) {
                    inv.setItem(i, GUI.createItemGlass(Material.STAINED_GLASS_PANE, GlassColor.getGlassColor(bottom_row), "&7"));
                }

                ItemStack head = GUI.getSkull(gui_head);
                ItemMeta head_meta = head.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                head_meta.setDisplayName(head_name);

                String[] strings = loreHeader.split("%nl%");
                for (String line : strings) {
                    lore.add(Chat.colored(line));
                }

                if (!(top[0] == null) && !(score1 == 0)) lore.add(Chat.colored(getPlacement(1) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[0])).getName() + " " + arrow + " " + FormatTime.calculateTime(score1)));
                if (!(top[1] == null) && !(score2 == 0)) lore.add(Chat.colored(getPlacement(2) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[1])).getName() + " " + arrow + " " + FormatTime.calculateTime(score2)));
                if (!(top[2] == null) && !(score3 == 0)) lore.add(Chat.colored(getPlacement(3) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[2])).getName() + " " + arrow + " " + FormatTime.calculateTime(score3)));
                if (!(top[3] == null) && !(score4 == 0)) lore.add(Chat.colored(getPlacement(4) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[3])).getName() + " " + arrow + " " + FormatTime.calculateTime(score4)));
                if (!(top[4] == null) && !(score5 == 0)) lore.add(Chat.colored(getPlacement(5) + " " + name + Bukkit.getOfflinePlayer(UUID.fromString(top[4])).getName() + " " + arrow + " " + FormatTime.calculateTime(score5)));

                String[] strings2 = loreFooter.split("%nl%");
                for (String line : strings2) {
                    lore.add(Chat.colored(line));
                }

                head_meta.setLore(lore);
                head.setItemMeta(head_meta);
                inv.setItem(22, head);

                p.openInventory(inv);
            }

        } else if (args[0].equalsIgnoreCase("reload")) {
            String perm = Main.configYML.getString("Reload.Permission");
            if(!p.hasPermission(perm)) {
                p.sendMessage(Chat.colored("&cDette har du ikke adgang til"));
                return false;
            }

            boolean reloadSuccess;
            try {
                Main.config.reloadConfig();
                Main.configYML = Main.config.getConfig();
                reloadSuccess = true;
            } catch (Exception e) {
                e.printStackTrace();
                reloadSuccess = false;
            }
            if (reloadSuccess) {
                sender.sendMessage(Chat.colored("&aReload successfully completed"));
            } else {
                sender.sendMessage(Chat.colored("&cAn error occurred. Please check the console."));
            }
            return true;
        }
        return false;
    }

    public String countdown(HashMap<String, Integer> map){
        String top = null;
        int maxValueInMap=(Collections.max(topSeconds.values()));  // This will return max value in the Hashmap
        for (Map.Entry<String, Integer> entry : topSeconds.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {    // Print the key with max value
                top = entry.getKey();
            }
        }

        return top;
    }

    /**
     * Ontimetop.
     *
     * @return string value of top
     */

    public String[] getTop5(){

        for(String user: Main.ontimeYML.getConfigurationSection("Accounts").getKeys(true)) {
            if(!user.contains(".")){
                topSeconds.put(user, Main.ontimeYML.getInt("Accounts." + user));
            }
        }

        //Making sure there is enough keys to do this right
        int neededKeys = 6 - topSeconds.size();
        String space = " ";
        for (int i = 0; i < neededKeys; i++) {
            topSeconds.put(space, 0);
            space = " " + space;
        }


        String[] top = new String[5];
        for (int i = 0; i < 5; i++) {
            top[i] = countdown(topSeconds);
            topSeconds.remove(top[i]);
        }
        return top;
    }


    private String getPlacement(int i) {
        String placement = Main.configYML.getString("Settings.placement");
        placement = placement.replace("%placement%", String.valueOf(i));
        return Chat.colored(placement);
    }

}
