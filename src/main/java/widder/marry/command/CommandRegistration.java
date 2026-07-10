package widder.marry.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;

public class CommandRegistration {

    public static void RegistCommand() {

        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) ->
                dispatcher.register(Commands.literal("Marry")
                        .then(Commands.literal("list").executes(List::list))
                        .then(Commands.literal("color").executes(Color::color))
                        .then(Commands.literal("accept").executes(Accept::accept))
                        .then(Commands.literal("request").executes(Request::request))));
    }

}
