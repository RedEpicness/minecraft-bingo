package com.extremelyd1.world;

import com.extremelyd1.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;

public class WorldManager {

    /**
     * The game instance
     */
    private final Game game;

    /**
     * The overworld world instance
     */
    private final World world;
    /**
     * The nether world instance
     */
    private final World nether;
    /**
     * The end world instance
     */
    private final World end;

    public WorldManager(Game game) throws IllegalArgumentException {
        this.game = game;

        this.world = Bukkit.getWorld("world");
        this.nether = Bukkit.getWorld("world_nether");
        this.end = Bukkit.getWorld("world_the_end");

        if (this.world == null) {
            throw new IllegalArgumentException("There is no overworld named 'world' loaded, cannot start game");
        }

        initialize();
    }

    /**
     * Initializes the loaded worlds
     * Sets the world border in all dimensions
     */
    private void initialize() {
        world.setAutoSave(false);
        if (nether != null) {
            nether.setAutoSave(false);
            nether.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }
        if (end != null) {
            end.setAutoSave(false);
            end.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }

        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        world.setTime(0);
    }

    /**
     * Resets the gamerules of the overworld to default vanilla behaviour
     */
    public void onGameStart() {
        world.setGameRule(GameRule.DO_MOB_SPAWNING, true);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
    }

    /**
     * Gets the spawn location of the overworld
     * @return The spawn location
     */
    public Location getSpawnLocation() {
        return world.getSpawnLocation();
    }

    public World getWorld() {
        return world;
    }

    public World getNether() {
        return nether;
    }

    public World getEnd() {
        return end;
    }
}
