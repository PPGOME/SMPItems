package me.ppgome.smpitems.datamanager;

import me.ppgome.smpitems.SMPItems;
import me.ppgome.smpitems.items.BasicItem;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;

public class JSONReader {

    private static File basicitems;
    private static YamlConfiguration basicyml;
    private static HashMap<String, Integer> basicitemmap;
    private static HashMap<String, BasicItem> itemList = new HashMap<>();

    public static void createBasicItems() {
        basicitems = new File(SMPItems.getPlugin().getDataFolder().getAbsolutePath() + "\\items", "basicitems.yml");

        basicyml = YamlConfiguration.loadConfiguration(basicitems);

        for(String s: basicyml.getConfigurationSection("basicitems").getKeys(false)) {
            if(basicyml.get("basicitems." + s + ".attributes") != null) {

                basicitemmap = new HashMap<>();

                for(String attribute : basicyml.getConfigurationSection("basicitems." + s + ".attributes").getKeys(false)) {
                    basicitemmap.put(attribute, basicyml.getInt("basicitems." + s + ".attributes." + attribute));
                }

            }
            System.out.println(s);
            itemList.put(s, new BasicItem(getStringValue("basicitems." + s + ".name"), Integer.parseInt(Objects.
                    requireNonNull(getStringValue("basicitems." + s + ".model"))), Material.matchMaterial(String.
                    valueOf(getStringValue("basicitems." + s + ".material"))), getStringValue("basicitems." +
                    s + ".lore"), basicitemmap));
        }
    }

    private static String getStringValue(String path) {
        if(basicyml.get(path) == null) return null;
        return String.valueOf(basicyml.get(path));
    }

    public static HashMap<String, BasicItem> getBasicItemsList() {
        return itemList;
    }

}
