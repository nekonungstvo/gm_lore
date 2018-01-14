package ru.konungstvo.gm_tooltip.core;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class ClientProxy extends CommonProxy {
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private String getNBT(ItemStack itemStack) {
        NBTTagCompound nbt = itemStack.getTagCompound();
        if (nbt != null) return nbt.getString("gm_lore");
        return "";
    }

    @SubscribeEvent
    public void tooltip_event(ItemTooltipEvent event) {
        EntityPlayer current_player = FMLClientHandler.instance().getClientPlayerEntity();

        if (!current_player.capabilities.isCreativeMode) return;

        String forGM = this.getNBT(event.itemStack);
        if (!forGM.equals("")) {
            event.toolTip.add("");
            event.toolTip.add(EnumChatFormatting.GOLD + "For gm:");
            event.toolTip.add(EnumChatFormatting.WHITE + forGM);
        }
    }
}
