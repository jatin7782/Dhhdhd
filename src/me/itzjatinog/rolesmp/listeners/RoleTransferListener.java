package me.itzjatinog.rolesmp.listeners;

import me.itzjatinog.rolesmp.RoleManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class RoleTransferListener implements Listener {
    private final RoleManager roleManager;

    public RoleTransferListener(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer != null) {
            String victimRole = roleManager.getRole(victim);

            // Remove the victim's role and assign it to the killer
            roleManager.assignRandomRole(killer);
            killer.sendMessage(ChatColor.GREEN + "You have inherited the role of " + ChatColor.AQUA + victimRole + "!");
        }
    }
}