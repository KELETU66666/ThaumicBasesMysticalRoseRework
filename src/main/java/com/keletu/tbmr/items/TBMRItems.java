package com.keletu.tbmr.items;

import net.minecraft.item.Item;
import thaumcraft.api.aspects.Aspect;


public class TBMRItems {
    public static final Item air_fragment = new ItemElementalKnowledgeFragments(Aspect.AIR).setRegistryName("air_fragment").setTranslationKey("air_fragment");
    public static final Item fire_fragment = new ItemElementalKnowledgeFragments(Aspect.FIRE).setRegistryName("fire_fragment").setTranslationKey("fire_fragment");
    public static final Item water_fragment = new ItemElementalKnowledgeFragments(Aspect.WATER).setRegistryName("water_fragment").setTranslationKey("water_fragment");
    public static final Item earth_fragment = new ItemElementalKnowledgeFragments(Aspect.EARTH).setRegistryName("earth_fragment").setTranslationKey("earth_fragment");
    public static final Item entropy_fragment = new ItemElementalKnowledgeFragments(Aspect.ENTROPY).setRegistryName("entropy_fragment").setTranslationKey("entropy_fragment");
    public static final Item order_fragment = new ItemElementalKnowledgeFragments(Aspect.ORDER).setRegistryName("order_fragment").setTranslationKey("order_fragment");
    public static final Item flux_fragment = new ItemElementalKnowledgeFragments(Aspect.ENTROPY).setRegistryName("flux_fragment").setTranslationKey("flux_fragment");
    public static final Item mixed_fragment = new ItemElementalKnowledgeFragments(Aspect.ORDER).setRegistryName("mixed_fragment").setTranslationKey("mixed_fragment");
}
