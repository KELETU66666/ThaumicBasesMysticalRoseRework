package com.keletu.tbmr.mixins.reforbiddenMagic;

import com.wonginnovations.oldresearch.OldResearch;
import com.wonginnovations.oldresearch.common.lib.network.PacketAspectPool;
import com.wonginnovations.oldresearch.common.lib.network.PacketHandler;
import keletu.forbiddenmagicre.compat.botania.flowers.SubTileWhisperweed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.capabilities.IPlayerWarp;
import vazkii.botania.api.subtile.SubTileFunctional;

import java.util.List;

@Mixin(SubTileWhisperweed.class)
public abstract class MixinWhisperWeed extends SubTileFunctional {

    @Shadow(remap = false)
    private static int cost;

    @Shadow(remap = false)
    private static int range;
    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    public void onUpdate(){
        super.onUpdate();

        if(redstoneSignal > 0)
            return;

        if(!supertile.getWorld().isRemote && mana >= cost && this.ticksExisted % 300 == 0) {
            List<EntityPlayer> players = supertile.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(supertile.getPos().getX() - range, supertile.getPos().getY() - range, supertile.getPos().getZ() - range, supertile.getPos().getX() + range + 1, supertile.getPos().getY() + range + 1, supertile.getPos().getZ() + range + 1));
            if(players.size() > 0) {
                EntityPlayer player = players.get(supertile.getWorld().rand.nextInt(players.size()));
                int amt = 1 + player.world.rand.nextInt(3);
                if(player.world.rand.nextInt(10) < 2){
                    amt += 1 + player.world.rand.nextInt(3);
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1 + player.world.rand.nextInt(5), IPlayerWarp.EnumWarpType.TEMPORARY);
                }

                for(int a = 0; a < amt; ++a) {
                    Aspect aspect;
                    if (player.world.rand.nextInt(25) > 2)
                        aspect = Aspect.getPrimalAspects().get(player.world.rand.nextInt(6));
                    else aspect = Aspect.getCompoundAspects()
                            .get(player.world.rand.nextInt(Aspect.getCompoundAspects().size()));
                    OldResearch.proxy.playerKnowledge.addAspectPool(player.getGameProfile().getName(), aspect, (short) 1);
                    PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), (short) 1, OldResearch.proxy.playerKnowledge.getAspectPoolFor(player.getGameProfile().getName(), aspect)), (EntityPlayerMP) player);
                }
                mana -= cost;
            }

        }
    }
}
