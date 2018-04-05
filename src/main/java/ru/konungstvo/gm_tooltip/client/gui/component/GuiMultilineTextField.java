package ru.konungstvo.gm_tooltip.client.gui.component;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;

public class GuiMultilineTextField extends Gui {
    private int shift = 0;
    private GuiTextField[] lines;

    public GuiMultilineTextField(FontRenderer fontRenderer, int xPosition, int yPosition, int width, int height) {
        int amount = height / fontRenderer.FONT_HEIGHT;
        this.lines = new GuiTextField[amount];
        for (int i = 0; i < amount; i++) {
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
        for (GuiTextField line : this.lines) {
            line.drawTextBox();
        }
    }

    public void mouseClicked(int x, int y, int z) {
        for (GuiTextField line : this.lines) {
            line.mouseClicked(x, y, z);
        }
    }

    public void updateCursorCounter() {
        for (GuiTextField line : this.lines) {
            line.updateCursorCounter();
        }
    }

    public void textboxKeyTyped(char character, int code) {
        for (GuiTextField line : this.lines) {
            line.textboxKeyTyped(character, code);
        }
    }
}
