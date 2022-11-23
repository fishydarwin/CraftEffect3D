package me.darwj1.crafteffect3d.bukkit.commands;

import me.darwj1.crafteffect3d.CraftEffect3D;
import me.darwj1.crafteffect3d.math.VectorLine;
import me.darwj1.crafteffect3d.math.geometry.Triangle;
import me.darwj1.crafteffect3d.math.geometry.shapes.ObjShape;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DebugCommand implements CommandExecutor {

    private CraftEffect3D plugin;
    public DebugCommand(CraftEffect3D plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Please be a player.");
            return true;
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {

                Player player = (Player) sender;
                World world = player.getWorld();

                Particle.DustOptions dustOptions = new Particle.DustOptions(
                        Color.fromRGB(255, 255, 255), 1.0F);

                try {
                    ObjShape shape = new ObjShape(new File(args[0]));
                    float precision = Float.parseFloat(args[1]);
                    float scale = Float.parseFloat(args[2]);

                    long time = System.currentTimeMillis();

                    shape.xyzScaleAroundPoint(new Vector(0, 0, 0), scale, scale, scale);
                    shape.yRotateAroundPoint(new Vector(0, 0, 0),
                            (float) Math.toRadians(player.getLocation().getYaw()));

                    for (Triangle t : shape.surfaceTriangles()) {
                        for (Vector point : t.surfacePoints(precision)) {
                            world.spawnParticle(
                                    Particle.REDSTONE,
                                    player.getLocation().add(point.toLocation(world)),
                                    1, dustOptions);
                        }
                    }

                    player.sendMessage("Done in " + ((Long) (System.currentTimeMillis() - time)).toString() + "ms");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        return true;
    }

}
