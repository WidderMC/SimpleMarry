package widder.marry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import widder.marry.utils.ClientCache;
import widder.marry.utils.MarriagePayload;

public class MarryClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //Register Global Receiver
        ClientPlayNetworking.registerGlobalReceiver(MarriagePayload.TYPE, ((payload, context) ->
                context.client().execute(() -> ClientCache.replaceALL(payload.data()))));

        //Clear Cache when disconnect
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> ClientCache.clear());
    }
}