package me.a_littlesquid.figt_for_honour;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Set;

public class TownData {

    public static boolean onlyhasonetown(String playername) {
        File filedata = new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(), "data.yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(filedata);
        ConfigurationSection configurationSection = data.getConfigurationSection("towns");
        Set<String> list = configurationSection.getKeys(false);
        int number = 0;
        for (String townname : list) {
            String owner = data.getString("towns." + townname + ".owner");
            if (owner.equalsIgnoreCase(playername)) {
                number++;
            }
        }
        if (number > 1) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean isRepetitive(String townname){
        return true;
    }
    public String getOwner(String townname){
        File filedata = new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(), "data.yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(filedata);
        String owner= data.getString("towns."+townname+".owner");
        return owner;
    }

}

