package me.a_littlesquid.figt_for_honour.task;

import me.a_littlesquid.figt_for_honour.Figt_for_honour;
import me.a_littlesquid.figt_for_honour.FileManager;
import me.a_littlesquid.figt_for_honour.TownData;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.VaultEco;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

import static org.bukkit.Bukkit.getServer;

public class count extends BukkitRunnable {
    private Player player;
    private static Economy econ = null;
    private String enermy;
    private String townname;
    private Figt_for_honour plugin;
    public count(Player player,String townname,String enermy){
        this.player=player;
        this.townname=townname;
        this.enermy=enermy;
    }
    @Override
    public void run(){

        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        TownData townData=new TownData(data,filedata);
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
        Block block1= location1.getBlock();
        Block block2= location2.getBlock();
        Block block3= location3.getBlock();
        Block block4= location4.getBlock();
        Block block5= location5.getBlock();
        Block block6= location6.getBlock();
        Block block7= location7.getBlock();
        Block block8= location8.getBlock();
        Block bannerblock1=block1.getRelative(BlockFace.UP);
        Block bannerblock2=block2.getRelative(BlockFace.UP);
        Block bannerblock3=block3.getRelative(BlockFace.UP);
        Block bannerblock4=block4.getRelative(BlockFace.UP);
        Block bannerblock5=block5.getRelative(BlockFace.UP);
        Block bannerblock6=block6.getRelative(BlockFace.UP);
        Block bannerblock7=block7.getRelative(BlockFace.UP);
        Block bannerblock8=block8.getRelative(BlockFace.UP);
        int result1=4;
        int result2=4;

        if (bannerblock1.isEmpty()){

            result1=result1-1;
        }
        if (bannerblock2.isEmpty()){

            result1=result1-1;
        }
        if (bannerblock3.isEmpty()){

            result1=result1-1;
        }
        if (bannerblock4.isEmpty()){

            result1=result1-1;
        }
        if (bannerblock5.isEmpty()){

            result2=result2-1;
        }
        if (bannerblock6.isEmpty()){

            result2=result2-1;
        }
        if (bannerblock7.isEmpty()){

            result2=result2-1;
        }
        if (bannerblock8.isEmpty()){

            result2=result2-1;
        }
        plugin = Figt_for_honour.getInstance();
        reborn task1=new reborn(bannerblock1);
        reborn task2=new reborn(bannerblock2);
        reborn task3=new reborn(bannerblock3);
        reborn task4=new reborn(bannerblock4);
        reborn task5=new reborn(bannerblock5);
        reborn task6=new reborn(bannerblock6);
        reborn task7=new reborn(bannerblock7);
        reborn task8=new reborn(bannerblock8);
        task1.runTask(plugin);
        task2.runTask(plugin);
        task3.runTask(plugin);
        task4.runTask(plugin);
        task5.runTask(plugin);
        task6.runTask(plugin);
        task7.runTask(plugin);
        task8.runTask(plugin);
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        econ = rsp.getProvider();
        double balance= econ.getBalance(player);
        if(result1<=2){
            if (result2<=2){
                player.sendTitle(ChatColor.YELLOW+"宣战已经结束了！","你在战争中两败俱伤",10,70,20);

            }else{
                player.sendTitle(ChatColor.RED+"宣战已经结束了！","你在战争中失败了",10,70,20);
                balance=balance-100;
            }
        }else{
            if(result2<=2){
                player.sendTitle(ChatColor.GREEN+"宣战已经结束了！","你在战争中胜利了",10,70,20);
                balance=balance+100;
            }else {
                player.sendTitle(ChatColor.YELLOW+"宣战已经结束了！","双方在战争中并没有实质性的伤害",10,70,20);
            }
        }
        data.set("towns." + townname + ".fighting","false");
        data.set("towns." + enermy + ".fighting","false");
        FileManager.reloadfile(data,filedata);
    }
}
