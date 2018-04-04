package ru.konungstvo.gm_tooltip.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class LoreContainer extends Container {
    public LoreContainer(InventoryPlayer inventory) {
        this.addSlotToContainer(new Slot(new LoreInventory(), 0, 9, 9));

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventory, i, 9 + i * 18, 112));
        }
    }


    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        System.out.println("test");
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
