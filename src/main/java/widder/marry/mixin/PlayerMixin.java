package widder.marry.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {

    /*
    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void getDisplayNameMixin(CallbackInfoReturnable<Component> info) {
        Player player = (Player)(Object)this;

        if (!player.level().isClientSide()) {
            String color = "#11FF66";

            TextColor textColor = parseColor(color);
            if (textColor != null) {
                MutableComponent coloredName = Component.literal(player.getGameProfile().name()).withStyle(style -> style.withColor(textColor));

                info.setReturnValue(coloredName);
            }
        }
    }
     */

    private TextColor parseColor(String color) {
        TextColor hexColor = TextColor.parseColor(color).getOrThrow();
        if (hexColor != null) return hexColor;
        return null;
    }
}