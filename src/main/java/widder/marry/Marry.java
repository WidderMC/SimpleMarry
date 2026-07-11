package widder.marry;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import widder.marry.command.CommandRegistration;
import widder.marry.untils.Load;

public class Marry implements ModInitializer {

	public static final String MOD_ID = "marry";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");

		Load.LoadData();

		CommandRegistration.RegistCommand();
	}
}