package dk.nydt.events;

import dk.nydt.Main;
import dk.nydt.utils.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    Main plugin;
    public InventoryListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        String GuiName = Chat.colored(Main.configYML.getString("GUI.GUI-name"));
        if(e.getClickedInventory().getName().equals(GuiName)) {
            e.setCancelled(true);
        }
    }
}
