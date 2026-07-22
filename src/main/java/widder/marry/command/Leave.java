package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import widder.marry.utils.JsonDataManager;

public class Leave {
    public static int leave(CommandContext<CommandSourceStack> context) {
        //Check if the command is run by a player
        if (context.getSource().getPlayer() == null) {
            context.getSource().sendFailure(Component.literal("The Command must be rund by a Player"));
            return 0;
            //Check if player is in a marriage
        }else if (!(JsonDataManager.isMarried(context.getSource().getPlayer().getName().getString()))) {
            context.getSource().sendFailure(Component.literal("You can’t leave if you aren’t in a marriage"));
            return 0;
        }
        //Remove Marriage
        JsonDataManager.removeMarriageByPlayer(context.getSource().getPlayer().getName().getString());
        JsonDataManager.updatePlayerTab(context.getSource().getServer());
        context.getSource().sendSuccess(() -> Component.literal("You left the marriage"),false);
        return 1;
    }
}