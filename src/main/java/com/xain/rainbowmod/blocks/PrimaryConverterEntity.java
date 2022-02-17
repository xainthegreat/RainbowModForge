package com.xain.rainbowmod.blocks;

import com.xain.rainbowmod.setup.Registration;
import com.xain.rainbowmod.tools.CapabilityLight;
import com.xain.rainbowmod.tools.LightStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


//TODO NOT GETTING LIGHT TO PUSH INTO THIS BLOCK.
//TODO when setting up tiered machines, reduce inputs of this one to PRIMARY colors.

public class PrimaryConverterEntity extends BlockEntity {
// todo update handlers and load/save also add what the block is going to do...

    private final ItemStackHandler itemHandler = createHandler();
    private final LightStorage lightStorage = createLightStorage();

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<LightStorage> light = LazyOptional.of(() -> lightStorage);

    private static final int CONVERT_TIME = 600; // 30 seconds default? //todo add to a config some how?
    public static final int USAGE = 10; //todo configurable

    // Server/Client side value indicating if there is power. Updated from server
    private boolean hasLight = false;
    private int counter;
    private boolean converting = false;
    private String installedLens = null;
    private String lensCheck = null;
    private String toGive = null;

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

    // getUpdatePacket() and onDataPacket() are for synchronizing on block updates
    // getUpdatePacket() is called server side and colle
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean("hasLight", hasLight);
        return new ClientboundBlockEntityDataPacket(worldPosition, 1, tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        hasLight = tag.getBoolean("hasLight");
    }

    // getUpdateTag() and handleUpdateTag() are for synchronizing the client side BE on chunk load
    // getUpdateTag() is called server side and collects data for the client
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putBoolean("hasLight", hasEnoughLightToWork());
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        hasLight = tag.getBoolean("hasLight");
    }

    public void tickServer(BlockState state) {
        ItemStack lens = itemHandler.getStackInSlot(0);
        if (lens.is(Registration.LENSES)) {
            installedLens = checkColor(lens);
            //primary can only do red, blue, and yellow
            if (installedLens.equals("red") || installedLens.equals("blue") || installedLens.equals("yellow")) {
                if (installedLens.equals(lensCheck)) {
                    if (toGive != null) {
                        if (createDroplet(toGive))
                            toGive = null;
                        return;
                    }
                    if (converting) {
                        counter--;
                        if (counter <= 0) {
                            createDroplet(installedLens);
                        }
                        return;
                    }
                }

                if (hasEnoughLightToWork()) {
                    lightStorage.consumeLight(USAGE); // todo make it take light during production instead of a lump sum?
                    lensCheck = installedLens;
                    counter = 20; //todo set to CONVERT_TIME
                    toGive = null;
                    converting = true;
                    setChanged();
                }
            }
        }
    }

    public void tickClient(BlockState state) {
        //todo update this, maybe to change texture or somethign?
        if (hasLight) {
            BlockPos p = this.worldPosition;
            level.addParticle(ParticleTypes.CLOUD,
                    p.getX()+.5, p.getY() + 1.0, p.getZ()+.5,
                    0.0, 0.0, 0.0);
        }
    }

    private boolean hasEnoughLightToWork() {
        return lightStorage.getLightStored() >= USAGE;
    }

    private String checkColor(ItemStack stack) {
        //todo this does not work for light blue and light gray, fixed?
        String name = stack.getItem().getRegistryName().toString();
        return name.contains("_") ? name.substring(name.indexOf(':')+1, name.lastIndexOf('_')) : null;
    }

    private boolean createDroplet(String color) {
        ItemStack item = itemHandler.getStackInSlot(1);

        if (item.is(Items.AIR)) {
            itemHandler.setStackInSlot(1, new ItemStack(colorToInsert(color)));
            lensCheck = null;
            converting = false;
            setChanged();
            return true;
        }

        if (color.equals(checkColor(item)) && item.getCount() < 64) {
            item = item.copy();
            item.grow(1);
            itemHandler.setStackInSlot(1, item);
            lensCheck = null;
            converting = false;
            setChanged();
            return true;
        }
        else {
            toGive = color;
            return false;
        }
    }

    private Item colorToInsert (String color) {
        //todo reduce this to the three colors when you finish adding the other blocks
        switch (color) {
            case "red" : return Registration.RED_DROPLET.get().asItem();
            case "white" : return Registration.WHITE_DROPLET.get().asItem(); // todo white is being removed?
            case "orange" : return Registration.ORANGE_DROPLET.get().asItem();
            case "magenta" : return Registration.MAGENTA_DROPLET.get().asItem();
            case "yellow" : return Registration.YELLOW_DROPLET.get().asItem();
            case "lime" : return Registration.LIME_DROPLET.get().asItem();
            case "pink" : return Registration.PINK_DROPLET.get().asItem();
            case "gray" : return Registration.GRAY_DROPLET.get().asItem();
            case "cyan" : return Registration.CYAN_DROPLET.get().asItem();
            case "purple" : return Registration.PURPLE_DROPLET.get().asItem();
            case "blue" : return Registration.BLUE_DROPLET.get().asItem();
            case "brown" : return Registration.BROWN_DROPLET.get().asItem();
            case "green" : return Registration.GREEN_DROPLET.get().asItem();
            case "black" : return Registration.BLACK_DROPLET.get().asItem(); //todo black is being removed?
            case "light_blue" : return Registration.LIGHT_BLUE_DROPLET.get().asItem(); //todo update case string
            case "light_gray" : return Registration.LIGHT_GRAY_DROPLET.get().asItem(); //todo update case string
            default: return null;
        }
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
                String itemName = checkColor(stack);
                if (slot == 0){
                    if (!itemName.equals("red") && !itemName.equals("yellow") && !itemName.equals("blue"))
                        return false;
                    else
                        return stack.is(Registration.LENSES);
                }
                return false;
            }

            @Override
            public int getSlotLimit(int slot)
            {
                if (slot == 0)
                    return 1;
                else
                    return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!stack.is(Registration.LENSES)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private LightStorage createLightStorage() {
        return new LightStorage(10) {
            @Override
            protected void onLightChanged() {
                boolean newHasLight = hasEnoughLightToWork();
                if (newHasLight != hasLight) {
                    hasLight = newHasLight;
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                }
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

