package me.ppgome.smpitems.datamanager;

import me.ppgome.smpitems.SMPItems;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Configs {

    private SMPItems plugin;
    private FileConfiguration config;
    private File file;
    private File directory;

    public void createConfig(String name) {

        file = new File(plugin.getDataFolder(), name);
        directory = new File(plugin.getDataFolder(), "items");

        if(!directory.exists()) {
            directory.mkdir();
        }

    }

}
