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
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class PrimaryConverterContainer extends AbstractContainerMenu {

    private BlockEntity blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;

    public PrimaryConverterContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.PRIMARY_CONVERTER_CONTAINER.get(), windowId);
        blockEntity = world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        // adds inventory slots to gui if needed
        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 67, 28));
                addSlot(new SlotItemHandler(h, 1, 146, 42));// TODO maybe remove this after testing, also update field in quick move
            });
        }
        layoutPlayerInventorySlots(10, 70);
        //trackPower();
    }

    // Setup syncing of power from server to client so that the GUI can show the amount of power in the block
    // TODO update this so that the bars change color while it is converting?
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
                blockEntity.getCapability(CapabilityLight.LIGHT).ifPresent(h -> {
                    int lightStored = h.getCurrentLight() & 0xffff0000;
                    h.setCurrentLight(lightStored + (value & 0xffff));
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
                blockEntity.getCapability(CapabilityLight.LIGHT).ifPresent(h -> {
                    int lightStored = h.getCurrentLight() & 0x0000ffff;
                    h.setCurrentLight(lightStored | (value << 16));
                });
            }
        });
    }

    public int getLight() {
        return blockEntity.getCapability(CapabilityLight.LIGHT).map(LightStorage::getCurrentLight).orElse(0);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                playerEntity, Registration.PRIMARY_CONVERTER.get());
    }

    // always needed, allows shift clicking of items, update this if there are restrictions to GUI's item slots
    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 0 || index == 1) {
                if (!this.moveItemStackTo(stack, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                // todo if stack is a lens
                if (stack.getItem().getRegistryName().toString().contains("lens")) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 29) {
                    if (!this.moveItemStackTo(stack, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.moveItemStackTo(stack, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }
    // always needed, for adding Player inventory to gui
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }
    // always needed, for adding Player inventory to gui
    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
    // always needed, for adding Player inventory to gui
    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}