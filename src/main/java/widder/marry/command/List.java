package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import static widder.marry.command.Request.RequestMap;

public class List {

    public static int list(CommandContext<CommandSourceStack> context) {

        //temp
        context.getSource().sendSuccess(() -> Component.literal(RequestMap.toString()), false);



        context.getSource().sendSuccess(() -> Component.literal("List"), false);
        return 1;
    }
}