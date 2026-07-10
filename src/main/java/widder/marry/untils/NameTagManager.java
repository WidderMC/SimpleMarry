package widder.marry.untils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;

public class NameTagManager {

    public static void applyNameTag(ServerPlayer player, TextColor color) {
        MinecraftServer server = player.level().getServer();
        ServerScoreboard scoreboard = server.getScoreboard();
        String teamId = "MarryTag_" + player.getStringUUID().substring(0, 8);
        PlayerTeam team = scoreboard.addPlayerTeam(teamId);
        Component prefix = Component.literal(player.getGameProfile().name()).withStyle(style -> style.withColor(color));
        team.setPlayerPrefix(prefix);



        scoreboard.addPlayerToTeam(player.getScoreboardName(), team);
    }

    public static void removeNameTag(ServerPlayer player) {
        MinecraftServer server = player.level().getServer();

        ServerScoreboard scoreboard = server.getScoreboard();
        String teamId = "MarryTag_" + player.getStringUUID().substring(0, 8);
        PlayerTeam team = scoreboard.getPlayerTeam(teamId);
        if (team != null) {
            scoreboard.removePlayerTeam(team);
        }
    }
}