package me.a_littlesquid.figt_for_honour.task;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class reborn extends BukkitRunnable {
    private Block block;

    public reborn(Block block){
        this.block=block;
    }
    @Override
    public void run() {
    block.setType(Material.WHITE_BANNER);
    }
}
