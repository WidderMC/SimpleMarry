package widder.marry.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    /*
    @Inject(method = "getTabListDisplayName", at = @At("RETURN"),cancellable = true)
    private void getTabListDisplayName(CallbackInfoReturnable<Component> info) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        String color = "#110066";

        TextColor textColor = parseColor(color);
        if (textColor != null) {
            MutableComponent coloredName = Component.literal(player.getGameProfile().name()).withStyle(style -> style.withColor(textColor));
            info.setReturnValue(coloredName);
        }
    }
     */

    private TextColor parseColor(String color) {
        TextColor hexColor = TextColor.parseColor(color).getOrThrow();
        if (hexColor != null) return hexColor;
        return null;
    }
}