package com.xain.rainbowmod.datagen;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModTags {

    public static class Blocks extends BlockTagsProvider {

        public Blocks(DataGenerator generator, ExistingFileHelper helper) {
            super(generator, RainbowMain.MODID, helper);
        }

        @Override
        protected void addTags() {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(Registration.EXAMPLE_GENERATOR.get())
                    .add(Registration.DEMO_BLOCK.get())
                    .add(Registration.SOLAR_TEST.get())
                    .add(Registration.PRIMARY_CONVERTER.get());

            tag(BlockTags.NEEDS_STONE_TOOL)
                    .add(Registration.PRIMARY_CONVERTER.get());

            tag(BlockTags.NEEDS_IRON_TOOL)
                    .add(Registration.EXAMPLE_GENERATOR.get())
                    .add(Registration.DEMO_BLOCK.get())
                    .add(Registration.SOLAR_TEST.get());

        }

        @Override
        public String getName() {
            return "RainbowMod Tags";
        }
    }

    public static class Items extends ItemTagsProvider {

        public Items(DataGenerator generator, BlockTagsProvider provider, ExistingFileHelper helper) {
            super(generator, provider, RainbowMain.MODID, helper);
        }

        @Override
        protected void addTags() {
            //---------------------------dye items
            tag(Tags.Items.DYES)
                    .add(Registration.WHITE_DROPLET.get())
                    .add(Registration.RED_DROPLET.get())
                    .add(Registration.YELLOW_DROPLET.get())
                    .add(Registration.BLUE_DROPLET.get())
                    .add(Registration.ORANGE_DROPLET.get())
                    .add(Registration.PURPLE_DROPLET.get())
                    .add(Registration.GREEN_DROPLET.get())
                    .add(Registration.CYAN_DROPLET.get())
                    .add(Registration.MAGENTA_DROPLET.get())
                    .add(Registration.LIME_DROPLET.get())
                    .add(Registration.PINK_DROPLET.get())
                    .add(Registration.LIGHT_BLUE_DROPLET.get())
                    .add(Registration.BROWN_DROPLET.get())
                    .add(Registration.LIGHT_GRAY_DROPLET.get())
                    .add(Registration.GRAY_DROPLET.get())
                    .add(Registration.BLACK_DROPLET.get());

            tag(Tags.Items.DYES_WHITE).add(Registration.WHITE_DROPLET.get());
            tag(Tags.Items.DYES_RED).add(Registration.RED_DROPLET.get());
            tag(Tags.Items.DYES_YELLOW).add(Registration.YELLOW_DROPLET.get());
            tag(Tags.Items.DYES_BLUE).add(Registration.BLUE_DROPLET.get());
            tag(Tags.Items.DYES_ORANGE).add(Registration.ORANGE_DROPLET.get());
            tag(Tags.Items.DYES_PURPLE).add(Registration.PURPLE_DROPLET.get());
            tag(Tags.Items.DYES_GREEN).add(Registration.GREEN_DROPLET.get());
            tag(Tags.Items.DYES_CYAN).add(Registration.CYAN_DROPLET.get());
            tag(Tags.Items.DYES_MAGENTA).add(Registration.MAGENTA_DROPLET.get());
            tag(Tags.Items.DYES_LIME).add(Registration.LIME_DROPLET.get());
            tag(Tags.Items.DYES_PINK).add(Registration.PINK_DROPLET.get());
            tag(Tags.Items.DYES_LIGHT_BLUE).add(Registration.LIGHT_BLUE_DROPLET.get());
            tag(Tags.Items.DYES_BROWN).add(Registration.BROWN_DROPLET.get());
            tag(Tags.Items.DYES_LIGHT_GRAY).add(Registration.LIGHT_GRAY_DROPLET.get());
            tag(Tags.Items.DYES_GRAY).add(Registration.GRAY_DROPLET.get());
            tag(Tags.Items.DYES_BLACK).add(Registration.BLACK_DROPLET.get());


            tag(Registration.LENSES)
                    .add(Registration.CLEAR_LENS.get()) //todo rename to white?
                    .add(Registration.RED_LENS.get())
                    .add(Registration.YELLOW_LENS.get())
                    .add(Registration.BLUE_LENS.get())
                    .add(Registration.ORANGE_LENS.get())
                    .add(Registration.PURPLE_LENS.get())
                    .add(Registration.GREEN_LENS.get())
                    .add(Registration.CYAN_LENS.get())
                    .add(Registration.MAGENTA_LENS.get())
                    .add(Registration.LIME_LENS.get())
                    .add(Registration.PINK_LENS.get())
                    .add(Registration.LIGHT_BLUE_LENS.get())
                    .add(Registration.BROWN_LENS.get())
                    .add(Registration.LIGHT_GRAY_LENS.get())
                    .add(Registration.GRAY_LENS.get())
                    .add(Registration.BLACK_LENS.get());
        }

        @Override
        public String getName() {
            return "RainbowMod Tags";
        }
    }


}