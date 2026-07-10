package widder.marry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import widder.marry.command.CommandRegistration;
import widder.marry.untils.NameColorHandler;
import widder.marry.untils.NameTagManager;

import java.awt.*;

public class Marry implements ModInitializer {

	public static final String MOD_ID = "marry";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");

		CommandRegistration.RegistCommand();

		/*
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayer player = handler.getPlayer();
			String colorString = "#11FF66";
			TextColor colorCode = TextColor.parseColor(colorString).getOrThrow();

			NameTagManager.applyNameTag(player, colorCode);
		});

		ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
			NameTagManager.removeNameTag(handler.getPlayer());
		});
		 */

		/*
		String colorString = "#11FF66";
		TextColor colorCode = TextColor.parseColor(colorString).getOrThrow();

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			var player = handler.player;

			player.setCustomName(
					Component.literal("test ").withStyle(style -> style.withColor(colorCode))
			);
			player.setCustomNameVisible(true);
		});
		 */

		// In deiner Hauptklasse (Marry.java) oder einem Event-Handler:
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayer player = handler.player;

			// Hier deine Logik, welche Farbe der Spieler haben soll
			// z.B. aus einer Config, Datenbank, oder Marriage-Status:
			int color = 0x112233;
			NameColorHandler.setPlayerColor(player, color);
		});

	}
}