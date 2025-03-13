package me.itzjatinog.rolesmp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RoleManager {
    private final HashMap<UUID, String> playerRoles = new HashMap<>();
    private final List<String> roles = Arrays.asList("President", "Prime Minister", "Army", "Police", "People");
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();
    
    public void assignRandomRole(Player player) {
        String randomRole = roles.get(new Random().nextInt(roles.size()));
        playerRoles.put(player.getUniqueId(), randomRole);
        player.sendMessage(ChatColor.GREEN + "You have been assigned the role: " + ChatColor.AQUA + randomRole);
        applyRoleAbilities(player, randomRole);
    }

    public String getRole(Player player) {
        return playerRoles.getOrDefault(player.getUniqueId(), "None");
    }

    public void applyRoleAbilities(Player player, String role) {
        switch (role) {
            case "President":
                player.sendMessage(ChatColor.GOLD + "As President, you can teleport with /teleporthere!");
                break;
            case "Prime Minister":
                player.sendMessage(ChatColor.YELLOW + "As Prime Minister, you can heal and activate shield!");
                break;
            case "Army":
                player.sendMessage(ChatColor.RED + "As Army, you have received special armor and Strength ability!");
                giveArmyGear(player);
                break;
            case "Police":
                player.sendMessage(ChatColor.BLUE + "As Police, you can summon cobwebs around you with /summoncobwebs!");
                break;
            case "People":
                player.sendMessage(ChatColor.GRAY + "You are a normal citizen with no special abilities.");
                break;
        }
    }

    private void giveArmyGear(Player player) {
        player.getInventory().addItem(createItem(Material.IRON_SWORD, "Army Sword"));
        player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
    }

    private ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GREEN + name);
            item.setItemMeta(meta);
        }
        return item;
    }

    public boolean isCooldownActive(UUID playerUUID) {
        return cooldowns.containsKey(playerUUID) && cooldowns.get(playerUUID) > System.currentTimeMillis();
    }

    public void setCooldown(UUID playerUUID, long cooldownTime) {
        cooldowns.put(playerUUID, System.currentTimeMillis() + cooldownTime);
    }

    public void resetCooldown(UUID playerUUID) {
        cooldowns.remove(playerUUID);
    }
}