package me.a_littlesquid.figt_for_honour.guilist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class menu {
    public static void  mainmenu(){
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
    }
}
