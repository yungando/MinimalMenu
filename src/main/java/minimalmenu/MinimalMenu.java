package minimalmenu;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.TextContent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import minimalmenu.config.ConfigHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Language;

public class MinimalMenu implements ClientModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_NAME = "MinimalMenu";

    @Override
    public void onInitializeClient() {
        ConfigHandler.read();
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    public static void printButtonInfo(Screen screen) {
        log(Level.INFO,"-------------------------------------");
        log(Level.INFO, screen.getClass().getName());

        Language language = Language.getInstance();
        List<ClickableWidget> buttons = Screens.getButtons(screen);
        for (ClickableWidget button : buttons) {
            Text buttonMessage = button.getMessage();
            TextContent textContent = buttonMessage.getContent();
            String buttonText = buttonMessage.getString();
            
            log(Level.INFO,"-------------------------------------");
            log(Level.INFO, "Button localized: " + buttonText);
            log(Level.INFO, "Button index:     " + buttons.indexOf(button));

            if (textContent instanceof TranslatableTextContent) {
                String buttonLangKey = ((TranslatableTextContent) textContent).getKey();
                String buttonLangText = language.get(buttonLangKey);

                log(Level.INFO, "Button key:       " + buttonLangKey);
                if (!buttonText.equals(buttonLangText)) {
                    log(Level.INFO, "Button text:      " + buttonLangText);
                }

                Object[] textArgs = ((TranslatableTextContent) textContent).getArgs();
                for (int i = 0; i < textArgs.length; i++) {
                    Object arg = textArgs[i];
                    if (arg instanceof TranslatableTextContent) {
                        String argKey = ((TranslatableTextContent) arg).getKey();
                        log(Level.INFO, "");
                        log(Level.INFO, "Text arg " + i + " key:   " + argKey);
                        log(Level.INFO, "Text arg " + i + " text:  " + language.get(argKey));
                    }
                }
            }
        }
        log(Level.INFO,"-------------------------------------");
    }

    public static boolean buttonMatchesKey(Widget widget, String... translationKeys) {
        if (widget instanceof ClickableWidget button) {
            Text text = button.getMessage();
            TextContent textContent = text.getContent();

            return textContent instanceof TranslatableTextContent && Arrays.stream(translationKeys)
              .anyMatch(s -> ((TranslatableTextContent) textContent).getKey().equals(s));
        }
        return false;
    }
}
