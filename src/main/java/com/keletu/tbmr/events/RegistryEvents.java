package com.keletu.tbmr.events;

import com.keletu.tbmr.items.TBMRItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryEvents {
    @SubscribeEvent
    public static void regItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(TBMRItems.air_fragment);
        event.getRegistry().registerAll(TBMRItems.fire_fragment);
        event.getRegistry().registerAll(TBMRItems.water_fragment);
        event.getRegistry().registerAll(TBMRItems.earth_fragment);
        event.getRegistry().registerAll(TBMRItems.entropy_fragment);
        event.getRegistry().registerAll(TBMRItems.order_fragment);
        event.getRegistry().registerAll(TBMRItems.flux_fragment);
        event.getRegistry().registerAll(TBMRItems.mixed_fragment);
    }
}
