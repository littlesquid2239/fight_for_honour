package me.a_littlesquid.figt_for_honour.utils;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class Prefix {
    public String getprefix(){
        Plugin config=me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class);
        String prefix=Color.translate(config.getConfig().getString("prefix"));
        return prefix;
    }
}
