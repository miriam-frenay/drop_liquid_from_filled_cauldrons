package nl.followyourdreams.drop_liquid_from_filled_cauldrons;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Levelled;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PlaceLiquidOnBreakingCauldron implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        BlockState state = block.getState();
        World world = state.getWorld();
        switch (state.getType()) {
            case LAVA_CAULDRON:
                onBreakLavaCauldron(event, block, state, world);
                break;
            case WATER_CAULDRON:
                onBreakWaterCauldron(event, block, state, world);
                break;
        }
    }

    private void onBreakLavaCauldron(BlockBreakEvent event, Block block, BlockState state, World world) {
        dropCauldronNaturally(event, state, world);
        block.setType(Material.LAVA);
    }

    private void onBreakWaterCauldron(BlockBreakEvent event, Block block, BlockState state, World world) {
        if (world.getEnvironment() != World.Environment.NETHER) {
            Levelled waterCauldron = (Levelled) state.getBlockData();
            if (waterCauldron.getLevel() == waterCauldron.getMaximumLevel()) {
                dropCauldronNaturally(event, state, world);
                block.setType(Material.WATER);
            }
        }
    }

    private void dropCauldronNaturally(BlockBreakEvent event, BlockState state, World world) {
        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
        for (ItemStack stack : state.getDrops(tool)) {
            world.dropItemNaturally(state.getLocation(), stack);
        }
        event.setDropItems(false);
    }
}
