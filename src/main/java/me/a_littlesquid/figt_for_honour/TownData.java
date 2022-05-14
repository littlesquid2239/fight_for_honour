package me.a_littlesquid.figt_for_honour;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Set;

public class TownData {
     private File filedata = new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(), "data.yml");
     private FileConfiguration data = YamlConfiguration.loadConfiguration(filedata);
    public boolean onlyhasonetown(String playername) {

        ConfigurationSection configurationSection = data.getConfigurationSection("towns");
        Set<String> list = configurationSection.getKeys(false);
            int number = 0;
            for (String townname : list) {
                String owner = data.getString("towns." + townname + ".owner");
                if (owner.equalsIgnoreCase(playername)) {
                    number++;
                }
            }
            return number <= 1;
    }
    public boolean isRepetitive(String townname){
        return true;
    }
    public String getOwner(String townname){
        String owner= data.getString("towns."+townname+".owner");
        return owner;
    }
    public String gettownname(String owner) {
        ConfigurationSection configurationSection = data.getConfigurationSection("towns");
        Boolean result = false;
        Set<String> list = configurationSection.getKeys(false);
        String resultname = null;
        for (String name : list) {
            String townowner = data.getString("towns." + name + ".owner");
            if (townowner.equalsIgnoreCase(owner)) {
                resultname = name;
                result = true;
            }
        }
        if (result = false) {
            return null;
        }else{
            return resultname;
        }
    }

    public  String getResidencename(String townname){
        String res=data.getString("towns."+townname+".residencename");
        return res;
    }
    public Location getBlockLocation(String townname,int number){
        int x=data.getInt("towns."+townname+".location.block"+number+".x");
        int y=data.getInt("towns."+townname+".location.block"+number+".y");
        int z=data.getInt("towns."+townname+".location.block"+number+".z");
        String worldname=data.getString("towns."+townname+".location.worldname");
        World world= Bukkit.getWorld(worldname);
        Location location=new Location(world,x,y,z);
        return location;
    }
    public void setOwner(String owner,String townname){
        data.set("towns."+townname+".owner",owner);
    }
    public void setResdenience(String Residencename,String townname){
        data.set("towns."+townname+".residencename",Residencename);
    }
    public void setLocation(String townname,int number,int x,int y,int z){
        data.set("towns."+townname+".location.block"+number+".x",x);
        data.set("towns."+townname+".location.block"+number+".x",y);
        data.set("towns."+townname+".location.block"+number+".x",z);
    }
    public void setFighting(String townname,Boolean isfighting){
        String result=null;
        if(isfighting){
             result="true";
        }else {
            result="false";
        }
        data.set("towns." + townname + ".fighting",result);
    }
    public Boolean isFighting(String townname){
        Boolean isfinghting=null;
        String fight=data.getString("towns." + townname + ".fighting");
        if (fight.equalsIgnoreCase("true")){
            isfinghting=true;
        }
        if (fight.equalsIgnoreCase("false")){
            isfinghting=false;
        }

        return isfinghting;
    }
    public void setworldname(String worldname,String townname){
        data.set("towns."+townname+".location.worldname",worldname);
    }
    public String getworldname(String townname){
        String worldname=data.getString("towns."+townname+".location.worldname");
        return worldname;
    }
}

