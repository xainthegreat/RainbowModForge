package com.xain.rainbowmod.datagen;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Tags {

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
                    .add(Registration.SOLAR_TEST.get());;
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
            tag(net.minecraftforge.common.Tags.Items.DYES).add(Registration.RED_DROPLET.get());
            tag(net.minecraftforge.common.Tags.Items.DYES_RED).add(Registration.RED_DROPLET.get());
        }

        @Override
        public String getName() {
            return "RainbowMod Tags";
        }
    }


}