package me.darwj1.crafteffect3d;

import me.darwj1.crafteffect3d.bukkit.commands.DebugCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftEffect3D extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("debug").setExecutor(new DebugCommand(this));

    }

}
