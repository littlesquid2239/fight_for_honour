package me.a_littlesquid.figt_for_honour.task;

import me.a_littlesquid.figt_for_honour.Figt_for_honour;
import me.a_littlesquid.figt_for_honour.FileManager;
import me.a_littlesquid.figt_for_honour.TownData;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class meet extends BukkitRunnable {
    private String enermy;
    private Player enermyplayer;
    private Player player;
    private String townname;
    private Figt_for_honour plugin;
    public meet(Player player,String townname,String enermy ,Player enermyplayer){
        this.player=player;
        this.townname=townname;
        this.enermy=enermy;
        this.enermyplayer=enermyplayer;
    }
    @Override
    public void run() {

        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        TownData townData=new TownData(data,filedata);
        if (townData.isFighting(enermy)){
            plugin = Figt_for_honour.getInstance();
            count task=new count(player,townname,enermy);
            count task1=new count(enermyplayer,enermy,townname);
            task.runTaskAsynchronously(plugin);
            task1.runTaskAsynchronously(plugin);
        }else{
            player.sendTitle("等待对方接战","对方将有十分钟来应战",10,70,20);
            enermyplayer.sendTitle(ChatColor.RED+"收到一份战书","输入/ffh fight "+townname+" 来应战",20,140,40);
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Boolean result=townData.isFighting(enermy);
            if (!result){
                data.set("towns." + townname + ".fighting","false");
                data.set("towns." + enermy + ".fighting","false");
                FileManager.reloadfile(data,filedata);
            }
        }
    }
}
