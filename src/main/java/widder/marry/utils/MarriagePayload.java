package widder.marry.utils;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import widder.marry.Marry;

import java.util.HashMap;
import java.util.Map;

public record MarriagePayload(Map<String,String> data) implements CustomPacketPayload {

    //Define CustomPayload
    public static final CustomPacketPayload.Type<MarriagePayload> TYPE =
            new Type<MarriagePayload>(Identifier.fromNamespaceAndPath(Marry.MOD_ID,"marry_sync"));

    //Define Codec for reading/writing the payload
    public static final StreamCodec<FriendlyByteBuf,MarriagePayload> CODEC = StreamCodec.ofMember(MarriagePayload::write, MarriagePayload::new);

    //Read Map from buffer
    public MarriagePayload(FriendlyByteBuf buffer) {
        this(readMap(buffer));
    }

    //Read buffer manually
    private static Map<String,String> readMap(FriendlyByteBuf buffer) {
        int size = buffer.readVarInt();
        Map<String,String> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            String key = buffer.readUtf();
            String value = buffer.readUtf();
            map.put(key,value);
        }
        return map;
    }


    //Write Map manually to buffer
    public void write(FriendlyByteBuf buffer) {
        buffer.writeVarInt(data.size());
        for(Map.Entry<String,String> entry : data.entrySet()) {
            buffer.writeUtf(entry.getKey());
            buffer.writeUtf(entry.getValue());
        }

        //buffer.writeMap(data, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeUtf);
    }

    //Register CustomPayload
    public static void register() {
        PayloadTypeRegistry.clientboundPlay().register(TYPE, CODEC);
    }

    //Return payload type
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}