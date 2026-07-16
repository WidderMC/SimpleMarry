package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import widder.marry.utils.JsonDataManager;

public class Leave {
    public static int leave(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Leave"),false);
        return 1;
    }
}