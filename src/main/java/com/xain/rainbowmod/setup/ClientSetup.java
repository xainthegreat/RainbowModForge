package com.xain.rainbowmod.setup;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.blocks.*;
import com.xain.rainbowmod.items.ExampleItem;
import com.xain.rainbowmod.tools.CapabilityLight;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static final ResourceLocation DISTANCE_PROPERTY = new ResourceLocation(RainbowMain.MODID, "distance");

    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityLight.register(); // todo is the right place to init a Capability?

            ItemBlockRenderTypes.setRenderLayer(Registration.DEMO_BLOCK.get(), RenderType.translucent());// makes see-through (add noOcclusion to properties if you want to see other blocks through this one, can use cutout to add items with open air, like dynamos.
            MenuScreens.register(Registration.EXAMPLE_GENERATOR_CONTAINER.get(), ExampleGeneratorScreen::new);
            MenuScreens.register(Registration.SOLAR_TEST_CONTAINER.get(), SolarTestScreen::new);
            MenuScreens.register(Registration.PRIMARY_CONVERTER_CONTAINER.get(), PrimaryConverterScreen::new);
            initTestItemOverrides();
        });
    }

    public static void initTestItemOverrides() {
        ExampleItem item = Registration.EXAMPLE_ITEM.get();
        ItemProperties.register(item, DISTANCE_PROPERTY,
                (stack, level, entity, damage) -> item.getDistance(stack));
    }
}
