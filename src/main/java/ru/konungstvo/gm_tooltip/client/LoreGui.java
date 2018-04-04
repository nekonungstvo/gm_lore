package ru.konungstvo.gm_tooltip.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import ru.konungstvo.gm_tooltip.common.LoreContainer;

@SideOnly(Side.CLIENT)
public class LoreGui extends GuiContainer {
    private static final ResourceLocation loreGuiTexture = new ResourceLocation(
            "lore:textures/gui/lore.png"
    );

    private GuiTextField loreField;

    public LoreGui(InventoryPlayer inventory) {
        super(new LoreContainer(inventory));
    }

    @Override
    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents(true);
        this.loreField = new GuiTextField(
                this.fontRendererObj,
                9,
                9 + 18,
                100,
                100
        );
        this.loreField.setMaxStringLength(100);
        this.loreField.setFocused(false);
        this.loreField.setCanLoseFocus(true);
    }

    public void drawScreen(int drawScreen1, int drawScreen2, float drawScreen3) {
        super.drawScreen(drawScreen1, drawScreen2, drawScreen3);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        this.loreField.drawTextBox();
    }

    @Override
    protected void mouseClicked(int x, int y, int z) {
        super.mouseClicked(x, y, z);
        this.loreField.mouseClicked(x, y, z);
    }

    @Override
    protected void keyTyped(char character, int code) {
        super.keyTyped(character, code);
        this.loreField.textboxKeyTyped(character, code);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.loreField.updateCursorCounter();
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