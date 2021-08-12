package com.xain.rainbowmod.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class LootTables extends LootTableProvider {
    private final DataGenerator generator;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public LootTables(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    @Override
    public void run(HashCache cache) {
        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        tables.put(Registration.EXAMPLE_GENERATOR.get().getLootTable(), createStandardTable(
                "example_generator", Registration.EXAMPLE_GENERATOR.get()).setParamSet(LootContextParamSets.BLOCK).build());
        tables.put(Registration.SOLAR_TEST.get().getLootTable(), createStandardTable(
                "solar_test", Registration.SOLAR_TEST.get()).setParamSet(LootContextParamSets.BLOCK).build());
//        tables.put(Registration.DEMO.get().getLootTable(), createStandardTable(
//                "demo", Registration.DEMO.get()).setParamSet(LootContextParamSets.BLOCK).build());
        writeTables(cache, tables);
    }

    //todo change "light" back to "energy" or split them so one is for power and one is for light
    protected LootTable.Builder createStandardTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy("inv", "BlockEntityTag.inv", CopyNbtFunction.MergeStrategy.REPLACE)
//                                .copy("energy", "BlockEntityTag.energy", CopyNbtFunction.MergeStrategy.REPLACE) //todo need energy in standard table?
                                .copy("light", "BlockEntityTag.light", CopyNbtFunction.MergeStrategy.REPLACE))
                        .apply(SetContainerContents.setContents()
                                .withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.lootTable().withPool(builder);
    }

    private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.save(GSON, cache, net.minecraft.world.level.storage.loot.LootTables.serialize(lootTable), path);
            } catch (IOException e) {
                RainbowMain.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Override
    public String getName() {
        return "Tutorial LootTables";
    }
}
