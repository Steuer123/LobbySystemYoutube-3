package de.steuer.lobby.listener;

import de.steuer.lobby.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(Main.vanish.contains(e.getPlayer())) {
            if(!e.getPlayer().hasPermission("lobby.vanish.on")) {
                Main.vanish.remove(e.getPlayer());
                for(PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
                    e.getPlayer().removePotionEffect(effect.getType());
                }
            }

        }

        Player p = e.getPlayer();
        String msg = Main.getPlugin().getConfig().getString("Lobby.LeaveMessage");
        msg = msg.replaceAll("&", "§");
        msg = msg.replace("%player%", p.getName());
        e.setQuitMessage(msg);
    }
}
