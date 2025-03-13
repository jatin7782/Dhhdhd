package me.itzjatinog.rolesmp.listeners;

import me.itzjatinog.rolesmp.RoleManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class PlayerJoinListener implements Listener {
    private final RoleManager roleManager;

    public PlayerJoinListener(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Assign a random role if the player doesn't have one already
        if (roleManager.getRole(player).equals("None")) {
            roleManager.assignRandomRole(player);
        }
    }
}