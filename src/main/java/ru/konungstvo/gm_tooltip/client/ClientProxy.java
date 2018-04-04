package ru.konungstvo.gm_tooltip.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import ru.konungstvo.gm_tooltip.GMLoreMod;
import ru.konungstvo.gm_tooltip.common.CommonProxy;

public class ClientProxy extends CommonProxy {
    @Mod.Instance(GMLoreMod.MODID)
    private static GMLoreMod instance;

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
