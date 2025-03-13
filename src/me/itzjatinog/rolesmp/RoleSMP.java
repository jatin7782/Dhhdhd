package me.itzjatinog.rolesmp;

import me.itzjatinog.rolesmp.commands.*;
import me.itzjatinog.rolesmp.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class RoleSMP extends JavaPlugin {
    private RoleManager roleManager;

    @Override
    public void onEnable() {
        roleManager = new RoleManager();

        // Register Commands
        getCommand("giverole").setExecutor(new GiveRoleCommand(roleManager));
        getCommand("heal").setExecutor(new PrimeMinisterHealCommand(roleManager));
        getCommand("teleporthere").setExecutor(new PresidentTeleportCommand(roleManager));
        getCommand("armystrength").setExecutor(new ArmyStrengthCommand(roleManager));
        getCommand("summoncobwebs").setExecutor(new PoliceCobwebCommand(roleManager));

        // Register Event Listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(roleManager), this);
        getServer().getPluginManager().registerEvents(new RoleTransferListener(roleManager), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("RoleSMP has been disabled.");
    }
}