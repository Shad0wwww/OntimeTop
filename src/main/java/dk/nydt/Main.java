package dk.nydt;

import dk.nydt.commands.Ontime;
import dk.nydt.commands.OntimeTop;
import dk.nydt.events.JoinListener;
import dk.nydt.events.QuitListener;
import dk.nydt.tasks.TimeUpdate;
import dk.nydt.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    public static Main instance;
    private static PluginManager pluginManager;
    public static Config ontime;
    public static FileConfiguration ontimeYML;
    @Override
    public void onEnable() {
        pluginManager = getServer().getPluginManager();
        instance = this;

        //register commands
        getCommand("Ontime").setExecutor(new Ontime());
        getCommand("OntimeTop").setExecutor(new OntimeTop());

        //register events
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);

        //on time yml
        if (!(new File(getDataFolder(), "ontime.yml")).exists())
            saveResource("ontime.yml", false);

        ontime = new Config(this, null, "ontime.yml");
        ontimeYML = ontime.getConfig();

        //updates on time every 10 minutes
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TimeUpdate(), 0L, 12000L);


    }

    @Override
    public void onDisable() {
        ontime.saveConfig();
    }

    public static Main getInstance(){
        return instance;
    }
}