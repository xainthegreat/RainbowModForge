package com.xain.rainbowmod.datagen;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, RainbowMain.MODID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerExampleGeneratorBlock();
        registerDemoBlock();
        registerSolarTestBlock();
        registerPrimaryConverterBlock();
    }

    private void registerDemoBlock() {
        simpleBlock(Registration.DEMO_BLOCK.get());
    }
    private void registerSolarTestBlock() {
        simpleBlock(Registration.SOLAR_TEST.get());
    }
    private void registerPrimaryConverterBlock() {
        simpleBlock(Registration.PRIMARY_CONVERTER.get());
    }

    private void registerExampleGeneratorBlock() {
        ResourceLocation side = new ResourceLocation(RainbowMain.MODID, "block/example_generator");
        BlockModelBuilder modelBlock = models().cube("example_generator",
                side, side, new ResourceLocation(RainbowMain.MODID, "block/example_generator_front"), side, side, side);
        BlockModelBuilder modelBlockPowered = models().cube("example_generator_powered",
                side, side, new ResourceLocation(RainbowMain.MODID, "block/example_generator_powered"), side, side, side);
        orientedBlock(Registration.EXAMPLE_GENERATOR.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelBlockPowered;
            } else {
                return modelBlock;
            }
        });
    }

    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }
}
