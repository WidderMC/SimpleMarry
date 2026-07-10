package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class Request {

    public static int request(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Request"), false);
        return 1;
    }

}
