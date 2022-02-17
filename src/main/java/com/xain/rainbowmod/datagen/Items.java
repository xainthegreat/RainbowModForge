package com.xain.rainbowmod.datagen;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.xain.rainbowmod.setup.ClientSetup.DISTANCE_PROPERTY;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RainbowMain.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        old way to register a single texture
//        singleTexture(
//                Registration.EXAMPLE_ITEM.get().getRegistryName().getPath(),
//                new ResourceLocation(("item/handheld")),
//                "layer0",
//                new ResourceLocation(RainbowMain.MODID, "items/example_item"));

        //TODO remove this?
        getBuilder(Registration.EXAMPLE_ITEM.get().getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "items/example_item0")
                .override().predicate(DISTANCE_PROPERTY, 0).model(createExampleItemModel(0)).end()
                .override().predicate(DISTANCE_PROPERTY, 1).model(createExampleItemModel(1)).end()
                .override().predicate(DISTANCE_PROPERTY, 2).model(createExampleItemModel(2)).end()
                .override().predicate(DISTANCE_PROPERTY, 3).model(createExampleItemModel(3)).end();

        //---------------------------dye items
        singleTexture(Registration.RED_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/red_droplet"));
        singleTexture(Registration.ORANGE_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/orange_droplet"));
        singleTexture(Registration.MAGENTA_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/magenta_droplet"));
        singleTexture(Registration.YELLOW_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/yellow_droplet"));
        singleTexture(Registration.LIME_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/lime_droplet"));
        singleTexture(Registration.PINK_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/pink_droplet"));
        singleTexture(Registration.GRAY_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/gray_droplet"));
        singleTexture(Registration.CYAN_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/cyan_droplet"));
        singleTexture(Registration.PURPLE_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/purple_droplet"));
        singleTexture(Registration.BLUE_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/blue_droplet"));
        singleTexture(Registration.BROWN_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/brown_droplet"));
        singleTexture(Registration.GREEN_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/green_droplet"));
        singleTexture(Registration.LIGHT_BLUE_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/light_blue_droplet"));
        singleTexture(Registration.LIGHT_GRAY_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/light_gray_droplet"));
        singleTexture(Registration.WHITE_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/white_droplet"));
        singleTexture(Registration.BLACK_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/black_droplet"));

        singleTexture(Registration.PRISM.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/prism"));

        //---------------------lenses
        singleTexture(Registration.CLEAR_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/clear_lens"));
        singleTexture(Registration.RED_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/red_lens"));
        singleTexture(Registration.ORANGE_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/orange_lens"));
        singleTexture(Registration.MAGENTA_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/magenta_lens"));
        singleTexture(Registration.YELLOW_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/yellow_lens"));
        singleTexture(Registration.LIME_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/lime_lens"));
        singleTexture(Registration.PINK_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/pink_lens"));
        singleTexture(Registration.GRAY_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/gray_lens"));
        singleTexture(Registration.CYAN_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/cyan_lens"));
        singleTexture(Registration.PURPLE_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/purple_lens"));
        singleTexture(Registration.BLUE_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/blue_lens"));
        singleTexture(Registration.BROWN_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/brown_lens"));
        singleTexture(Registration.GREEN_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/green_lens"));
        singleTexture(Registration.LIGHT_BLUE_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/light_blue_lens"));
        singleTexture(Registration.LIGHT_GRAY_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/light_gray_lens"));

        //TODO remove this?
        withExistingParent(Registration.EXAMPLE_GENERATOR_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/example_generator"));


        //TODO remove this?
        withExistingParent(Registration.DEMO_BLOCK_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/demo_block"));

        //TODO remove this?
        withExistingParent(Registration.SOLAR_TEST_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/solar_test"));

        withExistingParent(Registration.PRIMARY_CONVERTER_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/primary_converter"));

        withExistingParent(Registration.SOLAR_FURNACE_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/solar_furnace"));
    }

    private ItemModelBuilder createExampleItemModel(int suffix) {
        return getBuilder("example_item" + suffix).parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "items/example_item" + suffix);
    }
}
