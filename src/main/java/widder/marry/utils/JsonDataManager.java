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
import java.util.UUID;

public class JsonDataManager {
    private static final Path CONFIG_DIR  = FabricLoader.getInstance().getConfigDir().resolve(Marry.MOD_ID);
    private static final Path SAVE_FILE = CONFIG_DIR.resolve("marriages.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type LIST_TYPE = new TypeToken<List<MarriageData>>(){}.getType();
    private static List<MarriageData> marriages = new ArrayList<>();

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

    public static void addMarriage(UUID p1, UUID p2, String color) {
        marriages.add(new MarriageData(p1,p2,color));
        save();
    }

    public static boolean removeMarriageByPlayer(UUID player) {
        boolean remove = marriages.removeIf(m -> m.involves(player));
        if (remove) save();
        return remove;
    }

    public static boolean isMarried(UUID player) {
        return getMarriage(player) != null;
    }

    public static MarriageData getMarriage(UUID player) {
        for (MarriageData m : marriages) {
            if (m.involves(player)) return m;
        }
        return null;
    }

    public static String getColor(UUID player) {
        MarriageData m = getMarriage(player);
        if (m != null) {
            return m.color;
        } else {
            return null;
        }
    }

    public static TextColor getTextColor(UUID player) {
        String color = getColor(player);
        if (color == null || color.isEmpty()) return null;
        return TextColor.parseColor(color).getOrThrow();
    }
}