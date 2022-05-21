package me.a_littlesquid.figt_for_honour.guilist;

import me.a_littlesquid.figt_for_honour.TownData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class menu {
    public static void  mainmenu(Player player){
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        TownData townData=new TownData(data,filedata);
        String playername= player.getDisplayName();
        String townname=townData.gettownname(playername);
        Inventory gui = Bukkit.createInventory(null, 6 * 9, ChatColor.AQUA + "城池菜单");
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta g = glass.getItemMeta();
        g.setDisplayName(ChatColor.GOLD + "欢迎使用");
        glass.setItemMeta(g);
        gui.setItem(0,glass);
        gui.setItem(1,glass);
        gui.setItem(2,glass);
        gui.setItem(3,glass);
        gui.setItem(4,glass);
        gui.setItem(5,glass);
        gui.setItem(6,glass);
        gui.setItem(7,glass);
        gui.setItem(8,glass);
        gui.setItem(9,glass);
        gui.setItem(17,glass);
        gui.setItem(18,glass);
        gui.setItem(26,glass);
        gui.setItem(27,glass);
        gui.setItem(28,glass);
        gui.setItem(35,glass);
        gui.setItem(36,glass);
        gui.setItem(44,glass);
        ItemStack info=new ItemStack(Material.OAK_SIGN);
        ItemMeta infoMeta=info.getItemMeta();
        infoMeta.setDisplayName( ChatColor.AQUA+"城池信息");
        if (townData.hastown(playername)){
            List<String> lore=new ArrayList<>();
            lore.add(ChatColor.GOLD+"城池名："+ChatColor.GREEN+townname);
            lore.add(ChatColor.GOLD+"拥有者："+ChatColor.GREEN+townData.getOwner(townname));
            lore.add(ChatColor.GOLD+"所属领地："+ChatColor.GREEN+townData.getResidencename(townname));
            infoMeta.setLore(lore);
        }
        info.setItemMeta(infoMeta);
        gui.setItem(11,info);
        ItemStack banner=new ItemStack(Material.WHITE_BANNER);
        ItemMeta bannerMeta=banner.getItemMeta();
        bannerMeta.setDisplayName("设置旗帜");
        List<String> lore=new ArrayList<>();
        lore.add(ChatColor.GOLD+"点击此处设置旗帜");
        infoMeta.setLore(lore);
        banner.setItemMeta(bannerMeta);
        gui.setItem(13,banner);
    }
}
