package me.itzjatinog.rolesmp.commands;

import me.itzjatinog.rolesmp.RoleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PresidentTeleportCommand implements CommandExecutor {
    private final RoleManager roleManager;

    public PresidentTeleportCommand(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!roleManager.getRole(player).equals("President")) {
            player.sendMessage(ChatColor.RED + "Only the President can use this command!");
            return true;
        }

        if (roleManager.isCooldownActive(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Your teleportation ability is on cooldown!");
            return true;
        }

        // Teleport to the crosshair location
        player.teleport(player.getTargetBlock(null, 50).getLocation());
        player.sendMessage(ChatColor.GOLD + "You have teleported!");

        roleManager.setCooldown(player.getUniqueId(), 90000L);  // 1.5-minute cooldown
        return true;
    }
}