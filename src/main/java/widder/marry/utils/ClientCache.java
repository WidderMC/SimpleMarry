package widder.marry.utils;

import java.util.HashMap;
import java.util.Map;

public class ClientCache {

    //Create Public string
    public static final Map<String, String> Marriages = new HashMap<>();
    public ClientCache() {}

    //Replace Marriages Map with a new Marriages Map
    public static void replaceALL(Map<String, String> newMarriages) {
        Marriages.clear();
        Marriages.putAll(newMarriages);
    }

    //Clear Marriages Map
    public static void clear() {
        Marriages.clear();
    }
}
