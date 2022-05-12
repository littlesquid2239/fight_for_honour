package me.a_littlesquid.figt_for_honour.events;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.Set;


public class putblock implements Listener {
    public String prefix= ChatColor.AQUA + "[" + ChatColor.GOLD + "fight_for_hornor" + ChatColor.AQUA + "]: ";
    private Residence residence = Residence.getInstance();
    @EventHandler
    public  void onPutBlock(BlockPlaceEvent event){
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        Block block=event.getBlock();
        Player player=event.getPlayer();
        String playername=player.getDisplayName();
        Material blockmaterial=block.getType();
        if (blockmaterial.equals(Material.REDSTONE_BLOCK)){
            //如果检测到的数据为红石
            Location loc = event.getBlock().getLocation();
            int locx=loc.getBlockX();
            int locy=loc.getBlockY();
            int locz=loc.getBlockZ();
            ClaimedResidence res = this.residence.getResidenceManager().getByLoc(loc);
            if(res!=null) {
                //如果该位置有领地
                String thetownname=res.getResidenceName();
                ConfigurationSection configurationSection=data.getConfigurationSection("towns");
                for(String town:configurationSection.getKeys(false)){
                    String thetownresidencename=data.getString("towns."+town+".residencename");
                    if(thetownresidencename.equalsIgnoreCase(thetownname)){
                        //获取该地的城池信息
                        String theowner=data.getString("towns."+town+".owner");
                            if(theowner.equalsIgnoreCase(playername)){
                                ConfigurationSection configurationSection1=data.getConfigurationSection("towns."+town+".location");
                                Set<String> blcoklocation=configurationSection1.getKeys(false);
                                Block bannerblock=block.getRelative(BlockFace.UP);
                                if(blcoklocation.size()==1){
                                        data.set("towns."+town+".location.block1.x",locx);
                                        data.set("towns."+town+".location.block1.y",locy);
                                        data.set("towns."+town+".location.block1.z",locz);
                                        bannerblock.setType(Material.WHITE_BANNER);
                                    }
                                    if(blcoklocation.size()==2){
                                        data.set("towns."+town+".location.block2.x",locx);
                                        data.set("towns."+town+".location.block2.y",locy);
                                        data.set("towns."+town+".location.block2.z",locz);
                                        bannerblock.setType(Material.WHITE_BANNER);
                                    }
                                    if(blcoklocation.size()==3){
                                        data.set("towns."+town+".location.block3.x",locx);
                                        data.set("towns."+town+".location.block3.y",locy);
                                        data.set("towns."+town+".location.block3.z",locz);
                                        bannerblock.setType(Material.WHITE_BANNER);
                                    }
                                    if(blcoklocation.size()==4){
                                        data.set("towns."+town+".location.block4.x",locx);
                                        data.set("towns."+town+".location.block4.y",locy);
                                        data.set("towns."+town+".location.block4.z",locz);
                                        bannerblock.setType(Material.WHITE_BANNER);
                                        player.sendTitle(ChatColor.GREEN+"创建成功","领地名称："+ChatColor.GOLD+town,10,70,20);
                                    }

                                try {
                                    data.save(filedata);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    data.load(filedata);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                } catch (InvalidConfigurationException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            else{
                                player.sendMessage(prefix+ChatColor.RED+"这个领地并不属于你");
                                event.setCancelled(true);
                            }

                    }
                }
            }
            else{
                ItemStack itemStack=event.getItemInHand();
                ItemMeta itemMeta=itemStack.getItemMeta();
                String itemname= itemMeta.getDisplayName();
                if(itemname.equalsIgnoreCase("城池标志块")){
                    event.setCancelled(true);
                    player.sendMessage(prefix+ChatColor.RED+"标志块无法放在领地外");
                }
            }
        }
    }
}

