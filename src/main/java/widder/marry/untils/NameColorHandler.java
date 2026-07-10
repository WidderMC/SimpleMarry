package widder.marry.untils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;

public class NameColorHandler {

    /**
     * Setzt die Farbe eines Spielers (Hex-Code, z.B. 0xFF5733).
     * Beeinflusst Tab-Liste, Nametag und Chat.
     */
    public static void setPlayerColor(ServerPlayer player, int hexColor) {
        String originalName = player.getGameProfile().getName();

        MutableComponent coloredName = Component.literal(originalName)
                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(hexColor)));

        // DAS ist der wichtige Aufruf:
        player.setDisplayName(coloredName);

        // Optional: Auch im Tab-List-Eintrag explizit aktualisieren,
        // falls der Client es nicht sofort übernimmt:
        player.refreshTabListName();
    }

    /**
     * Hex-String (z.B. "#FF5733") zu int konvertieren.
     */
    public static int parseHex(String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);
        return Integer.parseInt(hex, 16);
    }
}