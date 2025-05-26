package net.chibify.chibify.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.lang.reflect.Field;
import java.net.URI;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ModConfigScreen {
    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.of("My Mod Configuration"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.of("General"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Text.of("Self-Chibi"))
                                .description(OptionDescription.of(Text.of("Render yourself as chibi")))
                                .binding(true, () -> ModConfig.shrinkSelf, newValue -> ModConfig.shrinkSelf = newValue)
                                .controller(BooleanControllerBuilder::create)
                                .build())

                        .option(Option.<Boolean>createBuilder()
                                .name(Text.of("AccurateEyePos"))
                                .description(OptionDescription.of(Text.of("Lower the eye pos to be more accurate when shrunk")))
                                .binding(true, () -> ModConfig.AccurateEyeHeight, newValue -> ModConfig.AccurateEyeHeight = newValue)
                                .controller(BooleanControllerBuilder::create)
                                .build())
                        .build())
                .save(ModConfig::save)
                .build()
                .generateScreen(parent);
    }
}