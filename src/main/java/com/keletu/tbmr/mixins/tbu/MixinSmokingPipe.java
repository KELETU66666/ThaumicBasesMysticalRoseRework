package com.keletu.tbmr.mixins.tbu;

import com.rumaruka.thaumicbases.common.item.TBTobacco;
import com.rumaruka.thaumicbases.init.TBItems;
import com.wonginnovations.oldresearch.OldResearch;
import com.wonginnovations.oldresearch.common.lib.network.PacketAspectPool;
import com.wonginnovations.oldresearch.common.lib.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.capabilities.IPlayerWarp;

@Mixin(TBTobacco.class)
public abstract class MixinSmokingPipe {

    @Redirect(method = "performTobaccoEffect", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;", ordinal = 4))
    public Item mixinPerformTobaccoRemoveEffect(){
        return null;
    }

    @Inject(method = "performTobaccoEffect", at = @At(value = "TAIL"), remap = false)
    public void mixinPerformTobaccoAddEffect(EntityPlayer smoker, ItemStack tobbaco, boolean isSilverwood, CallbackInfo ci){
        if (tobbaco.getItem() == TBItems.tobacco_knowledge) {
            if (!smoker.world.isRemote) {
                for(Aspect a : Aspect.getPrimalAspects()) {
                    OldResearch.proxy.playerKnowledge.addAspectPool(smoker.getGameProfile().getName(), a, (short) (isSilverwood ? 2 : 1));
                    PacketHandler.INSTANCE.sendTo(new PacketAspectPool(a.getTag(), (short) (isSilverwood ? 2 : 1), OldResearch.proxy.playerKnowledge.getAspectPoolFor(smoker.getGameProfile().getName(), a)), (EntityPlayerMP)smoker);
                }
                if (isSilverwood){
                    smoker.addExperience(1);
                }
                else {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, 5, IPlayerWarp.EnumWarpType.TEMPORARY);
                    ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, 1, IPlayerWarp.EnumWarpType.NORMAL);
                }
            }
        }
    }
}
