package net.fabricmc.av;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.av.features.FindPlayerVelocity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvaregeVelocityMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final class TimeScale{
		public static final int TICKS = 1;
		public static final int SECONDS = 20;
		public static final int MINUTES = 1_200;
		public static final int HOURS = 72_000;
	}
	public static final Logger LOGGER = LoggerFactory.getLogger("av");

	@Override
	public void onInitialize() {
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if(client == null) return;
			PlayerEntity player = client.player;
			if(player == null) return;
			FindPlayerVelocity.onPlayerMove(player);
			Vec3d vel = FindPlayerVelocity.findPlayerAvaregeVelocity(20, TimeScale.SECONDS);
			AvaregeVelocityMod.LOGGER.info(String.valueOf(vel.toString()));
		});
	}
}
