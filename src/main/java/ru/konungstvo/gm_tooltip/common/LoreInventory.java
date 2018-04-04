package ru.konungstvo.gm_tooltip.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class LoreInventory implements IInventory {
    private ItemStack item = null;

    @Override
    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStack() {
        return item;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return item;
    }

    @Override
    public ItemStack decrStackSize(int i, int amount) {
        if (item != null) {
            ItemStack itemStack = item;
            item = null;
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return item;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.item = itemStack;
    }

    @Override
    public String getInventoryName() {
        return "Lore processing";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }
}
