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

    private String text = "";

    private boolean isFocused = false;

    private int cursorPosition = 0;
    private int cursorCounter = 0;


    public GuiMultilineTextField(FontRenderer fontRenderer, int xPosition, int yPosition, int width, int height) {
        this.fontRenderer = fontRenderer;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    private String getDisplayedText() {
        return this.text.substring(0, cursorPosition);
    }

    private void drawBackground() {
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
    }

    private void drawText() {
        this.fontRenderer.drawSplitString(
                text,
                1 + xPosition,
                yPosition,
                width -1,
                Color.WHITE.getRGB()
        );
    }

    private void drawCursor() {
        boolean is_visible = cursorCounter % 16 > 4;

        if (is_visible && this.isFocused) {
            String displayedText = this.getDisplayedText();

            AbstractList<String> lines = (AbstractList<String>) this.fontRenderer.listFormattedStringToWidth(
                    displayedText,
                    this.width
            );

            String current_line = lines.get(lines.size() - 1);

            int xShift = this.fontRenderer.getStringWidth(current_line);
            int yShift = this.fontRenderer.FONT_HEIGHT * (lines.size() - 1);

            drawRect(
                    this.xPosition + xShift,
                    this.yPosition + yShift,
                    this.xPosition + xShift + 2,
                    this.yPosition + yShift + fontRenderer.FONT_HEIGHT,
                    Color.LIGHT_GRAY.getRGB()
            );
        }
    }

    public void draw() {
        this.drawBackground();
        this.drawText();
        this.drawCursor();
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
            this.cursorPosition--;
        }
    }

    private void write(String addition) {
        this.text = new StringBuilder(text).insert(this.text.length(), addition).toString();
        this.cursorPosition += addition.length();
    }
}
