package me.a_littlesquid.figt_for_honour.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class count extends BukkitRunnable {
    private Player player;
    private String townname;
    public count(Player player,String townname){
        this.player=player;
        this.townname=townname;
    }
    @Override
    public void run(){
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        player.sendTitle(ChatColor.RED+"宣战已经开始了！","拿起武器去争夺荣耀吧",10,70,20);
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int onex=data.getInt("towns."+townname+".location.block1.x");
        int oney=data.getInt("towns."+townname+".location.block1.y");
        int onez=data.getInt("towns."+townname+".location.block1.z");
        String worldname=data.getString("towns."+townname+".location.worldname");
        World world= Bukkit.getWorld(worldname);
        Location onelocation=new Location(world,onex,oney+1,onez);
        Block oneblock= onelocation.getBlock();
        int twox=data.getInt("towns."+townname+".location.block2.x");
        int twoy=data.getInt("towns."+townname+".location.block2.y");
        int twoz=data.getInt("towns."+townname+".location.block2.z");
        Location twolocation=new Location(world,twox,twoy+1,twoz);
        Block twoblock= twolocation.getBlock();
        int threex=data.getInt("towns."+townname+".location.block3.x");
        int threey=data.getInt("towns."+townname+".location.block3.y");
        int threez=data.getInt("towns."+townname+".location.block3.z");
        Location threelocation=new Location(world,threex,threey+1,threez);
        Block threeblock= threelocation.getBlock();
        int fourx=data.getInt("towns."+townname+".location.block1.x");
        int foury=data.getInt("towns."+townname+".location.block1.y");
        int fourz=data.getInt("towns."+townname+".location.block1.z");
        Location fourlocation=new Location(world,fourx,foury+1,fourz);
        Block fourblock= fourlocation.getBlock();
        int result=4;
        if(oneblock.isEmpty()){
            result=result-1;
        }
        if(twoblock.isEmpty()){
            result=result-1;
        }
        if(threeblock.isEmpty()){
            result=result-1;
        }
        if(fourblock.isEmpty()){
            result=result-1;
        }
        if(result<=2){
            player.sendTitle(ChatColor.GREEN+"宣战已经结束了！","你在胜利中失败了",10,70,20);
        }else {
            player.sendTitle(ChatColor.RED + "宣战已经结束了！", "你在战争中失败了", 10, 70, 20);
        }
        data.set("towns." + townname + ".fighting", "true");
    }
}
