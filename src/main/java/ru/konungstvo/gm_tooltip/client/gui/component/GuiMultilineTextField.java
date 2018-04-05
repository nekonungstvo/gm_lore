package ru.konungstvo.gm_tooltip.client.gui.component;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;

public class GuiMultilineTextField extends Gui {
    private FontRenderer fontRenderer;
    private int xPosition;
    private int yPosition;

    private int width;
    private int height;

    private int amount = 0;
    private GuiTextField[] lines;

    public GuiMultilineTextField(FontRenderer fontRenderer, int xPosition, int yPosition, int width, int height) {
        this.fontRenderer = fontRenderer;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;

        this.amount = height / fontRenderer.FONT_HEIGHT;
        this.lines = new GuiTextField[amount];
        for (int i = 0; i < this.amount; i++) {
            lines[i] = new GuiTextField(
                    fontRenderer,
                    xPosition,
                    yPosition + i * fontRenderer.FONT_HEIGHT,
                    width,
                    fontRenderer.FONT_HEIGHT
            );
        }
    }

    public void drawTextBox() {
        for (int i = 0; i < this.amount; i++) {
            lines[i].drawTextBox();
        }
    }

    public void mouseClicked(int x, int y, int z) {
        for (int i = 0; i < this.amount; i++) {
            lines[i].mouseClicked(x, y, z);
        }
    }

    public void updateCursorCounter() {
        for (int i = 0; i < this.amount; i++) {
            lines[i].updateCursorCounter();
        }
    }

    public void textboxKeyTyped(char character, int code) {
        for (int i = 0; i < this.amount; i++) {
            lines[i].textboxKeyTyped(character, code);
        }
    }
}
