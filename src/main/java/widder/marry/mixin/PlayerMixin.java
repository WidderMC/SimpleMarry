package widder.marry.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import widder.marry.utils.JsonDataManager;

@Mixin(Player.class)
public class PlayerMixin {

    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void getDisplayNameMixin(CallbackInfoReturnable<Component> info) {
        Player player = (Player)(Object)this;
        TextColor textColor = JsonDataManager.getTextColor(player.getName().getString());
        if (textColor != null) {
            MutableComponent coloredName = Component.literal(player.getGameProfile().name()).withStyle(style -> style.withColor(textColor));
            info.setReturnValue(coloredName);
        }
    }
}