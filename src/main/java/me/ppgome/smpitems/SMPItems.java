package me.ppgome.smpitems;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SMPItems extends JavaPlugin {

    Plugin plugin = this;

    // Directory for storing item YML files
    private File itemsdir;

    // Config file for basic items that have no abilities (ingots, mob drops, etc.)
    private File basicitems;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        CommandAPI.onEnable();
        System.out.println("Plugin loaded successfully. Checking/generating config files...");

        itemsdir = new File("items");

        if(!itemsdir.exists()) {
            itemsdir.mkdirs();
        }

        basicitems = new File(itemsdir, "items");

        if(!basicitems.exists()) {
            plugin.saveResource("items", false);
        }




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        CommandAPI.onDisable();
    }

    public void generateConfigs(String filename) {

    }

}
