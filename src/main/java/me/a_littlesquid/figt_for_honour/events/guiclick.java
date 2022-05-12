package me.a_littlesquid.figt_for_honour.events;

import me.a_littlesquid.figt_for_honour.guilist.townslist;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Set;

public class guiclick implements Listener {
    @EventHandler
    public void ClickGUI(InventoryClickEvent event){
        Player player= (Player) event.getWhoClicked();
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        if(event.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "城池列表")){
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "下一页")){
                Inventory inventory=event.getClickedInventory();
                ItemStack page=inventory.getItem(49);
                String pagenumber=page.getItemMeta().getDisplayName();
                String[] split= pagenumber.split("\\:");
                String number=split[1];
                int intnumber= Integer.parseInt(number);
                int result=(intnumber+1-1)*45;
                ConfigurationSection configurationSection=data.getConfigurationSection("towns");
                Set<String> list=configurationSection.getKeys(false);
                if (list.size()>=result) {
                    player.closeInventory();
                    townslist.nextpage(intnumber,player);
                }
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "下一页")){
                Inventory inventory=event.getClickedInventory();
                ItemStack page=inventory.getItem(49);
                String pagenumber=page.getItemMeta().getDisplayName();
                String[] split= pagenumber.split("\\:");
                String number=split[1];
                int intnumber= Integer.parseInt(number);
                int result=(intnumber-1-1)*45;
                ConfigurationSection configurationSection=data.getConfigurationSection("towns");
                Set<String> list=configurationSection.getKeys(false);
                if(intnumber>1){
                if (list.size()>=result) {
                    player.closeInventory();
                    townslist.previouspage(intnumber,player);
                    }
                }
            }
        }
    }
}
