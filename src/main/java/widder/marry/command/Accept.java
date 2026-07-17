package widder.marry.command;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import widder.marry.utils.JsonDataManager;

import java.util.List;
import java.util.Random;

import static widder.marry.command.Request.RequestMap;

public class Accept {

    public static int accept(CommandSourceStack source, ServerPlayer requester) {
        ServerPlayer target = source.getPlayer();
        //Check if the command is run by a player
        if (source.getPlayer() == null) {
            source.sendFailure(Component.literal("The Command must be rund by a Player"));
            return 0;
            //Check if Player is in a Marriage
        } else if (JsonDataManager.isMarried(source.getPlayer().getUUID())) {
            source.sendFailure(Component.literal("You can’t request a marriage if you are already in one"));
            return 0;
            //Check if the Player has put in his own name
        } else if (requester == target) {
            source.sendFailure(Component.literal("You don’t have any Requests from yourself"));
            return 0;
            //Check if the Player is in the HashMap
        } else if (!RequestMap.containsKey(target.getUUID())) {
            source.sendFailure(Component.literal("You don’t have any Requests or the Player went Offline"));
            return 0;
            //Check if the Player has a Request from the named name
        } else if (!RequestMap.get(target.getUUID()).equals(requester.getUUID())) {
            source.sendFailure(Component.literal("You don’t have any Requests from this Player"));
            return 0;
        }

        //Create Marriage
        JsonDataManager.addMarriage(requester.getUUID(), target.getUUID(),randomColor());

        //Spawn fireworks and Hearts
        summonFireAndHeart(requester, source.getPlayer());

        //Remove from the Request Map
        RequestMap.values().removeIf(UUID -> UUID.equals(source.getPlayer().getUUID()));
        RequestMap.remove(source.getPlayer().getUUID());

        //Announce
        source.sendSuccess(() -> Component.literal("The Player "+requester.getName()+" and "+target.getName()+" are now Married."),true);
        return 1;
    }

    //create Random color
    private static String randomColor() {
        int i = 0;
        String colorPalet = "0123456789ABCDEF";
        String color = "#";
        while (i <= 5) {
            Random random = new Random();
            int r = random.nextInt(16)+ 1;
            color = color + colorPalet.charAt(r-1);
            i ++;
        }
        return color;
    }

    private static void summonFireAndHeart(ServerPlayer p1, ServerPlayer p2) {
        //Spawn Hearts
        summonHeart(p1);
        summonHeart(p2);

        //Summon Fireworks
        summonFireworks(p1);
        summonFireworks(p2);
    }

    private static void summonHeart (ServerPlayer player) {
        player.level().sendParticles(ParticleTypes.HEART, player.getX(),player.getY()+1,player.getZ(),15, 0.8,0.5,0.8,0.1);
    }

    private static void summonFireworks(ServerPlayer player) {
        ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);
        IntArrayList color = new IntArrayList();
        color.add(0xFF0000);
        FireworkExplosion explosion = new FireworkExplosion(
                FireworkExplosion.Shape.STAR,
                color,
                new IntArrayList(),
                true,
                true
        );
        rocket.set(DataComponents.FIREWORKS, new Fireworks(1, List.of(explosion)));
        FireworkRocketEntity firework = new FireworkRocketEntity(player.level(),player.getX(),player.getY()+1,player.getZ(),rocket);
        player.level().addFreshEntity(firework);
    }
}