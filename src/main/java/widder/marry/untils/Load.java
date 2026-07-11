package widder.marry.untils;

import net.minecraft.network.chat.TextColor;

public class Load {

    public static String color;
    public static TextColor colorCode;

    public static void LoadData() {
        color = "#FF0066";
        colorCode = TextColor.parseColor(color).getOrThrow();
    }
}