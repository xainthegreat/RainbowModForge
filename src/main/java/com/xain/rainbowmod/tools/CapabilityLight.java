package com.xain.rainbowmod.tools;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityLight {

    @CapabilityInject(LightStorage.class)
    public static Capability<LightStorage> LIGHT = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(LightStorage.class);
    }

}
