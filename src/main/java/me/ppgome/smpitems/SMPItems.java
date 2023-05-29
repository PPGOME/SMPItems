package me.ppgome.smpitems;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import me.ppgome.smpitems.commands.GetItem;
import me.ppgome.smpitems.datamanager.JSONReader;
import me.ppgome.smpitems.items.BasicBlock;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

/**
 * The main class for the plugin.
 * @author PPGOME
 * @version Pre-0.1
 */
public final class SMPItems extends JavaPlugin {

    private static SMPItems plugin;

    // Directory for storing item YML files
    private File itemsdir;
    private File basicitems;

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true));
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        CommandAPI.onEnable();

        System.out.println("Plugin loaded successfully. Checking/generating config files...");

        itemsdir = new File(getDataFolder(), "items");

        if(!itemsdir.exists()) {
            try {
                itemsdir.mkdirs();
            } catch(Exception e) {
                getLogger().log(Level.SEVERE, "Main items directory could not be created!");
            }
        }

        basicitems = new File(itemsdir, "basicitems.yml");
        if(!basicitems.exists()) {
            saveResource("items/basicitems.yml", false);
        }

        getServer().getPluginManager().registerEvents(new BasicBlock(), this);

        JSONReader.createBasicItems();

        GetItem.getItemCommand();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        CommandAPI.onDisable();
    }

    /**
     * Method for sending an instance of the plugin around.
     * @return an instance of the plugin.
     */
    public static SMPItems getPlugin() { return plugin; }

}
