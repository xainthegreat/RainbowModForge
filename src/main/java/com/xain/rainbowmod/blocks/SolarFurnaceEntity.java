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

public class SolarFurnaceEntity  extends BlockEntity {
    // todo left off with solar test not keeping light in block when broken, also when placing a broken block it duplicates...
    public SolarFurnaceEntity(BlockPos pos, BlockState state) {
        super(Registration.SOLAR_TEST_ENTITY.get(), pos, state);
    }

    private final LightStorage lightStorage = createLightStorage();
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<LightStorage> light = LazyOptional.of(() -> lightStorage);

    private void sendOutLight() {
        AtomicInteger capacity = new AtomicInteger(lightStorage.getLightStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity entityToCheck = level.getBlockEntity(worldPosition.relative(direction));
                if (entityToCheck != null) {
                    boolean doContinue = entityToCheck.getCapability(CapabilityLight.LIGHT, direction).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveLight(Math.min(capacity.get(), 1), false);
                                    capacity.addAndGet(-received);
                                    lightStorage.consumeLight(received);
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
            lightStorage.receiveLight(1, false);
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            setChanged();
        }


        sendOutLight();
    }

    @Override
    public void load(CompoundTag tag) {
        lightStorage.deserializeNBT(tag.get("light"));

        super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("light", lightStorage.serializeNBT());

        return super.save(tag);
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
        if (cap == CapabilityLight.LIGHT) {
            return light.cast();
        }
        return super.getCapability(cap, side);
    }

    public void tickClient(BlockState state1) {
    }
}