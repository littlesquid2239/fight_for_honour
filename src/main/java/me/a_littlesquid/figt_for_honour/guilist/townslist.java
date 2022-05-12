package me.a_littlesquid.figt_for_honour.guilist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class townslist {
    public static void previouspage(int number ,Player player){
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        Inventory gui = Bukkit.createInventory(null, 6 * 9, ChatColor.AQUA + "城池列表");
        ItemStack page= new ItemStack(Material.PAPER);
        ItemMeta pagemeta=page.getItemMeta();
        int result=number-1;
        pagemeta.setDisplayName("页数:"+result);
        page.setItemMeta(pagemeta);
        gui.setItem(49,page);
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta g = glass.getItemMeta();
        g.setDisplayName(ChatColor.GOLD + "欢迎使用");
        glass.setItemMeta(g);
        gui.setItem(52,glass);
        gui.setItem(51, glass);
        gui.setItem(50, glass);
        gui.setItem(48, glass);
        gui.setItem(47, glass);
        gui.setItem(46, glass);
        ItemStack thenextpage=new ItemStack(Material.OAK_SIGN);
        ItemMeta thenext=thenextpage.getItemMeta();
        thenext.setDisplayName(ChatColor.GOLD+"下一页");
        thenextpage.setItemMeta(thenext);
        gui.setItem(53,thenextpage);
        ItemStack thepreivouspage=new ItemStack(Material.OAK_SIGN);
        ItemMeta thepreivous=thepreivouspage.getItemMeta();
        thepreivous.setDisplayName(ChatColor.GOLD+"上一页");
        thepreivouspage.setItemMeta(thepreivous);
        gui.setItem(45,thepreivouspage);
        int min=(result-1)*45;
        int max=result*45-1;
        ItemStack town=new ItemStack(Material.WHITE_BANNER);
        ItemMeta townmeta=town.getItemMeta();
        ConfigurationSection configurationSection=data.getConfigurationSection("towns");
        Set<String> townslist=configurationSection.getKeys(false);
        int index=0;
        for(String townname:townslist){
            if(index>=min){
                townmeta.setDisplayName(townname);
                String owner=data.getString("towns."+townname+".owner");
                String residencename=data.getString("towns."+townname+".residencename");
                List<String>  lore=new ArrayList<>();
                lore.add(ChatColor.GOLD+"君主:"+ChatColor.GREEN+owner);
                lore.add(ChatColor.GOLD+"所在领地:"+ChatColor.GREEN+residencename);
                townmeta.setLore(lore);
                town.setItemMeta(townmeta);
                gui.setItem(index,town);
            }
            if(index==max){
                break;
            }
            index++;
        }
        player.openInventory(gui);
    }
    public static  void nextpage(int number ,Player player){
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        Inventory gui = Bukkit.createInventory(null, 6 * 9, ChatColor.AQUA + "城池列表");
        ItemStack page= new ItemStack(Material.PAPER);
        ItemMeta pagemeta=page.getItemMeta();
        int result=number+1;
        pagemeta.setDisplayName("页数:"+result);
        page.setItemMeta(pagemeta);
        gui.setItem(49,page);
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta g = glass.getItemMeta();
        g.setDisplayName(ChatColor.GOLD + "欢迎使用");
        glass.setItemMeta(g);
        gui.setItem(52,glass);
        gui.setItem(51, glass);
        gui.setItem(50, glass);
        gui.setItem(48, glass);
        gui.setItem(47, glass);
        gui.setItem(46, glass);
        ItemStack thenextpage=new ItemStack(Material.OAK_SIGN);
        ItemMeta thenext=thenextpage.getItemMeta();
        thenext.setDisplayName(ChatColor.GOLD+"下一页");
        thenextpage.setItemMeta(thenext);
        gui.setItem(53,thenextpage);
        ItemStack thepreivouspage=new ItemStack(Material.OAK_SIGN);
        ItemMeta thepreivous=thepreivouspage.getItemMeta();
        thepreivous.setDisplayName(ChatColor.GOLD+"上一页");
        thepreivouspage.setItemMeta(thepreivous);
        gui.setItem(45,thepreivouspage);
        int min=(result-1)*45;
        int max=result*45-1;
        ItemStack town=new ItemStack(Material.WHITE_BANNER);
        ItemMeta townmeta=town.getItemMeta();
        ConfigurationSection configurationSection=data.getConfigurationSection("towns");
        Set<String> townslist=configurationSection.getKeys(false);
        int index=0;
        for(String townname:townslist){
            if(index>=min){
                townmeta.setDisplayName(townname);
                String owner=data.getString("towns."+townname+".owner");
                String residencename=data.getString("towns."+townname+".residencename");
                List<String>  lore=new ArrayList<>();
                lore.add(ChatColor.GOLD+"君主:"+ChatColor.GREEN+owner);
                lore.add(ChatColor.GOLD+"所在领地:"+ChatColor.GREEN+residencename);
                townmeta.setLore(lore);
                town.setItemMeta(townmeta);
                gui.setItem(index,town);
            }
            if(index==max){
                break;
            }
            index++;
        }
        player.openInventory(gui);
    }
    public static void maingui(Player player){
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        Inventory gui = Bukkit.createInventory(null, 6 * 9, ChatColor.AQUA + "城池列表");
        ItemStack page= new ItemStack(Material.PAPER);
        ItemMeta pagemeta=page.getItemMeta();
        pagemeta.setDisplayName("页数:1");
        page.setItemMeta(pagemeta);
        gui.setItem(49,page);
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta g = glass.getItemMeta();
        g.setDisplayName(ChatColor.GOLD + "欢迎使用");
        glass.setItemMeta(g);
        gui.setItem(52,glass);
        gui.setItem(51, glass);
        gui.setItem(50, glass);
        gui.setItem(48, glass);
        gui.setItem(47, glass);
        gui.setItem(46, glass);
        ItemStack thenextpage=new ItemStack(Material.OAK_SIGN);
        ItemMeta thenext=thenextpage.getItemMeta();
        thenext.setDisplayName(ChatColor.GOLD+"下一页");
        thenextpage.setItemMeta(thenext);
        gui.setItem(53,thenextpage);
        ItemStack thepreivouspage=new ItemStack(Material.OAK_SIGN);
        ItemMeta thepreivous=thepreivouspage.getItemMeta();
        thepreivous.setDisplayName(ChatColor.GOLD+"上一页");
        thepreivouspage.setItemMeta(thepreivous);
        gui.setItem(45,thepreivouspage);
        ItemStack town=new ItemStack(Material.WHITE_BANNER);
        ItemMeta townmeta=town.getItemMeta();
        ConfigurationSection configurationSection=data.getConfigurationSection("towns");
        Set<String> townslist=configurationSection.getKeys(false);
        int index=0;
        for(String townname:townslist){
            townmeta.setDisplayName(townname);
            String owner=data.getString("towns."+townname+".owner");
            String residencename=data.getString("towns."+townname+".residencename");
            List<String>  lore=new ArrayList<>();
            lore.add(ChatColor.GOLD+"君主:"+ChatColor.GREEN+owner);
            lore.add(ChatColor.GOLD+"所在领地:"+ChatColor.GREEN+residencename);
            townmeta.setLore(lore);
            town.setItemMeta(townmeta);
            gui.setItem(index,town);
            if(index==44){
                break;
            }
            index++;
        }

    player.openInventory(gui);
    }
}
