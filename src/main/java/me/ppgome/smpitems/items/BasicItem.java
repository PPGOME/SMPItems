package me.ppgome.smpitems.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteItemNBT;
import me.ppgome.smpitems.datamanager.JSONReader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class BasicItem {

    private Material itemtype;
    private Integer modeldata;
    private String name;
    private String lore;
    private Map<String, Double> attributes;

    private ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    private MiniMessage mm = MiniMessage.miniMessage();
    private static AttributeModifier attribute;

    public BasicItem(String name, int modeldata, Material itemtype, String lore, HashMap<String, Double> attributes) {
        this.itemtype = itemtype;
        this.modeldata = modeldata;
        this.name = name;
        this.lore = lore;
        this.attributes = attributes;

        console.sendMessage(Component.text().content("====== NEW ITEM ======").color(TextColor.fromHexString("#ff4d4d")));
        console.sendMessage(Component.text().content("Name: ").append(mm.deserialize(name)).build());
        console.sendMessage(Component.text().content("Model data: ").append(Component.text(String.valueOf(modeldata))).build());
        console.sendMessage(Component.text().content("Item type: ").append(mm.deserialize(String.valueOf(itemtype))).build());
        console.sendMessage(Component.text().content("Lore: ").append(mm.deserialize(lore)).build());
        console.sendMessage(Component.text().content("Attributes:").build());
        if(attributes != null) {
            attributes.forEach((key, value) -> {
                console.sendMessage(Component.text().content(key + ": " + value).build());
            });
        }
        console.sendMessage(Component.text().content("======================\n").color(TextColor.fromHexString("#ff4d4d")));

    }

    public static ItemStack createItem(BasicItem item, String itemname) {
        ItemStack customitem = new ItemStack(item.itemtype);
        ItemMeta custommeta = customitem.getItemMeta();
        custommeta.displayName(MiniMessage.miniMessage().deserialize(item.name));
        custommeta.setCustomModelData(item.modeldata);
        List<Component> lorelist = new ArrayList<>();
        lorelist.add(MiniMessage.miniMessage().deserialize(item.lore));
        JSONReader.getBasicItemsList().forEach((key, value) -> {
            if(key.equalsIgnoreCase(itemname)) {
                if(value.attributes != null) {
                    value.attributes.forEach((akey, avalue) -> {
                        System.out.println("It got here");
                        JSONReader.isitnull();
                        if(JSONReader.getUUID(itemname) != null) {
                            System.out.println("It also got here");
                            attribute = new AttributeModifier(Objects.requireNonNull(JSONReader.getUUID(itemname)),
                                    akey, avalue, AttributeModifier.Operation.ADD_NUMBER);
                            custommeta.addAttributeModifier(Attribute.valueOf(akey), attribute);
                            lorelist.add(Component.text(JSONReader.translateAttribute(akey) + ": +" + avalue)
                                    .color(TextColor.fromHexString("#6f57ea")));
                            System.out.println(new AttributeModifier(akey, avalue, AttributeModifier.Operation.ADD_NUMBER));
                        } else {
                            System.out.println("It got here instead");
                            JSONReader.setUUID(key, UUID.randomUUID());
                        }
                    });
                }
            }
        });
        custommeta.lore(lorelist);
        custommeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        customitem.setItemMeta(custommeta);
        return customitem;
    }

    public int getModelData() {
        return modeldata;
    }

}
