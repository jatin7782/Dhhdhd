package me.itzjatinog.rolesmp.commands;

import me.itzjatinog.rolesmp.RoleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PrimeMinisterHealCommand implements CommandExecutor {
    private final RoleManager roleManager;

    public PrimeMinisterHealCommand(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!roleManager.getRole(player).equals("Prime Minister")) {
            player.sendMessage(ChatColor.RED + "Only the Prime Minister can use this command!");
            return true;
        }

        if (roleManager.isCooldownActive(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Your ability is on cooldown!");
            return true;
        }

        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.sendMessage(ChatColor.YELLOW + "You have healed yourself and a shield appears for 20 seconds!");

        // Shield disappears after 20 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                // Handle shield removal logic (visual or item effect)
            }
        }.runTaskLater(plugin, 20L * 20);  // 20 seconds delay

        roleManager.setCooldown(player.getUniqueId(), 60000L);  // 1-minute cooldown
        return true;
    }
}