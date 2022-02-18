package com.xain.rainbowmod.setup;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.tools.LightStorage;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RainbowMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final CreativeModeTab TAB_RAINBOW = new CreativeModeTab("rainbowmod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.PRISM.get());
        }
    };

    @SubscribeEvent
    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(LightStorage.class);
    }
}
