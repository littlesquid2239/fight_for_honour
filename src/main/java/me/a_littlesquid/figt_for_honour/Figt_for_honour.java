package me.a_littlesquid.figt_for_honour;

import me.a_littlesquid.figt_for_honour.command.ffh;
import me.a_littlesquid.figt_for_honour.events.breakblock;
import me.a_littlesquid.figt_for_honour.events.guiclick;
import me.a_littlesquid.figt_for_honour.events.putblock;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Figt_for_honour extends JavaPlugin {
    private static Figt_for_honour instance;

    public static Figt_for_honour getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance=this;
        saveDefaultConfig();
        saveResource("data.yml",false);
        //保存文件
        getCommand("ffh").setExecutor(new ffh());
        //命令
        getServer().getPluginManager().registerEvents(new putblock(),this);
        getServer().getPluginManager().registerEvents(new guiclick(),this);
        getServer().getPluginManager().registerEvents(new breakblock(),this);
        //注册事件

        this.getLogger().info(ChatColor.AQUA+"---------------------");
        this.getLogger().info("插件名称："+ ChatColor.GOLD+"fight for honour");
        this.getLogger().info("作者："+ChatColor.GOLD+"a——littlesquid");
        this.getLogger().info(ChatColor.RED+"私用插件，请勿转发倒卖！");
        this.getLogger().info(ChatColor.GREEN+"成功加载插件");
        this.getLogger().info(ChatColor.GREEN+"祝您游玩愉快");
        this.getLogger().info(ChatColor.AQUA+"---------------------");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
