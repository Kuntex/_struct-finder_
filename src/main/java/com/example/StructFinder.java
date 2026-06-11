package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import com.example.gui.FinderMenuScreen;
import org.lwjgl.glfw.GLFW;

public class StructFinder implements ModInitializer, ClientModInitializer {

    private static KeyBinding openMenuKeyBinding;

    @Override
    public void onInitialize() {
        // Тут залишаємо порожньо, бо код суто клієнтський
    }

    @Override
    public void onInitializeClient() {
        // Реєструємо хоткей на кнопку Y для клієнта
        openMenuKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.structfinder.open", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_Y, 
                "category.structfinder"
        ));

        // Слідкуємо за натисканням у кінці кожного тіку
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openMenuKeyBinding.wasPressed() && client.currentScreen == null) {
                client.setScreen(new FinderMenuScreen());
            }
        });
    }
}