package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class Accept {

    public static int accept(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Accept"), false);
        return 1;
    }

}
