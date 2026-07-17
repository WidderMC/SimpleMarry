package widder.marry.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import widder.marry.utils.MarriageData;

import static widder.marry.utils.JsonDataManager.marriages;

public class ListCommand {

    public static int list(CommandContext<CommandSourceStack> context) {
        //context.getSource().sendSuccess(() -> Component.literal(marriages.toString()),false);
        context.getSource().sendSuccess(() -> Component.literal("List"),false);


        MinecraftServer server = context.getSource().getServer();

        for (MarriageData m : marriages) {


            context.getSource().sendSuccess(() -> Component.literal(""),false);
        }


        return 1;
    }
}