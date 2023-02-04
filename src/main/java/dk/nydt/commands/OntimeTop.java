package dk.nydt.commands;

import dk.nydt.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class OntimeTop implements CommandExecutor {

    FileConfiguration ontimeYML = Main.ontimeYML;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        return false;
    }
}
