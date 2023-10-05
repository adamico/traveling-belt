package me.kc00l.travelingbelt.common.containers;

import me.kc00l.travelingbelt.TravelingBelt;
import me.kc00l.travelingbelt.common.blockentities.ModificationAltarBE;
import me.kc00l.travelingbelt.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ModificationAltarContainer extends BaseContainer {
    public static final String TEXTURE_LOC_SLOT_TOOL = TravelingBelt.MODID + ":textures/gui/slot_belt.png";
    public static final String TEXTURE_LOC_SLOT_TEMPLATE = TravelingBelt.MODID + ":gui/slot_modifier";
    public static final int SLOTS = 2;
    private ModificationAltarBE be;

    public ModificationAltarContainer(int windowId, Inventory playerInventory, FriendlyByteBuf extraData) {
        super(Registration.ModificationAltar_Container.get(), windowId);
        BlockPos pos = extraData.readBlockPos();

        this.be = (ModificationAltarBE) playerInventory.player.level().getBlockEntity(pos);
        addOwnSlots();
        addPlayerSlots(playerInventory, -12, 70);
    }

    public ModificationAltarContainer(int windowId, Inventory playerInventory, ModificationAltarBE tileEntity) {
        super(Registration.ModificationAltar_Container.get(), windowId);
        this.be = Objects.requireNonNull(tileEntity);

        addOwnSlots();
        addPlayerSlots(playerInventory, -12, 70);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(be.getLevel(), be.getBlockPos()), playerIn, Registration.ModificationAltar.get());
    }

    private void addOwnSlots() {
        this.be.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
            int x = 132;
            addSlot(new SlotTemplateManager(h, 0, x, 18, TEXTURE_LOC_SLOT_TOOL));
            addSlot(new SlotTemplateManager(h, 1, x, 63, TEXTURE_LOC_SLOT_TEMPLATE));
        });
    }

    @Override
    @Nonnull
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack currentStack = slot.getItem();
            itemstack = currentStack.copy();

            if (index < SLOTS) {
                if (!this.moveItemStackTo(currentStack, SLOTS, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(currentStack, 0, SLOTS, false)) {
                return ItemStack.EMPTY;
            }

            if (currentStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public ModificationAltarBE getTe() {
        return be;
    }

    public static class SlotTemplateManager extends SlotItemHandler {
        private String backgroundLoc;

        public SlotTemplateManager(IItemHandler itemHandler, int index, int xPosition, int yPosition, String backgroundLoc) {
            super(itemHandler, index, xPosition, yPosition);
            this.backgroundLoc = backgroundLoc;
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }
    }
}