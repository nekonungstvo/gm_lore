package ru.konungstvo.gm_tooltip.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import ru.konungstvo.gm_tooltip.client.gui.component.GuiMultilineTextField;
import ru.konungstvo.gm_tooltip.common.inventory.LoreContainer;

@SideOnly(Side.CLIENT)
public class LoreGui extends GuiContainer {
    private final static int PADDING = 9;
    private final static int SLOT_SIZE = 18;

    private static final ResourceLocation loreGuiTexture = new ResourceLocation(
            "lore:textures/gui/lore.png"
    );

    private GuiTextField nameField;
    private GuiMultilineTextField loreField;

    public LoreGui(InventoryPlayer inventory) {
        super(new LoreContainer(inventory));
        this.width = 177;
        this.height = 136;
    }

    @Override
    public void initGui() {
        super.initGui();

        Keyboard.enableRepeatEvents(true);
        int shiftX = (this.width - this.xSize) / 2;
        int shiftY = (this.height - this.ySize) / 2;

        this.nameField = new GuiTextField(
                this.fontRendererObj,
                shiftX + PADDING + SLOT_SIZE + PADDING,
                shiftY + PADDING,
                this.xSize - PADDING - SLOT_SIZE - 2 * PADDING,
                SLOT_SIZE
        );

        this.nameField.setMaxStringLength(100);
        this.nameField.setFocused(false);
        this.nameField.setCanLoseFocus(true);

        this.loreField = new GuiMultilineTextField(
                this.fontRendererObj,
                shiftX + PADDING,
                shiftY + PADDING + SLOT_SIZE + PADDING,
                this.xSize - 2 * PADDING,
                70
        );
    }

    @Override
    protected void mouseClicked(int x, int y, int z) {
        super.mouseClicked(x, y, z);
        this.nameField.mouseClicked(x, y, z);
        this.loreField.mouseClicked(x, y, z);
    }

    @Override
    protected void keyTyped(char character, int code) {
        boolean processed = this.nameField.textboxKeyTyped(character, code);
        processed |= loreField.keyTyped(character, code);

        if (!processed) super.keyTyped(character, code);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.nameField.updateCursorCounter();
        this.loreField.updateCursorCounter();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(loreGuiTexture);
        int shiftX = (this.width - this.xSize) / 2;
        int shiftY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(shiftX, shiftY, 0, 0, this.xSize, this.ySize);

        this.nameField.drawTextBox();
        this.loreField.draw();
    }

}