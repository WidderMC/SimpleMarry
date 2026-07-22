package widder.marry.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;

public class CommandRegistration {

    public static void RegistCommand() {
        //RegistCommands
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) ->
                dispatcher.register(Commands.literal("Marry")
                        .then(Commands.literal("list").executes(ListCommand::list))
                        .then(Commands.literal("color").
                                then(Commands.argument("hexcode", StringArgumentType.word())
                                        .executes(context ->
                                                Color.color(context.getSource(), StringArgumentType.getString(context,"hexcode")))))
                        .then(Commands.literal("request").
                                then(Commands.argument("player", EntityArgument.player())
                                        .executes(context ->
                                                Request.request(context.getSource(),EntityArgument.getPlayer(context, "player")))))
                        .then(Commands.literal("accept").
                                then(Commands.argument("player", EntityArgument.player())
                                        .executes(context ->
                                                Accept.accept(context.getSource(),EntityArgument.getPlayer(context, "player")))))
                        .then(Commands.literal("leave").executes(Leave::leave))
                        .then(Commands.literal("deny").executes(Deny::deny))
                        .then(Commands.literal("help").executes(Help::help))));
    }
}