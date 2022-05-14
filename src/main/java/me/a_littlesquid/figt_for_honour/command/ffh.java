package me.a_littlesquid.figt_for_honour.command;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import me.a_littlesquid.figt_for_honour.Figt_for_honour;
import me.a_littlesquid.figt_for_honour.FileManager;
import me.a_littlesquid.figt_for_honour.TownData;
import me.a_littlesquid.figt_for_honour.guilist.townslist;
import me.a_littlesquid.figt_for_honour.task.count;
import me.a_littlesquid.figt_for_honour.task.meet;
import me.a_littlesquid.figt_for_honour.utils.Prefix;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ffh implements CommandExecutor {
    private Figt_for_honour plugin;
    private Residence residence = Residence.getInstance();
    public void getredstone(Inventory playerinvetory){
        //给玩家发标志块

        ItemStack itemstack = new ItemStack(Material.REDSTONE_BLOCK, 4);
        ItemMeta itemMeta=itemstack.getItemMeta();
        itemMeta.setDisplayName("城池标志块");
        List<String> lore=new ArrayList<>();
        lore.add(ChatColor.AQUA+"代表了城池主权的标志块");
        lore.add(ChatColor.UNDERLINE+"将标志块全部放入领地中以示主权");
        itemMeta.setLore(lore);
        itemstack.setItemMeta(itemMeta);
        playerinvetory.addItem(itemstack);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Prefix prefixmessage=new Prefix();
        String prefix=prefixmessage.getprefix();
        Plugin config=me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class);
        File filedata=new File(me.a_littlesquid.figt_for_honour.Figt_for_honour.getProvidingPlugin(me.a_littlesquid.figt_for_honour.Figt_for_honour.class).getDataFolder(),"data.yml");
        FileConfiguration data= YamlConfiguration.loadConfiguration(filedata);
        TownData townData=new TownData();
        Player player=(Player) sender;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + "——————" + ChatColor.GOLD + "欢迎使用城池争夺系统" + ChatColor.AQUA + "——————");
            sender.sendMessage(prefix + ChatColor.YELLOW + "主指令" + ChatColor.WHITE + " /ffh");
            sender.sendMessage(prefix + ChatColor.YELLOW + "重载插件" + ChatColor.WHITE + " /ffh reload ");
            sender.sendMessage(prefix + ChatColor.YELLOW + "获取城池菜单" + ChatColor.WHITE + " /ffh gui ");
            sender.sendMessage(prefix + ChatColor.YELLOW + "创建一个城池" + ChatColor.WHITE + " /ffh create {城池名称}");
            sender.sendMessage(prefix + ChatColor.YELLOW + "向其他城池宣战" + ChatColor.WHITE + " /ffh fight {城池名称}");
        }
        //主指令
        if(args.length ==1){
            String thenextcommand=args[0];
            if(thenextcommand.equalsIgnoreCase("reload")){
                FileManager.reloadfile(data,filedata);
                //储存文件
                config.saveConfig();
                config.reloadConfig();
                sender.sendMessage(prefix+ChatColor.GREEN+"数据储存成功");
            //配置文件
            }
            if(thenextcommand.equalsIgnoreCase("gui")){
                townslist.maingui(player);
            }

        }
        if (args.length == 2) {
            String thenextcommand = args[0];
            String townname=args[1];
            World world=player.getWorld();
            String worldname=world.getName();
            String playername=player.getDisplayName();
            if(thenextcommand.equalsIgnoreCase("fight")) {
                String enermy=townData.getOwner(townname);
                String owntownn=townData.gettownname(playername);
                Player enermyplayer= Bukkit.getPlayer(enermy);
                if(enermyplayer.isOnline()){
                    townData.setFighting(owntownn,true);
                    FileManager.reloadfile(data, filedata);
                    plugin = Figt_for_honour.getInstance();
                    meet meet=new meet(player,owntownn,townname,enermyplayer);
                    meet.runTaskAsynchronously(plugin);
                }else {
                    sender.sendMessage(prefix+ChatColor.RED+"该玩家未上线");
                }
            }
            if (thenextcommand.equalsIgnoreCase("create")) {
                Location location=player.getLocation();
                ClaimedResidence res = this.residence.getResidenceManager().getByLoc(location);
                if(res!=null) {
                    //检测玩家处地是否有领地
                    String owner=res.getOwner();
                    if(owner.equalsIgnoreCase(playername)){
                        //玩家是领地主人
                        Inventory pi=player.getInventory();
                        player.sendMessage(prefix+ChatColor.WHITE +"开启创建,请将标志块全部放入领地内");
                        player.sendTitle(ChatColor.AQUA+"开启创建","请把标志块全部放入领地内",10,70,20);
                        getredstone(pi);
                        //获取方块
                        String theresidencename=res.getResidenceName();
                        townData.setOwner(playername,townname);
                        townData.setResdenience(theresidencename,townname);
                        townData.setworldname(worldname,townname);
                        townData.setFighting(townname,false);
                        FileManager.reloadfile(data,filedata);
                        //写入储存文件
                    }
                    else {
                        sender.sendMessage(prefix+ChatColor.RED+"这个领地并不属于你");
                    }
                }else{
                    sender.sendMessage(prefix + ChatColor.RED +"这个地方并没有领地");
                }
            }
            // ffh create
            if(thenextcommand.equalsIgnoreCase("remove")){
                ConfigurationSection configurationSection=data.getConfigurationSection("towns");
                Set<String> townslist=configurationSection.getKeys(false);
                for (String town:townslist){
                    if(town.equalsIgnoreCase(townname)){
                        data.set("towns."+townname,null);
                        FileManager.reloadfile(data,filedata);
                        sender.sendMessage(prefix+ChatColor.GREEN+"清除成功");
                    }
                    if(townslist.isEmpty()){
                        break;
                    }
                }

            }
            // ffh remove
        }
        return false;
    }
}