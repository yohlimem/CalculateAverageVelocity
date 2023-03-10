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
        PositionTimes.add(player.getPos());
        if(PositionTimes.size() > 20){
//            AvaregeVelocityMod.LOGGER.info(PositionTimes.toString());
            PositionTimes.remove(0);
        }


    }
    public static Vec3d findPlayerAvaregeVelocity(Integer accuracy, Integer timeScale){
        accuracy = (accuracy == null || accuracy > 20 ? 20 : accuracy);
        timeScale = timeScale == null ? 1 : timeScale;


        Vec3d totalDisplacement = new Vec3d(0, 0, 0);

        if(accuracy <= PositionTimes.size()){
            for (int i = 0; i < accuracy; i++) {
                Vec3d pos1 = PositionTimes.get(i);
                if((i + 1) >= PositionTimes.size()){
                    accuracy--;
                    break;
                }

                Vec3d pos2 = PositionTimes.get(i+1);


//                AvaregeVelocityMod.LOGGER.info("pos1: " + pos1.toString());
//                AvaregeVelocityMod.LOGGER.info("pos2: " + pos2.toString());
//                AvaregeVelocityMod.LOGGER.info(displacement(pos1, pos2).toString());
                totalDisplacement = totalDisplacement.add(displacement(pos1, pos2));
//                AvaregeVelocityMod.LOGGER.info("totalDisplacement: " + totalDisplacement.toString());
//                AvaregeVelocityMod.LOGGER.info("i: " + i);
            }
            totalDisplacement = new Vec3d(totalDisplacement.x / accuracy, totalDisplacement.y / accuracy, totalDisplacement.z / accuracy);
//            AvaregeVelocityMod.LOGGER.info("totalDisplacement after divide: " + (totalDisplacement.multiply(20)).toString());
            // To get the velocity of the player by seconds: *20
            // To get the velocity of the player by Minutes: *1_200
            // To get the velocity of the player by Hours: *72_000
            return totalDisplacement.multiply(timeScale);
        }

        return new Vec3d(0, 0, 0);

    }


    public static Vec3d displacement(Vec3d pos1, Vec3d pos2){
        return pos2.subtract(pos1);
    }
}
