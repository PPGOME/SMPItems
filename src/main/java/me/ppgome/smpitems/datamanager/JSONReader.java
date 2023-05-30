package me.ppgome.smpitems.datamanager;

import me.ppgome.smpitems.SMPItems;
import me.ppgome.smpitems.items.BasicItem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JSONReader {

    private static File basicitems;
    private static YamlConfiguration basicyml;
    private static HashMap<String, BasicItem> itemList = new HashMap<>();

    private static String name;
    private static int model;
    private static Material material;
    private static String lore;
    private static HashMap<String, Double> basicitemmap;

    public static void createBasicItems() {
        basicitems = new File(SMPItems.getPlugin().getDataFolder().getAbsolutePath() + "\\items", "basicitems.yml");

        basicyml = YamlConfiguration.loadConfiguration(basicitems);

        for (String s : basicyml.getConfigurationSection("basicitems").getKeys(false)) {
            if (basicyml.get("basicitems." + s + ".attributes") != null) {

                basicitemmap = new HashMap<>();

                for (String attribute : basicyml.getConfigurationSection("basicitems." + s + ".attributes").getKeys(false)) {
                    basicitemmap.put(attribute, basicyml.getDouble("basicitems." + s + ".attributes." + attribute + ".level"));
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
        if (basicyml.get(path) == null) return null;
        return String.valueOf(basicyml.get(path));
    }

    public static HashMap<String, BasicItem> getBasicItemsList() {
        return itemList;
    }

    public static void setUUID(String name, String attribute, UUID uuid) {
        basicitems = new File(SMPItems.getPlugin().getDataFolder().getAbsolutePath() + "\\items", "basicitems.yml");
        basicyml = YamlConfiguration.loadConfiguration(basicitems);
        if(basicyml.get("basicitems." + name + ".attributes." + attribute + ".attributeid") == null) {
            basicyml.set("basicitems." + name + ".attributes." + attribute + ".attributeid", String.valueOf(uuid));
            try {
                basicyml.save(basicitems);
            } catch (IOException e) {
                System.out.println("Could not save UUID to file!");
            }
        }
    }

    public static String getUUID(String name, String attributename) {
        basicitems = new File(SMPItems.getPlugin().getDataFolder().getAbsolutePath() + "\\items", "basicitems.yml");
        basicyml = YamlConfiguration.loadConfiguration(basicitems);

        return String.valueOf(basicyml.get("basicitems." + name + ".attributes." + attributename + ".attributeid"));
    }

    public static void isitnull() {
        basicitems = new File(SMPItems.getPlugin().getDataFolder().getAbsolutePath() + "\\items", "basicitems.yml");
        basicyml = YamlConfiguration.loadConfiguration(basicitems);
        System.out.println(basicyml.get("basicitems.jubarium.itemid"));
    }

    public static String translateAttribute(String attribute) {

        switch (attribute) {
            case "GENERIC_ARMOR":
                return "Armor";
            case "GENERIC_ARMOR_TOUGHNESS":
                return "Armor Toughness";

            case "GENERIC_ATTACK_DAMAGE":
                return "Attack Damage";

            case "GENERIC_ATTACK_KNOCKBACK":
                return "Attack Knockback";

            case "GENERIC_ATTACK_SPEED":
                return "Attack Speed";

            case "GENERIC_KNOCKBACK_RESISTANCE":
                return "Knockback Resistance";

            case "GENERIC_LUCK":
                return "Luck";

            case "GENERIC_MAX_HEALTH":
                return "Max Health";

            case "GENERIC_MOVEMENT_SPEED":
                return "Speed";
        }
        return "Invalid Attribute";
    }

}
