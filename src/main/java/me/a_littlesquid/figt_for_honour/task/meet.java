package me.a_littlesquid.figt_for_honour.task;

import me.a_littlesquid.figt_for_honour.Figt_for_honour;
import me.a_littlesquid.figt_for_honour.TownData;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class meet extends BukkitRunnable {
    private String enermy;
    Player enermyplayer;
    private Player player;
    private String townname;
    private Figt_for_honour plugin;
    public meet(Player player,String townname,String enermy ,Player enermyplayer){
        this.player=player;
        this.townname=townname;
        this.enermy=enermy;
        this.enermyplayer=enermyplayer;
    }
    public meet getmeet(){
        return this;
    }
    @Override
    public void run() {
        TownData townData=new TownData();
        if (townData.isFighting(enermy)){
            count task=new count(player,townname,enermy);
            count task1=new count(enermyplayer,enermy,townname);
            task.runTaskAsynchronously(plugin);
            task1.runTaskAsynchronously(plugin);
        }else{
            player.sendTitle("等待对方接战","对方将有十分钟来应战",10,70,20);
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Boolean result=townData.isFighting(enermy);
            if (!result){
                townData.setFighting(townname ,false);
                townData.setFighting(enermy,false);
            }
        }
    }
}
