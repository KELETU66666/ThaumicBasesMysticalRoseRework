package com.keletu.tbmr.mixins.tbu;

import com.rumaruka.thaumicbases.common.handlers.EnchatmentHandler;
import com.rumaruka.thaumicbases.init.TBEnchant;
import com.wonginnovations.oldresearch.OldResearch;
import com.wonginnovations.oldresearch.common.lib.network.PacketAspectPool;
import com.wonginnovations.oldresearch.common.lib.network.PacketHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.internal.CommonInternals;

@Mixin(EnchatmentHandler.class)
public class MixinTBEvents {

    /**
     * @author
     * @reason
     */
    @SubscribeEvent
    @Overwrite(remap = false)
    public void onMobDeath(LivingDeathEvent e) {
        if (e.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer attacker = (EntityPlayer)e.getSource().getTrueSource();
            EntityLivingBase receiver = e.getEntityLiving();
            ItemStack mainHund = attacker.getHeldItemMainhand();
            if (!mainHund.isEmpty() && mainHund.getItem() instanceof ItemSword && EnchantmentHelper.getEnchantmentLevel(TBEnchant.elderKnowledge, mainHund) > 0) {
                int enchLevel = EnchantmentHelper.getEnchantmentLevel(TBEnchant.elderKnowledge, mainHund);
                if(attacker.world.rand.nextInt(Math.max(1, 7-enchLevel)) == 0)
                {
                    ThaumcraftApi.EntityTags eTags = null;
                    for(int i = 0; i < CommonInternals.scanEntities.size(); ++i)
                    {
                        ThaumcraftApi.EntityTags tags = CommonInternals.scanEntities.get(i);
                        if(tags != null && EntityList.getEntityString(receiver) != null && EntityList.getEntityString(receiver).equalsIgnoreCase(tags.entityName))
                        {
                            eTags = tags;
                            break;
                        }
                    }
                    if(eTags != null)
                    {
                        AspectList al = eTags.aspects;
                        for(int i = 0; i < al.size(); ++i)
                        {
                            OldResearch.proxy.playerKnowledge.addAspectPool(attacker.getGameProfile().getName(), al.getAspects()[i], 1);
                            PacketHandler.INSTANCE.sendTo(new PacketAspectPool(al.getAspects()[i].getTag(), 1, OldResearch.proxy.playerKnowledge.getAspectPoolFor(attacker.getGameProfile().getName(), al.getAspects()[i])), (EntityPlayerMP)attacker);
                            if(attacker.world.rand.nextBoolean())
                                break;
                        }
                    }
                }
            }
        }
    }
}
