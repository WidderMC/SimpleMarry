package widder.marry.command;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import widder.marry.utils.JsonDataManager;
import widder.marry.utils.MarriageData;

import static widder.marry.utils.JsonDataManager.marriages;
import static widder.marry.utils.JsonDataManager.updatePlayerTab;

public class Color {

    public static int color(CommandSourceStack source, String color) {
        //Check if the command is run by a player
        if (source.getPlayer() == null) {
            source.sendFailure(Component.literal("The Command must be rund by a Player"));
            return 0;
            //Check if player is in a marriage
        }else if (!(JsonDataManager.isMarried(source.getPlayer().getName().getString()))) {
            source.sendFailure(Component.literal("You can’t change the color if you aren’t in a marriage"));
            return 0;
            //Check if it starts with #
        } else if (color.startsWith("#")) {
            source.sendFailure(Component.literal("The color should begin with #"));
            return 0;
            //check if it hast a length of 6
        } else if (!(color.length() == 6)) {
            source.sendFailure(Component.literal("The color must have 6 characters"));
            return 0;
        }

        //Check if it's a valid hex code and change it
        if (isValidHex(color)) {
            source.sendSuccess(() -> Component.literal("Color updatet to #"+color), false);
            MarriageData m = JsonDataManager.getMarriage(source.getPlayer().getName().getString());
            m.color = "#"+ color;
            JsonDataManager.save();
            JsonDataManager.updatePlayerTab(source.getServer());
            return 1;
        }else {
            source.sendFailure(Component.literal(color + " isn´t a Hex code"));
            return 0;
        }
    }

    private static boolean isValidHex(String color) {
        //Check each character
        for (char c : color.toCharArray()) {
            if (!((c >= '0' && c <= '9') ||
            c >= 'a' && c <= 'f' ||
            c >= 'A' && c <= 'F')) {
                return false;
            }
        }
        return true;
    }
}