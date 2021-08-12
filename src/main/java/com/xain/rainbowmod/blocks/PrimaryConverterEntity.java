package com.xain.rainbowmod.blocks;

import com.xain.rainbowmod.setup.Registration;
import com.xain.rainbowmod.tools.CapabilityLight;
import com.xain.rainbowmod.tools.LightStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PrimaryConverterEntity extends BlockEntity {
// todo update handlers and load/save also add what the block is going to do...
    private final ItemStackHandler itemHandler = createHandler();
    private final LightStorage lightStorage = createLightStorage();

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<LightStorage> light = LazyOptional.of(() -> lightStorage);

    private int counter;
    private boolean converting;
    private String currentLens;
    private String lensCheck;

    public PrimaryConverterEntity(BlockPos pos, BlockState state) {
        super(Registration.PRIMARY_CONVERTER_ENTITY.get(), pos, state);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        // Don't forget to invalidate your caps when your block entity is removed
        handler.invalidate();
        light.invalidate();
    }

    public void tickServer(BlockState state) {
        //TODO take in light, based on what lens is installed, create that colored water, push water to condenser
        currentLens = itemHandler.getStackInSlot(0).getItem().getRegistryName().toString();
        if (currentLens == null){
            // remove water, or have button to purge water, or if correct lens is put back in start craft again?
        }
        else {
            if (counter > 0 && currentLens.contains(lensCheck)) {
                counter--;
                if (counter == 0){
                    //todo do i need to create a recipe somehow?
                    itemHandler.insertItem(1, new ItemStack(Registration.RED_DROPLET.get().asItem()), false);
                }
                setChanged();
            }

            if (counter <= 0) {
                if (lightStorage.getCurrentLight() == lightStorage.getMaxLight()) { //todo && there is water in tank
                    // check which colored lens
                    if (currentLens.contains("red")) {
                        lensCheck = "red";
                        // expected output is red water
                    }

                    // if light capacity is maxed start conversion counter = 600; // 600 ticks is 30 seconds
                    // while converting, if lens is removed light is lost
                    // while converting, if block is broken light and water is lost


                    setChanged();
                }
            }
        }
        level.setBlock(worldPosition, state,
                Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
//        BlockState blockState = level.getBlockState(worldPosition);
//        if (blockState.getValue(BlockStateProperties.POWERED) != counter > 0) {
//            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, counter > 0),
//                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
//        }
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        lightStorage.deserializeNBT(tag.get("light"));

        super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());

        if (!converting)
            tag.put("light", lightStorage.serializeNBT());

        return super.save(tag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) { // todo change back to 1 after testing, its changing the water not creating an item

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0)
                    return stack.getItem().getRegistryName().toString().contains("lens");

                return false;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!stack.getItem().getRegistryName().toString().contains("lens")) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private LightStorage createLightStorage() {
        return new LightStorage(10, true, false) {
            @Override
            protected void onLightChange() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityLight.LIGHT) {
            return light.cast();
        }
        return super.getCapability(cap, side);
    }
}

