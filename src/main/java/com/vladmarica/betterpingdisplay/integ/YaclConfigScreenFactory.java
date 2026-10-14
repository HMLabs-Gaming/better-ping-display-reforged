package com.vladmarica.betterpingdisplay.integ;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.vladmarica.betterpingdisplay.BetterPingDisplayMod;
import com.vladmarica.betterpingdisplay.Config;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.awt.Color;
import java.io.IOException;

import static com.vladmarica.betterpingdisplay.BetterPingDisplayMod.LOGGER;

public class YaclConfigScreenFactory implements ConfigScreenFactory<Screen> {

    @Override
    public Screen create(Screen parent) {
        BetterPingDisplayMod mod = BetterPingDisplayMod.instance();
        Config config = mod.getConfig();

        Option<Color> pingTextColorOption =  Option.<Color>createBuilder()
                .name(Component.translatable("betterpingdisplay.settings.pingTextColor"))
                .description(OptionDescription.of(Component.translatable("betterpingdisplay.settings.pingTextColor.description")))
                .binding(config.getTextColor(), config::getTextColor, config::setTextColor)
                .controller(o -> ColorControllerBuilder.create(o).allowAlpha(false))
                .available(!config.shouldAutoColorPingText())
                .build();

        Option<Boolean> autoColorPingTextOption = Option.<Boolean>createBuilder()
                .name(Component.translatable("betterpingdisplay.settings.autoColorPingText"))
                .description(OptionDescription.of(Component.translatable("betterpingdisplay.settings.autoColorPingText.description")))
                .binding(
                        config.shouldAutoColorPingText(),
                        config::shouldAutoColorPingText,
                        config::setShouldAutoColorPingText)
                .controller(o -> BooleanControllerBuilder.create(o).coloured(true))
                .addListener((option, event) -> pingTextColorOption.setAvailable(!option.pendingValue()))
                .build();

        Option<String> textFormatOption = Option.<String>createBuilder()
                .name(Component.translatable("betterpingdisplay.settings.pingTextFormatString"))
                .description(OptionDescription.of(Component.translatable("betterpingdisplay.settings.pingTextFormatString.description")))
                .binding(
                        config.getTextFormatString(),
                        config::getTextFormatString,
                        config::setTextFormatString)
                .controller(StringControllerBuilder::create)
                .build();

        Option<Boolean> renderPingBarsOption = Option.<Boolean>createBuilder()
                .name(Component.translatable("betterpingdisplay.settings.renderPingBars"))
                .description(OptionDescription.of(Component.translatable("betterpingdisplay.settings.renderPingBars.description")))
                .binding(
                        config.shouldRenderPingBars(),
                        config::shouldRenderPingBars,
                        config::setShouldRenderPingBars)
                .controller(o -> BooleanControllerBuilder.create(o).coloured(true))
                .build();

        return YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("betterpingdisplay.settings.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.translatable("betterpingdisplay.settings.title"))
                        .option(autoColorPingTextOption)
                        .option(pingTextColorOption)
                        .option(textFormatOption)
                        .option(renderPingBarsOption)
                        .build())
                .save(() -> {
                    try {
                        config.writeToFile(mod.getConfigFilePath().toFile());
                    } catch (IOException ex) {
                        LOGGER.warn("Failed to write config file", ex);
                    }
                })
                .build()
                .generateScreen(parent);
    }

}
