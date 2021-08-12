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

        getBuilder(Registration.EXAMPLE_ITEM.get().getRegistryName().getPath())
                .parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "items/example_item0")
                .override().predicate(DISTANCE_PROPERTY, 0).model(createExampleItemModel(0)).end()
                .override().predicate(DISTANCE_PROPERTY, 1).model(createExampleItemModel(1)).end()
                .override().predicate(DISTANCE_PROPERTY, 2).model(createExampleItemModel(2)).end()
                .override().predicate(DISTANCE_PROPERTY, 3).model(createExampleItemModel(3)).end();

        // TODO find a way to render this dynamically?
        singleTexture(Registration.RED_DROPLET.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/red_droplet"));

        singleTexture(Registration.PRISM.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",

                new ResourceLocation(RainbowMain.MODID, "items/prism"));

        singleTexture(Registration.LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/lens"));

        singleTexture(Registration.RED_LENS.get().getRegistryName().getPath(),
                new ResourceLocation(("item/generated")),
                "layer0",
                new ResourceLocation(RainbowMain.MODID, "items/red_lens"));

        withExistingParent(Registration.EXAMPLE_GENERATOR_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/example_generator"));


        withExistingParent(Registration.DEMO_BLOCK_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/demo_block"));

        //TODO remove this?
        withExistingParent(Registration.SOLAR_TEST_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/solar_test"));

        withExistingParent(Registration.PRIMARY_CONVERTER_ITEM.get().getRegistryName().getPath(),
                new ResourceLocation(RainbowMain.MODID, "block/primary_converter"));
    }

    private ItemModelBuilder createExampleItemModel(int suffix) {
        return getBuilder("example_item" + suffix).parent(getExistingFile(mcLoc("item/handheld")))
                .texture("layer0", "items/example_item" + suffix);
    }
}
