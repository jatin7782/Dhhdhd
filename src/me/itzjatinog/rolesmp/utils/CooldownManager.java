package me.itzjatinog.rolesmp.utils;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {
    // A map to store the cooldowns for each player using their UUID as the key
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    /**
     * Check if the player has an active cooldown.
     * @param playerUUID The UUID of the player.
     * @return true if the player is on cooldown, false otherwise.
     */
    public boolean isCooldownActive(UUID playerUUID) {
        return cooldowns.containsKey(playerUUID) && cooldowns.get(playerUUID) > System.currentTimeMillis();
    }

    /**
     * Set a cooldown for the player.
     * @param playerUUID The UUID of the player.
     * @param cooldownTime The cooldown time in milliseconds.
     */
    public void setCooldown(UUID playerUUID, long cooldownTime) {
        cooldowns.put(playerUUID, System.currentTimeMillis() + cooldownTime);
    }

    /**
     * Get the remaining time of the cooldown for the player.
     * @param playerUUID The UUID of the player.
     * @return the remaining cooldown time in milliseconds.
     */
    public long getRemainingCooldown(UUID playerUUID) {
        if (!isCooldownActive(playerUUID)) {
            return 0;
        }
        return cooldowns.get(playerUUID) - System.currentTimeMillis();
    }

    /**
     * Reset the cooldown for the player.
     * @param playerUUID The UUID of the player.
     */
    public void resetCooldown(UUID playerUUID) {
        cooldowns.remove(playerUUID);
    }

    /**
     * Get the time in seconds before the cooldown expires.
     * @param playerUUID The UUID of the player.
     * @return the remaining cooldown in seconds.
     */
    public long getCooldownInSeconds(UUID playerUUID) {
        return getRemainingCooldown(playerUUID) / 1000;
    }
}