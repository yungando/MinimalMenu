package minimalmenu.mixin;

import minimalmenu.MinimalMenu;
import minimalmenu.config.ConfigHandler;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
    super(title);
}
    @Shadow @Final @Mutable private static Text COPYRIGHT;

    @Inject(method = "init", at = @At("HEAD"))
    protected void initHead(CallbackInfo info) {
        if (ConfigHandler.REMOVE_COPYRIGHT) {
            COPYRIGHT = Text.of("");
        }
        if (ConfigHandler.REMOVE_REALMS) {
            assert this.client != null;
            this.client.options.getRealmsNotifications().setValue(false);
        }
    }

    @Inject(method = "init", at = @At("TAIL"))
    protected void initTail(CallbackInfo info) {
        final List<ClickableWidget> buttons = Screens.getButtons(this);
        Collections.reverse(buttons);

        int spacing = 24;
        int yOffset = 0;

        for (ClickableWidget widget : buttons) {
            if (widget instanceof ButtonWidget button) {
                if (ConfigHandler.REMOVE_SINGLEPLAYER && MinimalMenu.buttonMatchesKey(button, "menu.singleplayer")) {
                    button.visible = false;
                }
                if (ConfigHandler.REMOVE_MULTIPLAYER && MinimalMenu.buttonMatchesKey(button, "menu.multiplayer")) {
                    button.visible = false;
                    yOffset -= spacing;
                }
                if (ConfigHandler.REMOVE_REALMS && MinimalMenu.buttonMatchesKey(button, "menu.online")) {
                    button.visible = false;
                    yOffset -= spacing;
                }
                if (ConfigHandler.REMOVE_LANGUAGE && MinimalMenu.buttonMatchesKey(button, "options.language")) {
                    button.visible = false;
                }
                if (ConfigHandler.REMOVE_ACCESSIBILITY && MinimalMenu.buttonMatchesKey(button, "options.accessibility")) {
                    button.visible = false;
                }

                button.setY(button.getY() - yOffset);
            }
        }
    }
}
