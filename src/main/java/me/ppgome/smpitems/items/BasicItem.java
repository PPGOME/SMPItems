package me.ppgome.smpitems.items;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class BasicItem {

    private Material itemtype;
    private Integer modeldata;
    private String name;
    private String lore;
    private Map<String, Integer> attributes = new HashMap<>();

    public BasicItem(String name, int modeldata, Material itemtype, String lore, HashMap<String, Integer> attributes) {
        this.itemtype = itemtype;
        this.modeldata = modeldata;
        this.name = name;
        this.lore = lore;
        this.attributes = attributes;

        System.out.printf("Name: " + MiniMessage.miniMessage().deserialize(name) + "\n");

    }
}
