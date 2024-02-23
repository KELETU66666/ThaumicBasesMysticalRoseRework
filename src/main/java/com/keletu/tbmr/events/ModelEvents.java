package com.keletu.tbmr.events;

import com.keletu.tbmr.items.TBMRItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT)
public class ModelEvents {
    @SubscribeEvent
    public static void regModels(ModelRegistryEvent event) {
        defaultModel(TBMRItems.air_fragment);
        defaultModel(TBMRItems.fire_fragment);
        defaultModel(TBMRItems.water_fragment);
        defaultModel(TBMRItems.earth_fragment);
        defaultModel(TBMRItems.entropy_fragment);
        defaultModel(TBMRItems.order_fragment);
        defaultModel(TBMRItems.flux_fragment);
        defaultModel(TBMRItems.mixed_fragment);
    }

    static void defaultModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
