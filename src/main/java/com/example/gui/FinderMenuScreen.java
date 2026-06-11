package com.example.gui;

import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.util.math.MatrixStack;
import com.example.ScannerLogic;

public class FinderMenuScreen extends Screen {

    public FinderMenuScreen() {
        super(Text.literal("Сканер прогружених чанків"));
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int spacing = 24;
        
        int startX = this.width / 2 - buttonWidth / 2;
        int startY = this.height / 2 - 50;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Шукати Спавнери"), button -> {
            ScannerLogic.scanForBlock(Blocks.SPAWNER);
            this.close();
        }).dimensions(startX, startY, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Шукати Скрині"), button -> {
            ScannerLogic.scanForBlock(Blocks.CHEST);
            this.close();
        }).dimensions(startX, startY + spacing, buttonWidth, buttonHeight).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Шукати Портал в Енд"), button -> {
            ScannerLogic.scanForBlock(Blocks.END_PORTAL_FRAME);
            this.close();
        }).dimensions(startX, startY + (spacing * 2), buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredTextWithShadow(matrices, this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPauseGame() {
        return false;
    }
}