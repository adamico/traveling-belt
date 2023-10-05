package me.kc00l.travelingbelt.common.blockentities;

import me.kc00l.travelingbelt.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ModificationAltarBE extends BlockEntity implements MenuProvider {
    private final IItemHandler EMPTY = new ItemStackHandler(0);
    public final ModificationAltarHandler itemHandler = new ModificationAltarHandler(SLOTS, this);
    public LazyOptional<ModificationAltarHandler> handlerLazyOptional;

    public ModificationAltarBE(BlockPos pos, BlockState state) {
        super(Registration.ModificationAltar_BE.get(), pos, state);
        handlerLazyOptional = LazyOptional.of(() -> itemHandler);
    }

    // create methods to manage the Modification Altar functionalities, slots, etc.

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("travelingbelt.screen.modificationaltar");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new ModificationAltarContainer(i, playerInventory, this);
    }
}
