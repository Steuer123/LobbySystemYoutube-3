package de.steuer.lobby.main;

import de.steuer.lobby.commands.buildCMD;
import de.steuer.lobby.commands.setupCMD;
import de.steuer.lobby.commands.vanishCMD;
import de.steuer.lobby.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static List<Player> build = new ArrayList<>();
    public static List<Player> fly = new ArrayList<>();
    public static List<Player> vanish = new ArrayList<>();
    public static String prefix = "§8| §a§lLOBBY §r§8| §r";
    public static String noperms = prefix + "§7Dazu hast du keine Rechte.";
    public static String notfound = prefix + "§7Dieser Spieler wurde nicht gefunden.";

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage(prefix + "§7Das Lobbyplugin wurde erfolgreich gestartet");

        //LISTENER
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new SneakListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProtectionListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        //COMMANDS
        getCommand("build").setExecutor(new buildCMD());
        getCommand("lobby").setExecutor(new setupCMD());
        getCommand("vanish").setExecutor(new vanishCMD());
        getCommand("v").setExecutor(new vanishCMD());

        loadconfig();
    }

    private void loadconfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static Main getPlugin() {
        return plugin;
    }
}
