package ru.konungstvo.gm_tooltip.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LoreContainer extends Container {
    private LoreInventory processInv = new LoreInventory();

    public LoreContainer(InventoryPlayer inventory) {
        this.addSlotToContainer(new Slot(processInv, 0, 9, 9));

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventory, i, 9 + i * 18, 112));
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        ItemStack itemStack = processInv.getStack();
        player.inventory.addItemStackToInventory(itemStack);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
