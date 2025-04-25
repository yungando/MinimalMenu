package minimalmenu.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import minimalmenu.MinimalMenu;
import minimalmenu.config.ConfigHandler;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.List;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {
  protected OptionsScreenMixin(Text title) {
    super(title);
  }

  @Inject(method = "init", at = @At("TAIL"))
  private void init(CallbackInfo ci) {
    final List<ClickableWidget> buttons = Screens.getButtons(this);

    for (ClickableWidget widget : buttons) {
      if (widget instanceof ButtonWidget button) {
        if (ConfigHandler.REMOVE_ONLINE) {
          if (MinimalMenu.buttonMatchesKey(button, "options.online")) {
            button.visible = false;
          }
          if (MinimalMenu.buttonMatchesKey(button, "options.fov")) {
            button.setWidth(310);
          }
        }
        if (ConfigHandler.REMOVE_CREDITS) {
          if (MinimalMenu.buttonMatchesKey(button, "options.credits_and_attribution")) {
            button.visible = false;
          }
        }
      }
    }
  }
}
