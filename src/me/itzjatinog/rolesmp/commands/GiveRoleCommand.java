package me.itzjatinog.rolesmp.commands;

import me.itzjatinog.rolesmp.RoleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveRoleCommand implements CommandExecutor {
    private final RoleManager roleManager;

    public GiveRoleCommand(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        roleManager.assignRandomRole(player);
        return true;
    }
}