package me.ppgome.smpitems.commands;

import dev.jorel.commandapi.CommandAPICommand;
import me.ppgome.smpitems.SMPItems;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.logging.Level;

public class ConfigCommands {

    public static void reloadServerCommand() {
        CommandAPICommand reload = new CommandAPICommand("reload")
                .executes((sender, args) -> {
                    File basicitems = new File(SMPItems.getPlugin().getDataFolder().getAbsolutePath() + "\\items", "basicitems.yml");
                    YamlConfiguration basicyml = YamlConfiguration.loadConfiguration(basicitems);

                    try {
                        basicyml.load(basicitems);
                    } catch(Exception e) {
                        Bukkit.getServer().getLogger().log(Level.SEVERE, "Could not reload configs!");
                    }
                });

        new CommandAPICommand("smpitems")
                .withSubcommand(reload)
                .register();

    }

}
