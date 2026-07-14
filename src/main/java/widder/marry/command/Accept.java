package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import static widder.marry.command.Request.RequestMap;

public class Accept {

    public static int accept(CommandContext<CommandSourceStack> context) {
        //Check if the command is run by a player
        if (context.getSource().getPlayer() == null) {
            context.getSource().sendFailure(Component.literal("The Command must be rund by a Player"));
            return 0;
        //Check if the Player is Online
        }else if (!RequestMap.containsKey(context.getSource().getPlayer().getUUID())) {
            context.getSource().sendSuccess(() -> Component.literal("You dount have any Requests or\nthe Player isn't online"),false);
            return 0;
        //Create Marry
        }else {
            //------------------------------------------------------------------------------------------------------------------
        }

        //Return Error-Code (Should never happen)
        context.getSource().sendFailure(Component.literal("Error: Accept-1"));
        return 1;
    }
}