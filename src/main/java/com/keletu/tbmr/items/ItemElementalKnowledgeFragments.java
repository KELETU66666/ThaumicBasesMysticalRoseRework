package com.keletu.tbmr.items;

import com.rumaruka.thaumicbases.client.creativetabs.TBCreativeTabs;
import com.wonginnovations.oldresearch.OldResearch;
import com.wonginnovations.oldresearch.common.lib.network.PacketAspectPool;
import com.wonginnovations.oldresearch.common.lib.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.capabilities.IPlayerWarp;

public class ItemElementalKnowledgeFragments extends Item {
    private Aspect aspect;
    public ItemElementalKnowledgeFragments(Aspect aspect) {
        this.setMaxStackSize(64);
        this.setHasSubtypes(false);
        this.setMaxDamage(0);
        this.setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);

        this.aspect = aspect;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if(!player.capabilities.isCreativeMode) {
            stack.setCount(stack.getCount()-1);
        }

        if(!world.isRemote) {
            if(this == TBMRItems.mixed_fragment){
                for(Aspect a : Aspect.getPrimalAspects()) {
                    OldResearch.proxy.playerKnowledge.addAspectPool(player.getGameProfile().getName(), a, 2);
                    PacketHandler.INSTANCE.sendTo(new PacketAspectPool(a.getTag(), 2, OldResearch.proxy.playerKnowledge.getAspectPoolFor(player.getGameProfile().getName(), a)), (EntityPlayerMP)player);
                }
            }
            else if (this == TBMRItems.flux_fragment) {
                for(Aspect a : Aspect.getPrimalAspects()) {
                    OldResearch.proxy.playerKnowledge.addAspectPool(player.getGameProfile().getName(), a, 1);
                    PacketHandler.INSTANCE.sendTo(new PacketAspectPool(a.getTag(), 1, OldResearch.proxy.playerKnowledge.getAspectPoolFor(player.getGameProfile().getName(), a)), (EntityPlayerMP)player);
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, IPlayerWarp.EnumWarpType.TEMPORARY);
                }
            }else {
                OldResearch.proxy.playerKnowledge.addAspectPool(player.getGameProfile().getName(), aspect, 8);
                PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), 8, OldResearch.proxy.playerKnowledge.getAspectPoolFor(player.getGameProfile().getName(), aspect)), (EntityPlayerMP) player);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
