package dk.nydt.commands;

import dk.nydt.Main;
import dk.nydt.utils.Chat;
import dk.nydt.utils.FormatTime;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class OntimeTop implements CommandExecutor {

    HashMap<String, Integer> topSeconds = new HashMap<String,Integer>();

    FormatTime FormatTime = new FormatTime();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        String[] top = getTop5();
        int score1 = Main.ontimeYML.getInt("Accounts."+top[0]);
        int score2 = Main.ontimeYML.getInt("Accounts."+top[1]);
        int score3 = Main.ontimeYML.getInt("Accounts."+top[2]);
        int score4 = Main.ontimeYML.getInt("Accounts."+top[3]);
        int score5 = Main.ontimeYML.getInt("Accounts."+top[4]);
        p.sendMessage(Chat.colored("------[ Top 5 Gamers ]------"));
        if(!(top[0]==null)&&!(score1==0)) p.sendMessage("#1 " + Bukkit.getOfflinePlayer(UUID.fromString(top[0])).getName() + " \u00BB " + FormatTime.calculateTime(score1));
        if(!(top[1]==null)&&!(score2==0)) p.sendMessage("#2 " + Bukkit.getOfflinePlayer(UUID.fromString(top[1])).getName()  + " \u00BB " + FormatTime.calculateTime(score2));
        if(!(top[2]==null)&&!(score3==0)) p.sendMessage("#3 " + Bukkit.getOfflinePlayer(UUID.fromString(top[2])).getName()  + " \u00BB " + FormatTime.calculateTime(score3));
        if(!(top[3]==null)&&!(score4==0)) p.sendMessage("#4 " + Bukkit.getOfflinePlayer(UUID.fromString(top[3])).getName()  + " \u00BB " + FormatTime.calculateTime(score4));
        if(!(top[4]==null)&&!(score5==0)) p.sendMessage("#5 " + Bukkit.getOfflinePlayer(UUID.fromString(top[4])).getName()  + " \u00BB " + FormatTime.calculateTime(score5));
        p.sendMessage(Chat.colored("------[ ---------- ]------"));




        return true;
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


}
