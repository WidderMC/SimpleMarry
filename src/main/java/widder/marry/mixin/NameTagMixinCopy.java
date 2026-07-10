package widder.marry.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * DIE FINALE LÖSUNG FÜR SERVER-SIDE NAMETAGS:
 * 
 * Der Grund, warum es bisher nicht ging: Minecraft nutzt für das Rendering über dem Kopf 
 * serverseitig die Methode 'getTabListDisplayName'. Wenn diese Methode einen Wert zurückgibt, 
 * wird dieser Wert in das Paket für den Client geschrieben und dort als NameTag angezeigt.
 */
@Mixin(ServerPlayer.class)
public abstract class NameTagMixinCopy {

    @Unique
    private String hexcode = "#FF3399";

    /**
     * Diese Injektion ist der Schlüssel! 
     * Sie ändert den Namen, der in der Tab-Liste UND über dem Kopf angezeigt wird.
     */
    @Inject(method = "getTabListDisplayName", at = @At("RETURN"), cancellable = true)
    private void onGetTabListDisplayName(CallbackInfoReturnable<Component> cir) {
        ServerPlayer player = (ServerPlayer) (Object) this;
        
        // Wir erstellen den farbigen Namen
        Component coloredName = getColoedName(player);
        
        // Wir setzen den Rückgabewert auf unseren farbigen Namen
        cir.setReturnValue(coloredName);
    }

    @Unique
    private Component getColoedName(ServerPlayer player) {
        TextColor textColor = parseColor(hexcode);
        if (textColor == null) return player.getName();
        
        // Wir bauen den Namen mit dem Hex-Code
        return Component.literal(player.getGameProfile().name()).withStyle(style -> style.withColor(textColor));
    }

    @Unique
    private TextColor parseColor(String color) {
        return TextColor.parseColor(color).result().orElse(null);
    }
}
