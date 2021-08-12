package com.xain.rainbowmod;

import com.xain.rainbowmod.setup.ClientSetup;
import com.xain.rainbowmod.setup.Registration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RainbowMain.MODID)
public class RainbowMain
{
    public static final String MODID = "rainbowmod";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public RainbowMain() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(ClientSetup::setup);

        MinecraftForge.EVENT_BUS.register(this);

        Registration.init();
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

}
