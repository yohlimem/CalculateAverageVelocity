package net.fabricmc.av;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.av.features.FindPlayerVelocity;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvaregeVelocityMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("av");

	@Override
	public void onInitialize() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(client == null) return;
			PlayerEntity player = client.player;
			if(player == null) return;
			FindPlayerVelocity.onPlayerMove(player);
			AvaregeVelocityMod.LOGGER.info(String.valueOf(FindPlayerVelocity.findPlayerAvaregeVelocity(5)));
//			if(FindPlayerVelocity.playerMoving(player)){
//				LOGGER.info(FindPlayerVelocity.findPlayerAvaregeVelocity(5).toString());
//			}
		});
	}
}
