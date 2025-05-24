package minimalmenu.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {

    public static Screen getConfigScreen(Screen parentScreen) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parentScreen)
                .setTitle(Text.translatable("minimalmenu.config.title"));

        builder.setSavingRunnable(ConfigHandler::write);

        //Create categories
        ConfigCategory titleScreen = builder.getOrCreateCategory(Text.translatable("minimalmenu.config.category.title"));
        ConfigCategory pauseScreen = builder.getOrCreateCategory(Text.translatable("minimalmenu.config.category.pause"));
        ConfigCategory optionsScreen = builder.getOrCreateCategory(Text.translatable("minimalmenu.config.category.options"));
        ConfigCategory otherOptions = builder.getOrCreateCategory(Text.translatable("minimalmenu.config.category.other"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //Build title screen options
        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.splash"), ConfigHandler.REMOVE_SPLASH)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_SPLASH = newValue)
                .build());

        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.edition"), ConfigHandler.REMOVE_EDITION)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_EDITION = newValue)
                .build());

        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.singleplayer"), ConfigHandler.REMOVE_SINGLEPLAYER)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_SINGLEPLAYER = newValue)
                .build());

        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.multiplayer"), ConfigHandler.REMOVE_MULTIPLAYER)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_MULTIPLAYER = newValue)
                .build());

        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.realms"), ConfigHandler.REMOVE_REALMS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_REALMS = newValue)
                .build());

        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.accessibility"), ConfigHandler.REMOVE_ACCESSIBILITY)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_ACCESSIBILITY = newValue)
                .build());

        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.language"), ConfigHandler.REMOVE_LANGUAGE)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_LANGUAGE = newValue)
                .build());

        titleScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.title.copy"), ConfigHandler.REMOVE_COPYRIGHT)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_COPYRIGHT = newValue)
                .build());

        //Build options screen options
        optionsScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.options.online"), ConfigHandler.REMOVE_ONLINE)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_ONLINE = newValue)
                .build());

        optionsScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.options.credits"), ConfigHandler.REMOVE_CREDITS)
          .setDefaultValue(false)
          .setSaveConsumer(newValue -> ConfigHandler.REMOVE_CREDITS = newValue)
          .build());

        optionsScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.options.telemetry"), ConfigHandler.REMOVE_TELEMETRY)
          .setDefaultValue(false)
          .setSaveConsumer(newValue -> ConfigHandler.REMOVE_TELEMETRY = newValue)
          .build());

        //Build pause screen options
        pauseScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.pause.feedback"), ConfigHandler.REMOVE_FEEDBACK)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_FEEDBACK = newValue)
                .build());

        pauseScreen.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.pause.bugs"), ConfigHandler.REMOVE_BUGS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigHandler.REMOVE_BUGS = newValue)
                .build());

        //Build other options
        otherOptions.addEntry(entryBuilder.startBooleanToggle(Text.translatable("minimalmenu.config.option.other.dev"), ConfigHandler.DEV_MODE)
                .setDefaultValue(false)
                .setTooltip(Text.translatable("minimalmenu.config.option.other.dev.tooltip"))
                .setSaveConsumer(newValue -> ConfigHandler.DEV_MODE = newValue)
                .build());

        return builder.build();
    }
}
