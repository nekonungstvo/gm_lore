package ru.konungstvo.gm_tooltip.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.konungstvo.gm_tooltip.client.LoreGui;

public class LoreGuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        return new LoreContainer(entityPlayer.inventory);
    }

    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        return new LoreGui(entityPlayer.inventory);
    }
}
