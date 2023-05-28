package me.ppgome.smpitems.commands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import me.ppgome.smpitems.datamanager.JSONReader;
import me.ppgome.smpitems.items.BasicItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItem {

    private static BasicItem item;
    private static ItemStack itemtogive;

    public static void getItemCommand() {
        new CommandAPICommand("giveitem")
                .withArguments(new StringArgument("itemname"))
                .executes((sender, args) -> {
                    String arg = (String) args.get(0);
                    if(JSONReader.getBasicItemsList().containsKey(arg)) {
                        item = JSONReader.getBasicItemsList().get(arg);

                        itemtogive = BasicItem.createItem(item, arg);

                        if(sender instanceof Player) {
                            ((Player) sender).getInventory().addItem(itemtogive);
                        }

                    } else {
                        throw CommandAPI.failWithString("That item does not exist.");
                    }
                }).register();
    }
}
