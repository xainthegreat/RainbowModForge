package com.xain.rainbowmod.blocks;

import com.xain.rainbowmod.setup.Registration;
import com.xain.rainbowmod.tools.CapabilityLight;
import com.xain.rainbowmod.tools.CustomEnergyStorage;
import com.xain.rainbowmod.tools.LightStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class SolarTestContainer extends AbstractContainerMenu {

    private BlockEntity blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;

    public SolarTestContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.SOLAR_TEST_CONTAINER.get(), windowId);
        blockEntity = world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

//        if (blockEntity != null) {
//            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
//                addSlot(new SlotItemHandler(h, 0, 64, 24));
//            });
//        }
        layoutPlayerInventorySlots(10, 70);
        trackLight();
    }

    // Setup syncing of power from server to client so that the GUI can show the amount of light(mod) in the block
    private void trackLight() {
        // Unfortunatelly on a dedicated server ints are actually truncated to short so we need
        // to split our integer here (split our 32 bit integer into two 16 bit integers)
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getLight() & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityLight.LIGHT_STORAGE_CAPABILITY).ifPresent(h -> {
                    int lightStored = h.getLightStored() & 0xffff0000;
                    h.setLight(lightStored + (value & 0xffff));
                });
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getLight() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityLight.LIGHT_STORAGE_CAPABILITY).ifPresent(h -> {
                    int lightStored = h.getLightStored() & 0x0000ffff;
                    h.setLight(lightStored | (value << 16));
                });
            }
        });
    }

    public int getLight() {
        return blockEntity.getCapability(CapabilityLight.LIGHT_STORAGE_CAPABILITY).map(LightStorage::getLightStored).orElse(0);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                playerEntity, Registration.SOLAR_TEST.get());
    }

    @Override
    public ItemStack quickMoveStack(Player p_39253_, int pos) {
        return ItemStack.EMPTY;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}