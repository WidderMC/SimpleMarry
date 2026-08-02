package widder.marry.utils;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import widder.marry.Marry;

import java.util.Map;

public record MarriagePayload(Map<String,String> data) implements CustomPacketPayload {

    //Define CustomPayload
    public static final CustomPacketPayload.Type<MarriagePayload> TYPE =
            new Type<MarriagePayload>(Identifier.fromNamespaceAndPath(Marry.MOD_ID,"marry_sync"));

    //Define Codec for reading/writing the payload
    public static final StreamCodec<FriendlyByteBuf,MarriagePayload> CODEC = StreamCodec.ofMember(MarriagePayload::write, MarriagePayload::new);

    //Read Map from buffer
    public MarriagePayload(FriendlyByteBuf buffer) {
        this(buffer.readMap(FriendlyByteBuf::readUtf, FriendlyByteBuf::readUtf));
    }

    //Write Map to buffer
    public void write(FriendlyByteBuf buffer) {
        buffer.writeMap(data, FriendlyByteBuf::writeUtf, FriendlyByteBuf::writeUtf);
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