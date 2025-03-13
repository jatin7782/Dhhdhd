package me.itzjatinog.rolesmp.commands;

import me.itzjatinog.rolesmp.RoleManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.Arrays;
import java.util.List;

public class PoliceCobwebCommand implements CommandExecutor {
    private final RoleManager roleManager;
    private final List<int[]> cobwebPattern = Arrays.asList(
            new int[]{0, 0}, new int[]{1, 0}, new int[]{-1, 0},
            new int[]{0, 1}, new int[]{0, -1}, new int[]{1, 1},
            new int[]{-1, -1}, new int[]{1, -1}, new int[]{-1, 1}
    );

    public PoliceCobwebCommand(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        if (!roleManager.getRole(player).equals("Police")) {
            player.sendMessage(ChatColor.RED + "Only Police can use this command!");
            return true;
        }

        if (roleManager.isCooldownActive(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Your ability is on cooldown!");
            return true;
        }

        Location playerLocation = player.getLocation();
        
        // Place cobwebs around the player
        for (int[] offset : cobwebPattern) {
            Location blockLocation = playerLocation.clone().add(offset[0], 0, offset[1]);
            Block block = blockLocation.getBlock();
            if (block.getType() == Material.AIR) {
                block.setType(Material.COBWEB);
            }
        }

        player.sendMessage(ChatColor.BLUE + "You have summoned cobwebs around you!");

        // Set cooldown for 2 minutes (120 seconds)
        roleManager.setCooldown(player.getUniqueId(), 120000L);

        return true;
    }
}