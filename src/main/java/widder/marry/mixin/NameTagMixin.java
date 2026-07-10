package widder.marry.mixin;

import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.scores.PlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerTeam.class)
public abstract class NameTagMixin {

    /*
    @Inject(method = "setPlayerPrefix",at = @At("RETURN"), cancellable = true)
    private void setPlayerPrefixMixin(Component playerPrefix, CallbackInfoReturnable<Component> info) {


        info.setReturnValue(playerPrefix);
    }
     */



    private TextColor parseColor(String color) {
        TextColor hexColor = TextColor.parseColor(color).getOrThrow();
        if (hexColor != null) return hexColor;
        return null;
    }
}