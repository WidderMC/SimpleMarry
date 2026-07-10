package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class List {

    public static int list(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("List"), false);
        return 1;
    }

}
