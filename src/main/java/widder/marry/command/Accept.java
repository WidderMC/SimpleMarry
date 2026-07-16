package widder.marry.command;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import widder.marry.utils.JsonDataManager;

import static widder.marry.command.Request.RequestMap;

public class Accept {

    public static int accept(CommandSourceStack source, ServerPlayer requester) {
        ServerPlayer target = source.getPlayer();
        //Check if the command is run by a player
        if (source.getPlayer() == null) {
            source.sendFailure(Component.literal("The Command must be rund by a Player"));
            return 0;
            //Check if Player is in a Marriage
        } else if (JsonDataManager.isMarried(source.getPlayer().getUUID())) {
            source.sendFailure(Component.literal("You can’t request a marriage if you are already in one"));
            return 0;
            //Check if the Player has put in his own name
        } else if (requester == target) {
            source.sendFailure(Component.literal("You don’t have any Requests from yourself"));
            return 0;
            //Check if the Player is in the HashMap
        } else if (!RequestMap.containsKey(target.getUUID())) {
            source.sendFailure(Component.literal("You don’t have any Requests or the Player went Offline"));
            return 0;
            //Check if the Player has a Request from the named name
        } else if (!RequestMap.get(target.getUUID()).equals(requester.getUUID())) {
            source.sendFailure(Component.literal("You don’t have any Requests from this Player"));
            return 0;
        }



        source.sendSuccess(() -> Component.literal(""+target.getName().getString()+" + "+requester.getName().getString()),false);

        return 1;
    }
}