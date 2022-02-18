package com.xain.rainbowmod.tools;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CapabilityLight {

    public static Capability<LightStorage> LIGHT_STORAGE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
}
