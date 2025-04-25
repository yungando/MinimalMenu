package minimalmenu.mixin;

import com.terraformersmc.modmenu.event.ModMenuEventHandler;
import minimalmenu.config.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModMenuEventHandler.class)
public class ModMenuEventHandlerMixin {
  @ModifyArg(method = "afterTitleScreenInit", at = @At(value = "INVOKE", target = "Lcom/terraformersmc/modmenu/event/ModMenuEventHandler;buttonHasText(Lnet/minecraft/client/gui/widget/Widget;[Ljava/lang/String;)Z"), index = 1)
  private static String[] moveModMenuButton(String[] translationKeys) {
    if (ConfigHandler.REMOVE_REALMS) {
      if (!ConfigHandler.REMOVE_MULTIPLAYER) {
        return new String[]{"menu.multiplayer"};
      }
      return new String[]{"menu.singleplayer"};
    }
    return translationKeys;
  }
}
