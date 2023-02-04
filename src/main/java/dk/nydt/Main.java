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
    public static Config ontime, config;
    public static FileConfiguration ontimeYML, configYML;
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

        //ontime yml
        if (!(new File(getDataFolder(), "ontime.yml")).exists())
            saveResource("ontime.yml", false);

        ontime = new Config(this, null, "ontime.yml");
        ontimeYML = ontime.getConfig();

        //config yml
        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = ontime.getConfig();

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
