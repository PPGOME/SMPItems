package me.ppgome.smpitems.items;

import org.bukkit.Material;

import java.util.HashMap;

public class BasicItem {

    private Material itemtype;
    private int modeldata;
    private String name;
    private int durability;
    private String lore;
    private HashMap<String, Integer> attributes = new HashMap<>();

    public BasicItem(Material itemtype, int modeldata, String name, int durability, String lore, HashMap<String, Integer> attributes) {
        this.itemtype = itemtype;
        this.modeldata = modeldata;
        this.name = name;
        this.durability = durability;
        this.lore = lore;
        this.attributes = attributes;
    }
}
