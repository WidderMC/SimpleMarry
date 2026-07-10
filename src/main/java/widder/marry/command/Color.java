package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class Color {

    public static int color(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Color"), false);
        return 1;
    }

}
