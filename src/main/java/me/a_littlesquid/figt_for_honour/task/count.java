package me.a_littlesquid.figt_for_honour.task;

import me.a_littlesquid.figt_for_honour.TownData;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class count extends BukkitRunnable {
    private Player player;
    private String enermy;
    private String townname;
    public count(Player player,String townname,String enermy){
        this.player=player;
        this.townname=townname;
        this.enermy=enermy;
    }
    @Override
    public void run(){
        TownData townData=new TownData();
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        player.sendTitle(ChatColor.RED+"宣战已经开始了！","拿起武器去争夺荣耀吧",10,70,20);
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Location location1=townData.getBlockLocation(townname,1);

        Location location2=townData.getBlockLocation(townname,2);
        Location location3=townData.getBlockLocation(townname,3);
        Location location4=townData.getBlockLocation(townname,4);
        Location location5=townData.getBlockLocation(enermy,1);
        Location location6=townData.getBlockLocation(enermy,2);
        Location location7=townData.getBlockLocation(enermy,3);
        Location location8=townData.getBlockLocation(enermy,4);
        location1.setY(location1.getBlockY()+1);
        Block block1= location1.getBlock();
        location2.setY(location2.getBlockY()+1);
        Block block2= location2.getBlock();
        location3.setY(location3.getBlockY()+1);
        Block block3= location3.getBlock();
        location4.setY(location4.getBlockY()+1);
        Block block4= location4.getBlock();
        location5.setY(location5.getBlockY()+1);
        Block block5= location5.getBlock();
        location6.setY(location6.getBlockY()+1);
        Block block6= location6.getBlock();
        location7.setY(location7.getBlockY()+1);
        Block block7= location7.getBlock();
        location8.setY(location8.getBlockY()+1);
        Block block8= location8.getBlock();
        int result1=4;
        int result2=4;
        if (block1.isEmpty()){
            result1=result1-1;
        }

        if (block2.isEmpty()){
            result1=result1-1;
        }

        if (block3.isEmpty()){
            result1=result1-1;
        }

        if (block4.isEmpty()){
            result1=result1-1;
        }

        if (block5.isEmpty()){
            result2=result2-1;
        }
        if (block6.isEmpty()){
            result2=result2-1;
        }
        if (block7.isEmpty()){
            result2=result2-1;
        }
        if (block8.isEmpty()){
            result2=result2-1;
        }
        if(result1<=2){
            if (result2<=2){
                player.sendTitle(ChatColor.YELLOW+"宣战已经结束了！","你在战争中两败俱伤",10,70,20);
            }else{
                player.sendTitle(ChatColor.RED+"宣战已经结束了！","你在战争中失败了",10,70,20);
            }
        }else{
            if(result2<=2){
                player.sendTitle(ChatColor.GREEN+"宣战已经结束了！","你在战争中胜利了",10,70,20);
            }else {
                player.sendTitle(ChatColor.YELLOW+"宣战已经结束了！","双方在战争中并没有实质性的伤害",10,70,20);
            }
            Block bannerblock1=block1.getRelative(BlockFace.UP);
            Block bannerblock2=block2.getRelative(BlockFace.UP);
            Block bannerblock3=block3.getRelative(BlockFace.UP);
            Block bannerblock4=block4.getRelative(BlockFace.UP);
            Block bannerblock5=block5.getRelative(BlockFace.UP);
            Block bannerblock6=block6.getRelative(BlockFace.UP);
            Block bannerblock7=block7.getRelative(BlockFace.UP);
            Block bannerblock8=block8.getRelative(BlockFace.UP);
            if (bannerblock1.isEmpty()){
                bannerblock1.setType(Material.WHITE_BANNER);
            }
            if (bannerblock2.isEmpty()){
                bannerblock2.setType(Material.WHITE_BANNER);
            }
            if (bannerblock3.isEmpty()){
                bannerblock3.setType(Material.WHITE_BANNER);
            }
            if (bannerblock4.isEmpty()){
                bannerblock4.setType(Material.WHITE_BANNER);
            }
            if (bannerblock5.isEmpty()){
                bannerblock5.setType(Material.WHITE_BANNER);
            }
            if (bannerblock6.isEmpty()){
                bannerblock6.setType(Material.WHITE_BANNER);
            }
            if (bannerblock7.isEmpty()){
                bannerblock7.setType(Material.WHITE_BANNER);
            }
            if (bannerblock8.isEmpty()){
                bannerblock8.setType(Material.WHITE_BANNER);
            }
        }
        townData.setFighting(townname,false);
        townData.setFighting(enermy,false);
    }
}
