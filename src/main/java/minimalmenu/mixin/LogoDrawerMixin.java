package minimalmenu.mixin;

import minimalmenu.config.ConfigHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.LogoDrawer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LogoDrawer.class)
public class LogoDrawerMixin {
  @Shadow @Final @Mutable public static Identifier EDITION_TEXTURE;

  @Inject(method = "draw(Lnet/minecraft/client/gui/DrawContext;IFI)V", at = @At("HEAD"))
  private void replaceEditionTexture(DrawContext context, int screenWidth, float alpha, int y, CallbackInfo ci) {
    if (ConfigHandler.REMOVE_EDITION) {
      EDITION_TEXTURE = Identifier.of("minimalmenu", "textures/gui/title/edition_empty.png");
    } else {
      EDITION_TEXTURE = Identifier.ofVanilla("textures/gui/title/edition.png");
    }
  }
}
