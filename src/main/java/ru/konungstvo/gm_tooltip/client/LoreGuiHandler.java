package ru.konungstvo.gm_tooltip.client;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class LoreGuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        return new ContainerLore(entityPlayer.inventory);
    }

    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        return new LoreGui(entityPlayer.inventory);
    }
}
