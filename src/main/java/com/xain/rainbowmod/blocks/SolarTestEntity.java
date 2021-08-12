package com.xain.rainbowmod.blocks;

import com.xain.rainbowmod.setup.Registration;
import com.xain.rainbowmod.tools.CapabilityLight;
import com.xain.rainbowmod.tools.LightStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class SolarTestEntity extends BlockEntity {

    public SolarTestEntity(BlockPos pos, BlockState state) {
        super(Registration.SOLAR_TEST_ENTITY.get(), pos, state);
    }

    private final LightStorage lightStorage = createLightStorage();
    private final LazyOptional<LightStorage> light = LazyOptional.of(() -> lightStorage);


    private void sendOutLight() {
        AtomicInteger capacity = new AtomicInteger(lightStorage.getCurrentLight());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity entityToCheck = level.getBlockEntity(worldPosition.relative(direction));
                if (entityToCheck != null) {
                    boolean doContinue = entityToCheck.getCapability(CapabilityLight.LIGHT, direction).map(handler -> {
                                if (handler.canReceive()) {
                                    capacity.addAndGet(-handler.addLight(Math.min(capacity.get(), 1)));
                                    lightStorage.setCurrentLight(capacity.get());
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void setRemoved() {
        // Don't forget to invalidate your caps when your block entity is removed
        super.setRemoved();
        light.invalidate();
    }

    public void tickServer(BlockState state) {
        if (level.canSeeSky(worldPosition.above()) && level.isDay()) {
            lightStorage.createLight(1);
            setChanged();
            level.setBlock(worldPosition, state, Constants.BlockFlags.BLOCK_UPDATE);
        }

        sendOutLight();
    }

    private LightStorage createLightStorage() {
        // todo make configurable
        return new LightStorage(100, false, true) {
            @Override
            protected void onLightChange() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityLight.LIGHT) {
            return light.cast();
        }
        return super.getCapability(cap, side);
    }

        @Override
    public void load(CompoundTag tag) {
        lightStorage.deserializeNBT(tag.getCompound("light"));
//        itemHandler.deserializeNBT(tag.getCompound("inv"));
//        energyStorage.deserializeNBT(tag.get("energy"));
//
//        counter = tag.getInt("counter");
        super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("light", lightStorage.serializeNBT());
//        tag.put("inv", itemHandler.serializeNBT());
//        tag.put("energy", energyStorage.serializeNBT());
//
//        tag.putInt("counter", counter);
        return super.save(tag);
    }

}
