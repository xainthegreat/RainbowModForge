package com.xain.rainbowmod.blocks;

import com.xain.rainbowmod.setup.Registration;
import com.xain.rainbowmod.tools.CapabilityLight;
import com.xain.rainbowmod.tools.LightStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class SolarFurnaceEntity  extends BlockEntity {
    // todo left off with solar test not keeping light in block when broken, also when placing a broken block it duplicates...
    public SolarFurnaceEntity(BlockPos pos, BlockState state) {
        super(Registration.SOLAR_TEST_ENTITY.get(), pos, state);
    }

    private final LightStorage lightHandler = createLightStorage();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<LightStorage> light = LazyOptional.of(() -> lightHandler);

    private void sendOutLight() {
        AtomicInteger capacity = new AtomicInteger(lightHandler.getLightStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity entityToCheck = level.getBlockEntity(worldPosition.relative(direction));
                if (entityToCheck != null) {
                    boolean doContinue = entityToCheck.getCapability(CapabilityLight.LIGHT_STORAGE_CAPABILITY, direction).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveLight(Math.min(capacity.get(), 1), false);
                                    capacity.addAndGet(-received);
                                    lightHandler.consumeLight(received);
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
//        ServerWorld#getBlockTicks;
//        level.getBlockTicks().scheduleTick();// todo delay light increases to a couple seconds per light absorption
        if (level.canSeeSky(worldPosition.above()) && level.isDay()) {
            level.getBlockTicks();
            lightHandler.receiveLight(1, false);
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            setChanged();
        }


        sendOutLight();
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains("light")){
            lightHandler.deserializeNBT(tag.get("light"));
        }

        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("light", lightHandler.serializeNBT());
    }

    private LightStorage createLightStorage() {
        // todo make configurable
        return new LightStorage(100, 1) {
            @Override
            protected void onLightChanged() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityLight.LIGHT_STORAGE_CAPABILITY) {
            return light.cast();
        }
        return super.getCapability(cap, side);
    }

    public void tickClient(BlockState state1) {
    }
}