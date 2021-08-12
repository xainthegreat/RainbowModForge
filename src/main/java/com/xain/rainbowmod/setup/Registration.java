package com.xain.rainbowmod.setup;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.blocks.*;
import com.xain.rainbowmod.items.ExampleItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RainbowMain.MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RainbowMain.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, RainbowMain.MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RainbowMain.MODID);



    public static void init () {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);
        BLOCKENTITIES.register(bus);
        CONTAINERS.register(bus);
    }


    /*---------------------------------------------------ITEMS--------------------------------------------------------*/
    public static final RegistryObject<ExampleItem> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new ExampleItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    //dye items
    public static final RegistryObject<Item> RED_DROPLET = ITEMS.register("red_droplet",
            () -> new Item(new Item.Properties()));

    //prisms
    public static final RegistryObject<Item> PRISM = ITEMS.register("prism",
            () -> new Item(new Item.Properties()));

    //lenses
    public static final RegistryObject<Item> LENS = ITEMS.register("lens",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_LENS = ITEMS.register("red_lens",
            () -> new Item(new Item.Properties()));

    /*--------------------------------------------------BLOCKS--------------------------------------------------------*/
    public static final RegistryObject<ExampleGenerator> EXAMPLE_GENERATOR = BLOCKS.register("example_generator", ExampleGenerator::new);
    public static final RegistryObject<Item> EXAMPLE_GENERATOR_ITEM = ITEMS.register("example_generator",
            () -> new BlockItem(EXAMPLE_GENERATOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockEntityType<ExampleGeneratorEntity>> EXAMPLE_GENERATOR_ENTITY = BLOCKENTITIES.register(
            "example_generator",
            () -> BlockEntityType.Builder.of(ExampleGeneratorEntity::new, EXAMPLE_GENERATOR.get()).build(null));
    public static final RegistryObject<MenuType<ExampleGeneratorContainer>> EXAMPLE_GENERATOR_CONTAINER = CONTAINERS.register(
            "example_generator",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return new ExampleGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<DemoBlock> DEMO_BLOCK = BLOCKS.register("demo_block", DemoBlock::new);
    public static final RegistryObject<Item> DEMO_BLOCK_ITEM = ITEMS.register("demo_block",
            () -> new BlockItem(DEMO_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockEntityType<DemoBlockEntity>> DEMO_BLOCK_ENTITY = BLOCKENTITIES.register(
            "demo_block",
            () -> BlockEntityType.Builder.of(DemoBlockEntity::new, DEMO_BLOCK.get()).build(null));

    public static final RegistryObject<SolarTest> SOLAR_TEST = BLOCKS.register("solar_test", SolarTest::new);
    public static final RegistryObject<Item> SOLAR_TEST_ITEM = ITEMS.register("solar_test",
            () -> new BlockItem(SOLAR_TEST.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockEntityType<SolarTestEntity>> SOLAR_TEST_ENTITY = BLOCKENTITIES.register("solar_test",
            () -> BlockEntityType.Builder.of(SolarTestEntity::new, SOLAR_TEST.get()).build(null));
    public static final RegistryObject<MenuType<SolarTestContainer>> SOLAR_TEST_CONTAINER = CONTAINERS.register(
            "solar_test",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return new SolarTestContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<PrimaryConverter> PRIMARY_CONVERTER = BLOCKS.register("primary_converter", PrimaryConverter::new);
    public static final RegistryObject<Item> PRIMARY_CONVERTER_ITEM = ITEMS.register("primary_converter",
            () -> new BlockItem(PRIMARY_CONVERTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockEntityType<PrimaryConverterEntity>> PRIMARY_CONVERTER_ENTITY = BLOCKENTITIES.register(
            "primary_converter",
            () -> BlockEntityType.Builder.of(PrimaryConverterEntity::new, PRIMARY_CONVERTER.get()).build(null));
    public static final RegistryObject<MenuType<PrimaryConverterContainer>> PRIMARY_CONVERTER_CONTAINER = CONTAINERS.register(
            "primary_converter",
            () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return new PrimaryConverterContainer(windowId, world, pos, inv, inv.player);
            }));

}
