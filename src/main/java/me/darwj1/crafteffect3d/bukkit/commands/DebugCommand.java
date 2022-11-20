package me.darwj1.crafteffect3d.bukkit.commands;

import me.darwj1.crafteffect3d.math.VectorLine;
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

        Particle.DustOptions dustOptions = new Particle.DustOptions(
                Color.fromRGB(255, 255, 255), 1.0F);

        VectorLine edge1 = new VectorLine(
                player.getLocation().add(2, -2, 2),
                player.getLocation().add(2, 2, 2)
        );
        VectorLine edge2 = new VectorLine(
                player.getLocation().add(2, 2, 2),
                player.getLocation().add(2, 2, -2)
        );
        VectorLine edge3 = new VectorLine(
                player.getLocation().add(2, 2, -2),
                player.getLocation().add(2, -2, -2)
        );
        VectorLine edge4 = new VectorLine(
                player.getLocation().add(2, -2, -2),
                player.getLocation().add(2, -2, 2)
        );

        edge1.yRotateAroundPoint(player.getLocation().toVector(),
                (float) Math.toRadians(player.getLocation().getYaw()));
        edge2.yRotateAroundPoint(player.getLocation().toVector(),
                (float) Math.toRadians(player.getLocation().getYaw()));
        edge3.yRotateAroundPoint(player.getLocation().toVector(),
                (float) Math.toRadians(player.getLocation().getYaw()));
        edge4.yRotateAroundPoint(player.getLocation().toVector(),
                (float) Math.toRadians(player.getLocation().getYaw()));

        for (Vector point : edge1.pointsBetween(0.25f)) {
            world.spawnParticle(Particle.REDSTONE, point.toLocation(world), 1, dustOptions);
        }
        for (Vector point : edge2.pointsBetween(0.25f)) {
            world.spawnParticle(Particle.REDSTONE, point.toLocation(world), 1, dustOptions);
        }
        for (Vector point : edge3.pointsBetween(0.25f)) {
            world.spawnParticle(Particle.REDSTONE, point.toLocation(world), 1, dustOptions);
        }
        for (Vector point : edge4.pointsBetween(0.25f)) {
            world.spawnParticle(Particle.REDSTONE, point.toLocation(world), 1, dustOptions);
        }

        player.sendMessage("okk");

        return true;
    }

}
