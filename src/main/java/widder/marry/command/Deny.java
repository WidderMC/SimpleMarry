package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

import static widder.marry.command.Request.RequestMap;

public class Deny {
    public static int deny(CommandContext<CommandSourceStack> context) {
        //Check if the command is run by a player
        if (context.getSource().getPlayer() == null) {
            context.getSource().sendFailure(Component.literal("The Command must be rund by a Player"));
            //Check if the Player has any Requests
        }else if (!RequestMap.containsKey(context.getSource().getPlayer().getUUID()) && !RequestMap.values().removeIf(UUID ->
                UUID.equals(context.getSource().getPlayer().getUUID()))) {
            context.getSource().sendSuccess(() -> Component.literal("You don’t have any Requests"),false);
            return 0;
            //Deny the Request if the Target runs Deny
        } else if (RequestMap.containsKey(context.getSource().getPlayer().getUUID())) {
            context.getSource().sendSuccess(() -> Component.literal("You denied the request"),false);
            RequestMap.remove(context.getSource().getPlayer().getUUID());
            return 0;
            //Deny the Request if the Requester runs Deny
        } else if (RequestMap.containsValue(context.getSource().getPlayer().getUUID())) {
            context.getSource().sendSuccess(() -> Component.literal("You denied your request"),false);
            RequestMap.values().removeIf(UUID -> UUID.equals(context.getSource().getPlayer().getUUID()));
            return 0;
        }
        return 1;
    }
}