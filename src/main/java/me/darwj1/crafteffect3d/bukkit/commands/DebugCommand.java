package me.darwj1.crafteffect3d.bukkit.commands;

import me.darwj1.crafteffect3d.math.VectorLine;
import me.darwj1.crafteffect3d.math.geometry.Triangle;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Please be a player.");
            return true;
        }

        Player player = (Player) sender;
        World world = player.getWorld();

        long time = System.currentTimeMillis();

        Particle.DustOptions dustOptions = new Particle.DustOptions(
                Color.fromRGB(255, 255, 255), 1.0F);

        Triangle t = new Triangle(
                player.getLocation().toVector().add(new Vector(0, 2, 2)),
                player.getLocation().toVector().add(new Vector(0, 0, 2)),
                player.getLocation().toVector().add(new Vector(2, 0, 2)));
        t.xRotateAroundPoint(player.getLocation().toVector(), (float) Math.toRadians(player.getLocation().getPitch()));
        t.yRotateAroundPoint(player.getLocation().toVector(), (float) Math.toRadians(player.getLocation().getYaw()));

        for (Vector point : t.surfacePoints(0.25f)) {
            world.spawnParticle(
                    Particle.REDSTONE,
                    point.toLocation(world),
                    1, dustOptions);
        }

        player.sendMessage("Done in " + ((Long) (System.currentTimeMillis() - time)).toString() + "ms");

        return true;
    }

}
