package ru.konungstvo.gm_tooltip.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerLore extends Container {
    public ContainerLore(InventoryPlayer inventory) {
        this.addSlotToContainer(new Slot(inventory, 9, 9, 9));

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventory, i, 9 + i * 18, 112));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
