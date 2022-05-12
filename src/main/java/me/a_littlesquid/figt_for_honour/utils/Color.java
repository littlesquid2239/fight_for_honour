package me.a_littlesquid.figt_for_honour.utils;

import org.bukkit.ChatColor;

public class Color {
    public static String translate(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }
}
