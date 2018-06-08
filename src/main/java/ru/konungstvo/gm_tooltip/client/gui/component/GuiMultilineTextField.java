package ru.konungstvo.gm_tooltip.client.gui.component;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;

import java.awt.Color;
import java.util.AbstractList;
import java.util.List;

public class GuiMultilineTextField extends Gui {
    private final FontRenderer fontRenderer;

    private final int xPosition;
    private final int yPosition;
    private final int width;
    private final int height;

    private int cursorCounter = 0;
    private String text = "";

    private boolean isFocused = false;

    public GuiMultilineTextField(FontRenderer fontRenderer, int xPosition, int yPosition, int width, int height) {
        this.fontRenderer = fontRenderer;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    public void draw() {
        drawRect(
                this.xPosition - 1,
                this.yPosition - 1,
                this.xPosition + this.width + 1,
                this.yPosition + this.height + 1,
                Color.GRAY.getRGB()
        );

        drawRect(
                this.xPosition,
                this.yPosition,
                this.xPosition + this.width,
                this.yPosition + this.height,
                Color.BLACK.getRGB()
        );

        this.fontRenderer.drawSplitString(
                text,
                1 + xPosition,
                yPosition,
                width -1,
                Color.WHITE.getRGB()
        );
    }

    public void mouseClicked(int x, int y, int z) {
        this.isFocused = x >= this.xPosition && x < this.xPosition + this.width;
        this.isFocused &= y >= this.yPosition && y < this.yPosition + this.height;
    }

    public void updateCursorCounter() {
        ++cursorCounter;
    }

    public boolean keyTyped(char character, int code) {
        if (!isFocused) return false;

        if (code == 47 && GuiScreen.isCtrlKeyDown()) {
            this.write(GuiScreen.getClipboardString());
            return true;
        }

        switch (code) {
            case 14:
                this.backspace();
                return true;
            case 28:
            case 156:
                this.write("\n");
                return true;
            default:
                if (ChatAllowedCharacters.isAllowedCharacter(character)) {
                    this.write(Character.toString(character));
                    return true;
                }
        }

        return false;
    }

    private void backspace() {
        if (this.text.length() > 0) {
            this.text = new StringBuilder(text).deleteCharAt(this.text.length() - 1).toString();
        }
    }

    private void write(String addition) {
        this.text = new StringBuilder(text).insert(this.text.length(), addition).toString();
    }
}
