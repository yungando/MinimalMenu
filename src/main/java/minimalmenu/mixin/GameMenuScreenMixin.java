package minimalmenu.mixin;

import minimalmenu.MinimalMenu;
import minimalmenu.config.ConfigHandler;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }
    
    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo info) {
        final List<ClickableWidget> buttons = Screens.getButtons(this);

        final int buttonWidth = 204;
        int spacing = 24;
        int yOffset = 0;

        for (ClickableWidget widget : buttons) {
            if (widget instanceof ButtonWidget button) {
                if (ConfigHandler.REMOVE_FEEDBACK) {
                    if (MinimalMenu.buttonMatchesKey(button, "menu.sendFeedback")) {
                        button.visible = false;
                    }
                    if (!ConfigHandler.REMOVE_BUGS) {
                        if (MinimalMenu.buttonMatchesKey(button, "menu.reportBugs")) {
                            button.setWidth(buttonWidth);
                            button.setX(this.width / 2 - buttonWidth / 2);
                        }
                    }
                }

                if (ConfigHandler.REMOVE_BUGS) {
                    if (MinimalMenu.buttonMatchesKey(button, "menu.reportBugs")) {
                        button.visible = false;
                        if (ConfigHandler.REMOVE_FEEDBACK) {
                            yOffset += spacing;
                        }
                    }
                    if (!ConfigHandler.REMOVE_FEEDBACK) {
                        if (MinimalMenu.buttonMatchesKey(button, "menu.sendFeedback")) {
                            button.setWidth(buttonWidth);
                            button.setX(this.width / 2 - buttonWidth / 2);
                        }
                    }
                }
                button.setY(button.getY() - yOffset);
            }
        }
    }
}
