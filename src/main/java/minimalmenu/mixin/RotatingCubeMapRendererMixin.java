package minimalmenu.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import minimalmenu.config.ConfigHandler;
import net.minecraft.client.gui.RotatingCubeMapRenderer;

@Mixin(RotatingCubeMapRenderer.class)
public abstract class RotatingCubeMapRendererMixin {
    @Unique private float time;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(DrawContext context, int width, int height, float alpha, float delta, CallbackInfo info) {
        if (ConfigHandler.DIRT_BACKGROUND) {
            Identifier texture = Identifier.ofVanilla("textures/block/dirt.png");
            Screen.renderBackgroundTexture(context, texture, 0, 0, 0, 0, width, height);
            info.cancel();
        } else if (ConfigHandler.STOP_SPIN) {
            time -= delta;
        }
    }
}
