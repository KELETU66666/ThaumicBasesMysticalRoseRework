package com.keletu.tbmr.mixins;

import com.keletu.tbmr.items.TBMRItems;
import com.rumaruka.thaumicbases.common.block.BlockknozeSeed;
import com.rumaruka.thaumicbases.init.TBBlocks;
import com.rumaruka.thaumicbases.init.TBItems;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(BlockknozeSeed.class)
public abstract class MixinBlockKnozeSeeds {

    @Shadow(remap = false)
    public PropertyInteger AGE;

    @Shadow(remap = false)
    public int growthStages;

    @Shadow(remap = false)
    public ItemStack dropSeed;

    /**
     * @author keletu
     * @reason just you know why ~~~ just you know ~~~ I don't know why ;)
     */
    @Overwrite(remap = false)
    public List<ItemStack> getDrops(IBlockAccess w, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList();
        if (w instanceof World) {
            World world = (World) w;
            ret.add(new ItemStack(TBItems.knozeseed, 1));
            int metadata = state.getValue(this.AGE);
            if (metadata >= this.growthStages - 1) {
                for (int i = 0; i < 1; ++i) {
                    if (world.rand.nextInt(this.growthStages) <= metadata && this.dropSeed != ItemStack.EMPTY) {
                        for (int j = 0; j < 4 + fortune; ++j) {
                            if (world.rand.nextBoolean() && world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblockfire) {
                                ret.add(TBMRItems.fire_fragment.getDefaultInstance());
                            }

                            if (world.rand.nextBoolean() && world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblockwater) {
                                ret.add(TBMRItems.water_fragment.getDefaultInstance());
                            }

                            if (world.rand.nextBoolean() && world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblockearth) {
                                ret.add(TBMRItems.earth_fragment.getDefaultInstance());
                            }

                            if (world.rand.nextBoolean() && world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblockair) {
                                ret.add(TBMRItems.air_fragment.getDefaultInstance());
                            }

                            if (world.rand.nextBoolean() && world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblockorder) {
                                ret.add(TBMRItems.order_fragment.getDefaultInstance());
                            }

                            if (world.rand.nextBoolean() && world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblockentropy) {
                                ret.add(TBMRItems.entropy_fragment.getDefaultInstance());
                            }

                            if (world.rand.nextBoolean() && world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblocktainted) {
                                ret.add(TBMRItems.flux_fragment.getDefaultInstance());
                            }

                            if (world.getBlockState(pos.down(1)).getBlock() == TBBlocks.crystalblockmixed) {
                                ret.add(TBMRItems.mixed_fragment.getDefaultInstance());
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
