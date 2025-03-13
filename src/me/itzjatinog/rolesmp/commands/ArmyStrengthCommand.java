package me.itzjatinog.rolesmp.commands;

import me.itzjatinog.rolesmp.RoleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ArmyStrengthCommand implements CommandExecutor {
    private final RoleManager roleManager;

    public ArmyStrengthCommand(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!roleManager.getRole(player).equals("Army")) {
            player.sendMessage(ChatColor.RED + "Only the Army can use this command!");
            return true;
        }

        if (roleManager.isCooldownActive(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Your ability is on cooldown!");
            return true;
        }

        // Apply Strength II for 1 minute
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 60, 1));

        // Apply Special Armor (Protection 6) temporarily
        roleManager.giveArmyGear(player);

        player.sendMessage(ChatColor.DARK_RED + "You activated Army Strength! You have Strength II and special armor for 1 minute!");

        // Set cooldown for 1.5 minutes (90 seconds)
        roleManager.setCooldown(player.getUniqueId(), 90000L);

        return true;
    }
}