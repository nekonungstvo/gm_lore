package ru.konungstvo.gm_tooltip.client.gui.component;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;

import java.util.ArrayList;

public class GuiMultilineTextField extends Gui {
    private int shift = 0;
    private int line = 0;

    private GuiTextField[] guiLines;
    private ArrayList<String> lines = new ArrayList<String>();

    public GuiMultilineTextField(FontRenderer fontRenderer, int xPosition, int yPosition, int width, int height) {
        int amount = height / fontRenderer.FONT_HEIGHT;
        this.guiLines = new GuiTextField[amount];
        for (int i = 0; i < amount; i++) {
            guiLines[i] = new GuiTextField(
                    fontRenderer,
                    xPosition,
                    yPosition + i * fontRenderer.FONT_HEIGHT,
                    width,
                    fontRenderer.FONT_HEIGHT
            );
        }
    }

    private void writeToGuiLines() {
        for (int i = 0; i < this.guiLines.length; i++) {
            int line = this.shift + i;

            if (line < lines.size())
                this.guiLines[i].setText(this.lines.get(line));
        }
    }

    private void readFromGuiLines() {
        for (int i = this.lines.size(); i < this.line; i++) {
            this.lines.add("");
        }

        for (int i = 0; i < this.line - shift; i++) {
            GuiTextField guiLine = this.guiLines[i];
            this.lines.set(this.shift + i, guiLine.getText());
        }
    }

    private void moveToLine(int line) {
        if (line == this.line || line < 0) return;

        int relative_new = line - this.shift;
        int relative_old = this.line - this.shift;
        boolean new_shift = relative_new >= this.guiLines.length || relative_new < 0;

        if (new_shift) {
            this.shift = this.shift + (line - this.line);
            relative_new = line - this.shift;
        }

        this.line = line;

        this.guiLines[relative_old].setFocused(false);
        this.guiLines[relative_new].setFocused(true);

        if (new_shift) this.writeToGuiLines();
    }

    public void drawTextBox() {
        for (GuiTextField guiLine : this.guiLines) {
            guiLine.drawTextBox();
        }
    }

    public void mouseClicked(int x, int y, int z) {
        for (int i = 0; i < guiLines.length; i++) {
            GuiTextField guiLine = this.guiLines[i];

            guiLine.mouseClicked(x, y, z);
            if (guiLine.isFocused()) moveToLine(shift + i);
        }
    }

    public void updateCursorCounter() {
        for (GuiTextField guiLine : this.guiLines) {
            guiLine.updateCursorCounter();
        }
    }

    public void textboxKeyTyped(char character, int code) {
        switch (code) {
            case 200:
                this.moveToLine(this.line - 1);
                break;
            case 208:
                this.moveToLine(this.line + 1);
                break;
            default:
                for (GuiTextField guiLine : this.guiLines) {
                    guiLine.textboxKeyTyped(character, code);
                }
        }

        this.readFromGuiLines();
    }
}
