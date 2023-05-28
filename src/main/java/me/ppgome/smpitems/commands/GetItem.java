package me.ppgome.smpitems.commands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import me.ppgome.smpitems.datamanager.JSONReader;
import me.ppgome.smpitems.items.BasicItem;

public class GetItem {

    private static BasicItem item;

    public static void getItemCommand() {
        new CommandAPICommand("giveitem")
                .withArguments(new GreedyStringArgument("itemname"))
                .executes((sender, args) -> {
                    if(JSONReader.getBasicItemsList().containsKey(args)) {
                        item = JSONReader.getBasicItemsList().get(args);
                    } else {
                        throw CommandAPI.failWithString("That item does not exist.");
                    }
                }).register();
    }
}
