package widder.marry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import widder.marry.command.CommandRegistration;
import widder.marry.utils.JsonDataManager;

import static widder.marry.command.Request.RequestMap;

public class Marry implements ModInitializer {
	public static final String MOD_ID = "marry";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		//JasonDataManager to create jason file and save stuff
		JsonDataManager.load();

		//RegistCommands
		CommandRegistration.RegistCommand();

		//Remove HashMap´s with the Player how disconnect
		ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
			ServerPlayer player = handler.getPlayer();
			if (RequestMap.containsKey(player.getUUID())) {
				RequestMap.remove(player.getUUID());
			}else if (RequestMap.containsValue(player.getUUID())) {
				RequestMap.values().removeIf(UUID -> UUID.equals(player.getUUID()));
			}
		});


		//Print Info after everything is loaded
		LOGGER.info("Marry Mod Successfully loaded");
	}
}