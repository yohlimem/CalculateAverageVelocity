package net.fabricmc.av.features;

import net.fabricmc.av.AvaregeVelocityMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class FindPlayerVelocity {

    public static List<Vec3d> PositionTimes = new ArrayList<>();

    public static void onPlayerMove(PlayerEntity player){
//        PlayerEntity player = client.player;
        if(player == null) return;
        PositionTimes.add(new Vec3d(player.getX(), player.getY(), player.getZ()));
        if(PositionTimes.size() > 20){
            PositionTimes.remove(0);
        }


    }
    public static Vec3d findPlayerAvaregeVelocity(Integer howMuch){
        howMuch = howMuch == null || howMuch > 20 ? 20 : howMuch;
        Vec3d totalDisplacement = new Vec3d(0, 0, 0);

        if(howMuch <= PositionTimes.size()){
            for (int i = 0; i < howMuch; i++) {
                Vec3d pos1 = PositionTimes.get(i);
                Vec3d pos2 = PositionTimes.get((i + 1) % PositionTimes.size());
                totalDisplacement = totalDisplacement.add(displacement(pos1, pos2));
                AvaregeVelocityMod.LOGGER.info("totalDisplacement: " + totalDisplacement.toString());
            }
            totalDisplacement = new Vec3d(totalDisplacement.x / howMuch, totalDisplacement.y / howMuch, totalDisplacement.z / howMuch);
            AvaregeVelocityMod.LOGGER.info("totalDisplacement after divide: " + totalDisplacement.toString());
            return totalDisplacement;
        }

        return new Vec3d(0, 0, 0);

    }

//    public static boolean playerMoving(PlayerEntity player){
//        AvaregeVelocityMod.LOGGER.info(String.valueOf(player.getMovementSpeed()));
//        return player.isM() > 0.1d;
//
//    }

    public static Vec3d displacement(Vec3d pos1, Vec3d pos2){
        return pos2.subtract(pos1);
    }
}
