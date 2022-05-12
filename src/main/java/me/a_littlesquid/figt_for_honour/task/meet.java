package me.a_littlesquid.figt_for_honour.task;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class meet extends BukkitRunnable {
    private Player player;
    private String townname;
    public meet(Player player,String townname){
        this.player=player;
        this.townname=townname;
    }
    @Override
    public void run() {
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        player.sendTitle("等待对方接战","对方将有十分钟来应战",10,70,20);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
