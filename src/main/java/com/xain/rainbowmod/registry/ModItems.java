package com.xain.rainbowmod.registry;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.items.ExampleItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RainbowMain.MODID);

    public static void registerItems () {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> EXAMPLEITEM = ITEMS.register("exampleitem", () -> new ExampleItem(new Item.Properties()));

}
