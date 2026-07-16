package widder.marry.command;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import widder.marry.utils.JsonDataManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Request {
    public static final Map<UUID, UUID> RequestMap = new HashMap<>();
    public static int request(CommandSourceStack source, ServerPlayer target) {
        //Test if the Player runs the Command / if the Target isn't himself / if the player is in a Marriage
        if (PlayerCheck(source,target) == false) return 1;

        //Put the Requester and the Target in a HashMap
        ServerPlayer requester = source.getPlayer();
        RequestMap.put(target.getUUID(), requester.getUUID());

       //Send MSG´s to Player
        SendMSG(requester,target);
        return 1;
    }

    //Test if the Player runs the Command / if the Target isn't himself / if the player is in a Marriage
    private static boolean PlayerCheck(CommandSourceStack source, ServerPlayer target) {
        if (source.getPlayer() == null) {
            source.sendFailure(Component.literal("The Command must be rund by a Player"));
            return false;
        } else if (Objects.equals(source.getPlayer(),target)) {
            source.sendFailure(Component.literal("Sadly, you can't send a request to yourself"));
            return false;
        } else if (JsonDataManager.isMarried(source.getPlayer().getUUID())) {
            source.sendFailure(Component.literal("You can’t request a marriage if you are already in one"));
            return false;
        } else if (JsonDataManager.isMarried(target.getUUID())) {
            source.sendFailure(Component.literal("You can’t request someone who is already in a marriage"));
            return false;
        }
        return true;
    }

    //Build and Send the MSG to the Player
    private static void SendMSG(ServerPlayer requester, ServerPlayer target) {
        MutableComponent tragetMSG = Component.literal("")
                .append(Component.literal(requester.getGameProfile().name()).withColor(0x00FFFF))
                .append(Component.literal(" send you a\nMarry-Request: ")).withColor(0xFFFFFF)
                .append(Component.literal("[Accept] ").withStyle(Style.EMPTY.withClickEvent(new
                        ClickEvent.RunCommand("/Marry accept "+requester.getName().getString())).withColor(0x00AA00)))
                .append(Component.literal("[Deny]").withStyle(Style.EMPTY.withClickEvent(new
                        ClickEvent.RunCommand("/Marry deny")).withColor(0xFF0000)));

        MutableComponent requesterMSG = Component.literal("")
                .append(Component.literal("You sent a Marry-Request\nto: "))
                .append(Component.literal(target.getGameProfile().name()).withColor(0x00FFFF));

        target.sendSystemMessage(tragetMSG);
        requester.sendSystemMessage(requesterMSG);
    }
}