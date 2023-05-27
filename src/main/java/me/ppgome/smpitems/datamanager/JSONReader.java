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
    private static List<String> basicitemlist;
    private static ArrayList<String> itemList = new ArrayList<>();

    public static void createBasicItems() {
        basicitems = new File(SMPItems.getPlugin().getDataFolder().getAbsolutePath() + "\\items", "basicitems.yml");

        basicyml = YamlConfiguration.loadConfiguration(basicitems);

        for(String s: basicyml.getConfigurationSection("basicitems").getKeys(false)) {
            //TODO: Figure out this array stuff. Idk how this works :(
            if(basicyml.get("basicitems." + s + ".attributes") != null) {
                System.out.println("NOT NULL!");
                System.out.println(basicyml.get("basicitems." + s + ".attributes"));
            }

            basicitemlist = basicyml.getStringList("basicitems." + s + ".attributes");
            ArrayList<String> test = new ArrayList<>(basicitemlist);
            System.out.println(test.size());

//            itemList.add(new BasicItem(getStringValue("basicitems." + s + ".name"), Integer.parseInt(Objects.
//                    requireNonNull(getStringValue("basicitems." + s + ".model"))), Material.matchMaterial(String.
//                    valueOf(getStringValue("basicitems." + s + ".material"))), getStringValue("basicitems." +
//                    s + ".lore"), ));
        }
    }

    private static String getStringValue(String path) {
        if(basicyml.get(path) == null) return null;
        return String.valueOf(basicyml.get(path));
    }

    private static @NotNull List<Map<?, ?>> getAttributes(String path) {
        if(basicyml.get(path) == null) return null;
        return basicyml.getMapList(path);
    }

}
