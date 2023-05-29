package me.ppgome.smpitems.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.NotePlayEvent;

public class BasicBlock implements Listener {



    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent e) {

        if(e.getBlock().getType() == Material.NOTE_BLOCK) {
            Block block = e.getBlock();
            if(block.getBlockData() instanceof NoteBlock) {
                NoteBlock nb = (NoteBlock) block.getBlockData();
                if(nb.getInstrument().equals(Instrument.STICKS)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler void onNotePlay(NotePlayEvent e) {
        if(e.getInstrument().equals(Instrument.STICKS)) {
            e.setCancelled(true);
            e.setNote(e.getNote());
        }
    }

}