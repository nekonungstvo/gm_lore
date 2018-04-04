package ru.konungstvo.gm_tooltip.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.konungstvo.gm_tooltip.common.LoreContainer;

public class LoreGui extends GuiContainer {
    private static final ResourceLocation loreGuiTexture = new ResourceLocation(
            "lore:textures/gui/lore.png"
    );

    public LoreGui(InventoryPlayer inventory) {
        super(new LoreContainer(inventory));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(loreGuiTexture);
        int shiftX = (this.width - this.xSize) / 2;
        int shiftY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(shiftX, shiftY, 0, 0, this.xSize, this.ySize);
    }

}