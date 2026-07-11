package widder.marry.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static widder.marry.untils.Load.colorCode;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @Inject(method = "getTabListDisplayName", at = @At("RETURN"),cancellable = true)
    private void getTabListDisplayName(CallbackInfoReturnable<Component> info) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        if (colorCode != null) {
            MutableComponent coloredName = Component.literal(player.getGameProfile().name()).withStyle(style -> style.withColor(colorCode));
            info.setReturnValue(coloredName);
        }
    }
}