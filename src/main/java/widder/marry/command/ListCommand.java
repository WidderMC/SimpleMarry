package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;

import widder.marry.utils.MarriageData;

import static widder.marry.utils.JsonDataManager.marriages;

public class ListCommand {
    //List All Marriages
    public static int list(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("A List off all Marriages:"),false);
        //for loop to print all Marriages
        for (MarriageData m : marriages) {
            context.getSource().sendSuccess(() -> Component.literal(m.player1+" ❤ "+m.player2).withStyle(
                    style -> Style.EMPTY.withColor(TextColor.parseColor(m.color).getOrThrow())
            ),false);
        }
        return 1;
    }
}