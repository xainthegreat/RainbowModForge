package com.xain.rainbowmod.setup;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.blocks.*;
import com.xain.rainbowmod.items.ExampleAdvancedItem;
import com.xain.rainbowmod.items.ExampleItem;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
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
            () -> new ExampleItem(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<ExampleAdvancedItem> EXAMPLE_ADVANCED_ITEM = ITEMS.register("example_advanced_item",
            () -> new ExampleAdvancedItem(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));

    //---------------------------dye items
    public static final RegistryObject<Item> RED_DROPLET = ITEMS.register("red_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> ORANGE_DROPLET = ITEMS.register("orange_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> MAGENTA_DROPLET = ITEMS.register("magenta_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> YELLOW_DROPLET = ITEMS.register("yellow_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> LIME_DROPLET = ITEMS.register("lime_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> PINK_DROPLET = ITEMS.register("pink_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> GRAY_DROPLET = ITEMS.register("gray_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> CYAN_DROPLET = ITEMS.register("cyan_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> PURPLE_DROPLET = ITEMS.register("purple_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> BLUE_DROPLET = ITEMS.register("blue_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> BROWN_DROPLET = ITEMS.register("brown_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> GREEN_DROPLET = ITEMS.register("green_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> LIGHT_BLUE_DROPLET = ITEMS.register("light_blue_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> LIGHT_GRAY_DROPLET = ITEMS.register("light_gray_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> WHITE_DROPLET = ITEMS.register("white_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> BLACK_DROPLET = ITEMS.register("black_droplet",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));

    //---------------------prisms
    public static final RegistryObject<Item> PRISM = ITEMS.register("prism",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));

    //---------------------lenses
    public static final RegistryObject<Item> CLEAR_LENS = ITEMS.register("clear_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> RED_LENS = ITEMS.register("red_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> ORANGE_LENS = ITEMS.register("orange_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> MAGENTA_LENS = ITEMS.register("magenta_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> YELLOW_LENS = ITEMS.register("yellow_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> LIME_LENS = ITEMS.register("lime_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> PINK_LENS = ITEMS.register("pink_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> GRAY_LENS = ITEMS.register("gray_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> CYAN_LENS = ITEMS.register("cyan_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> PURPLE_LENS = ITEMS.register("purple_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> BLUE_LENS = ITEMS.register("blue_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> BROWN_LENS = ITEMS.register("brown_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> GREEN_LENS = ITEMS.register("green_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> LIGHT_BLUE_LENS = ITEMS.register("light_blue_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> LIGHT_GRAY_LENS = ITEMS.register("light_gray_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
//    public static final RegistryObject<Item> WHITE_LENS = ITEMS.register("white_lens",
//            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<Item> BLACK_LENS = ITEMS.register("black_lens",
            () -> new Item(new Item.Properties().tab(ModSetup.TAB_RAINBOW)));

    /*--------------------------------------------------BLOCKS--------------------------------------------------------*/
    public static final RegistryObject<ExampleGenerator> EXAMPLE_GENERATOR = BLOCKS.register("example_generator", ExampleGenerator::new);
    public static final RegistryObject<Item> EXAMPLE_GENERATOR_ITEM = ITEMS.register("example_generator",
            () -> new BlockItem(EXAMPLE_GENERATOR.get(), new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<BlockEntityType<ExampleGeneratorEntity>> EXAMPLE_GENERATOR_ENTITY = BLOCKENTITIES.register(
            "example_generator",
            () -> BlockEntityType.Builder.of(ExampleGeneratorEntity::new, EXAMPLE_GENERATOR.get()).build(null));
    public static final RegistryObject<MenuType<ExampleGeneratorContainer>> EXAMPLE_GENERATOR_CONTAINER = CONTAINERS.register(
            "example_generator",
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return new ExampleGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<DemoBlock> DEMO_BLOCK = BLOCKS.register("demo_block", DemoBlock::new);
    public static final RegistryObject<Item> DEMO_BLOCK_ITEM = ITEMS.register("demo_block",
            () -> new BlockItem(DEMO_BLOCK.get(), new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<BlockEntityType<DemoBlockEntity>> DEMO_BLOCK_ENTITY = BLOCKENTITIES.register(
            "demo_block",
            () -> BlockEntityType.Builder.of(DemoBlockEntity::new, DEMO_BLOCK.get()).build(null));

    public static final RegistryObject<SolarTest> SOLAR_TEST = BLOCKS.register("solar_test", SolarTest::new);
    public static final RegistryObject<Item> SOLAR_TEST_ITEM = ITEMS.register("solar_test",
            () -> new BlockItem(SOLAR_TEST.get(), new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<BlockEntityType<SolarTestEntity>> SOLAR_TEST_ENTITY = BLOCKENTITIES.register("solar_test",
            () -> BlockEntityType.Builder.of(SolarTestEntity::new, SOLAR_TEST.get()).build(null));
    public static final RegistryObject<MenuType<SolarTestContainer>> SOLAR_TEST_CONTAINER = CONTAINERS.register(
            "solar_test",
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return new SolarTestContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<PrimaryConverter> PRIMARY_CONVERTER = BLOCKS.register("primary_converter", PrimaryConverter::new);
    public static final RegistryObject<Item> PRIMARY_CONVERTER_ITEM = ITEMS.register("primary_converter",
            () -> new BlockItem(PRIMARY_CONVERTER.get(), new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<BlockEntityType<PrimaryConverterEntity>> PRIMARY_CONVERTER_ENTITY = BLOCKENTITIES.register(
            "primary_converter",
            () -> BlockEntityType.Builder.of(PrimaryConverterEntity::new, PRIMARY_CONVERTER.get()).build(null));
    public static final RegistryObject<MenuType<PrimaryConverterContainer>> PRIMARY_CONVERTER_CONTAINER = CONTAINERS.register(
            "primary_converter",
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return new PrimaryConverterContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<SolarFurnace> SOLAR_FURNACE = BLOCKS.register("solar_furnace", SolarFurnace::new);
    public static final RegistryObject<Item> SOLAR_FURNACE_ITEM = ITEMS.register("solar_furnace",
            () -> new BlockItem(SOLAR_FURNACE.get(), new Item.Properties().tab(ModSetup.TAB_RAINBOW)));
    public static final RegistryObject<BlockEntityType<SolarFurnaceEntity>> SOLAR_FURNACE_ENTITY = BLOCKENTITIES.register(
            "solar_furnace",
            () -> BlockEntityType.Builder.of(SolarFurnaceEntity::new, SOLAR_FURNACE.get()).build(null));
    public static final RegistryObject<MenuType<SolarFurnaceContainer>> SOLAR_FURNACE_CONTAINER = CONTAINERS.register(
            "solar_furnace", //todo create a png
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return new SolarFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

//    public static final Tags.IOptionalNamedTag<Block> MYSTERIOUS_ORE = BlockTags.createOptional(new ResourceLocation(RainbowMain.MODID, "mysterious_ore"));
    public static final Tags.IOptionalNamedTag<Item> LENSES = ItemTags.createOptional(new ResourceLocation(RainbowMain.MODID, "lenses"));

}
