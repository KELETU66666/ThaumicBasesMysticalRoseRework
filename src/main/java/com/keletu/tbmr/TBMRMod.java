package com.keletu.tbmr;

import com.keletu.tbmr.items.TBMRItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;


@Mod(modid = TBMRMod.MODID, name = TBMRMod.NAME, version = TBMRMod.VERSION, acceptedMinecraftVersions = TBMRMod.MC_VERSION,
dependencies="required-after:baubles@[1.5.2, ); required-after:thaumcraft@[6.1.BETA26]; required-after:mixinbooter@[4.2, ); required-after:oldresearch; required-after:thaumicbases")
public class TBMRMod
{
    public static final String MODID = "tbmr";
    public static final String NAME = "Thaumic Bases Mystical Rose Rework";
    public static final String VERSION = "0.0.1";
    public static final String MC_VERSION = "[1.12.2]";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        ThaumcraftApi.registerObjectTag(TBMRItems.air_fragment.getDefaultInstance(), new AspectList().add(Aspect.AIR, 6).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(TBMRItems.fire_fragment.getDefaultInstance(), new AspectList().add(Aspect.FIRE, 6).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(TBMRItems.water_fragment.getDefaultInstance(), new AspectList().add(Aspect.WATER, 6).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(TBMRItems.earth_fragment.getDefaultInstance(), new AspectList().add(Aspect.EARTH, 6).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(TBMRItems.order_fragment.getDefaultInstance(), new AspectList().add(Aspect.ORDER, 6).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(TBMRItems.entropy_fragment.getDefaultInstance(), new AspectList().add(Aspect.ENTROPY, 6).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(TBMRItems.flux_fragment.getDefaultInstance(), new AspectList().add(Aspect.FLUX, 6).add(Aspect.PLANT, 1));
        ThaumcraftApi.registerObjectTag(TBMRItems.mixed_fragment.getDefaultInstance(), new AspectList().add(Aspect.AIR, 6).add(Aspect.FIRE, 6).add(Aspect.WATER, 6).add(Aspect.EARTH, 6).add(Aspect.ORDER, 6).add(Aspect.ENTROPY, 6).add(Aspect.PLANT, 1));
    }
}
