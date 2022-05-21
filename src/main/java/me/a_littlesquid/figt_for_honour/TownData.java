package me.a_littlesquid.figt_for_honour;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.Set;

public class TownData {
    public TownData(FileConfiguration data ,File filedata){
        this.filedata=filedata;
        this.data=data;
    }
    private File filedata;
    private FileConfiguration data;
    public boolean hastown(String playername) {

        ConfigurationSection configurationSection = data.getConfigurationSection("towns");
        Set<String> list = configurationSection.getKeys(false);
        boolean result=false;
            for (String townname : list) {
                String owner = data.getString("towns." + townname + ".owner");
                if (owner.equalsIgnoreCase(playername)) {
                    result=true;
                    break;
                }
            }
            return result;
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
    public String gettownnameByres(String residencename){
        ConfigurationSection configurationSection = data.getConfigurationSection("towns");
        Boolean result = false;
        Set<String> list = configurationSection.getKeys(false);
        String resultname = null;
        for (String name : list) {
            String townowner = data.getString("towns." + name + ".owner");
            if (townowner.equalsIgnoreCase(residencename)) {
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
        FileManager.reloadfile(data,filedata);
    }
    public void setResdenience(String Residencename,String townname){
        data.set("towns."+townname+".residencename",Residencename);
        FileManager.reloadfile(data,filedata);
    }
    public void setLocation(String townname,int number,int x,int y,int z){
        data.set("towns."+townname+".location.block"+number+".x",x);
        data.set("towns."+townname+".location.block"+number+".x",y);
        data.set("towns."+townname+".location.block"+number+".x",z);
        FileManager.reloadfile(data,filedata);
    }
    public void setFighting(String townname,Boolean isfighting){
        String result=null;
        if(isfighting){
             result="true";
        }else {
            result="false";
        }
        data.set("towns." + townname + ".fighting",result);
        FileManager.reloadfile(data,filedata);
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
        FileManager.reloadfile(data,filedata);
    }
    public String getworldname(String townname){
        String worldname=data.getString("towns."+townname+".location.worldname");
        return worldname;
    }
}

