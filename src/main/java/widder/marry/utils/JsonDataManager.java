package widder.marry.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.TextColor;
import widder.marry.Marry;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonDataManager {
    private static final Path CONFIG_DIR  = FabricLoader.getInstance().getConfigDir().resolve(Marry.MOD_ID);
    private static final Path SAVE_FILE = CONFIG_DIR.resolve("marriages.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type LIST_TYPE = new TypeToken<List<MarriageData>>(){}.getType();
    public static List<MarriageData> marriages = new ArrayList<>();

    //Load config file or create file
    public static void load() {
        try {
            if (Files.notExists(CONFIG_DIR)) {
                Files.createDirectories(CONFIG_DIR);
            }
            if (Files.notExists(SAVE_FILE)) {
                save();
                return;
            }
            try (Reader reader = Files.newBufferedReader(SAVE_FILE,StandardCharsets.UTF_8)) {
                List<MarriageData> loaded = GSON.fromJson(reader, LIST_TYPE);
                if (loaded != null) marriages = loaded;
            }
            Marry.LOGGER.info("Loaded {} marriages", marriages.size());

        } catch (IOException e) {
            Marry.LOGGER.error("Failed to load marriages",e);
        }
    }

    //Save data to file
    public static void save() {
        try {
            Files.createDirectories(CONFIG_DIR);
            try (Writer writer = Files.newBufferedWriter(SAVE_FILE,StandardCharsets.UTF_8)) {
                GSON.toJson(marriages, LIST_TYPE, writer);
            }
        } catch (IOException e) {
            Marry.LOGGER.error("Failed to save marriages",e);
        }
    }

    //Add Marriage to list
    public static void addMarriage(String p1, String p2, String color) {
        marriages.add(new MarriageData(p1,p2,color));
        save();
    }

    //Remove Marriage from list
    public static boolean removeMarriageByPlayer(String player) {
        boolean remove = marriages.removeIf(m -> m.involves(player));
        if (remove) save();
        return remove;
    }

    //Test if Player is in a Marriage
    public static boolean isMarried(String player) {
        return getMarriage(player) != null;
    }

    //Get Player Marriage Array
    public static MarriageData getMarriage(String player) {
        for (MarriageData m : marriages) {
            if (m.involves(player)) return m;
        }
        return null;
    }

    //Get Player color
    public static String getColor(String player) {
        MarriageData m = getMarriage(player);
        if (m != null) {
            return m.color;
        } else {
            return null;
        }
    }

    //get TextColor from Player
    public static TextColor getTextColor(String player) {
        String color = getColor(player);
        if (color == null || color.isEmpty()) return null;
        return TextColor.parseColor(color).getOrThrow();
    }

    //Update Tablist
    public static void updatePlayerTab(net.minecraft.server.MinecraftServer server, MarriageData m) {
        if (m == null) return;

        //Update p1
        net.minecraft.server.level.ServerPlayer p1 = server.getPlayerList().getPlayerByName(m.player1);
        if (p1 != null) {
            server.getPlayerList().broadcastAll(new net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket(
                    net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME, p1
            ));
        }

        //Update p2
        net.minecraft.server.level.ServerPlayer p2 = server.getPlayerList().getPlayerByName(m.player2);
        if (p2 != null) {
            server.getPlayerList().broadcastAll(new net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket(
                    net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME, p2
            ));
        }
    }
}