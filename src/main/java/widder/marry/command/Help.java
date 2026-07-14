package widder.marry.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class Help {
    public static int help(CommandContext<CommandSourceStack> context) {
        MutableComponent help = Component.literal("")
                .append(Component.literal("-------- Marry Help Center --------\n").withColor(0xFF00FF))
                .append(Component.literal("/Marry help\n").withColor(0x00FFFF))
                .append(Component.literal("  -> Shows the Help-Page\n").withColor(0xFFFFFF))
                .append(Component.literal("/Marry list\n").withColor(0x00FFFF))
                .append(Component.literal("  -> List all Marriages\n").withColor(0xFFFFFF))
                .append(Component.literal("/Marry request <Player>\n").withColor(0x00FFFF))
                .append(Component.literal("  -> Send a Marry request\n").withColor(0xFFFFFF))
                .append(Component.literal("/Marry accept <Player>\n").withColor(0x00FFFF))
                .append(Component.literal("  -> Accept a Marry request\n").withColor(0xFFFFFF))
                .append(Component.literal("/Marry deny\n").withColor(0x00FFFF))
                .append(Component.literal("  -> Deny a Marry request\n").withColor(0xFFFFFF))
                .append(Component.literal("/Marry color <Hex-Code>\n").withColor(0x00FFFF))
                .append(Component.literal("  -> Change the Name color\n").withColor(0xFFFFFF))
                .append(Component.literal("/Marry leave <Hex-Code>\n").withColor(0x00FFFF))
                .append(Component.literal("  -> Leave your Marriage\n").withColor(0xFFFFFF))
                .append(Component.literal("---------------------------------\n").withColor(0xFF00FF))
                .append(Component.literal("Note: Nametag colors are only visible\n").withColor(0x888888))
                .append(Component.literal("if the mod is also installed clientside\n").withColor(0x888888))
                .append(Component.literal("on your Minecraft game client").withColor(0x888888));
        context.getSource().sendSuccess(() -> help,false);
        return 1;
    }
}