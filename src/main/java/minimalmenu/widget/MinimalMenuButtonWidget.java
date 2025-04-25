package minimalmenu.widget;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MinimalMenuButtonWidget extends ButtonWidget {
  public MinimalMenuButtonWidget(int x, int y, int width, int height, Text text, PressAction onPress) {
    super(
      x,
      y,
      width,
      height,
      text,
      onPress,
      ButtonWidget.DEFAULT_NARRATION_SUPPLIER
    );
  }
}
