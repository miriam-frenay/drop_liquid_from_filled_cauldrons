package nl.followyourdreams.drop_liquid_from_filled_cauldrons;

import org.bukkit.plugin.java.JavaPlugin;

public final class DropLiquidFromFilledCauldrons extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlaceLiquidOnBreakingCauldron(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
