package com.xain.rainbowmod.datagen;

import com.xain.rainbowmod.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        // Registration.LENS.get() for something we have created?

        ShapedRecipeBuilder.shaped(Registration.EXAMPLE_ITEM.get())
                .pattern("xxx")
                .pattern(" s ")
                .pattern(" s ")
                .define('x', Tags.Items.BONES)
                .define('s', Items.STICK)
                .group("tutorial")
                .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.EXAMPLE_GENERATOR.get())
                .pattern("iii")
                .pattern("iCi")
                .pattern("ccc")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('C', Tags.Items.STORAGE_BLOCKS_COAL)
                .define('c', ItemTags.COALS)
                .group("tutorial")
                .unlockedBy("coals", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COAL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.DEMO_BLOCK.get())
                .pattern("rir")
                .pattern("iri")
                .pattern("rir")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('r', Tags.Items.DUSTS_REDSTONE)
                .group("tutorial")
                .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
                .save(consumer);


        ShapelessRecipeBuilder.shapeless(Items.WHITE_WOOL)
                .requires(Tags.Items.DYES_WHITE)
                .requires(ItemTags.WOOL)
                .group("Rainbow Mod")
                .unlockedBy("white", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.WHITE_DROPLET.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(Items.RED_WOOL)
                .requires(Tags.Items.DYES_RED)
                .requires(ItemTags.WOOL)
                .group("Rainbow Mod")
                .unlockedBy("red", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.RED_DROPLET.get()))
                .save(consumer);
    }
}
