package dev.tauri.jsgtransporters.common.helpers;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 1.20.1 ITeleporter replacement: builds the DimensionTransition used to move
 * an entity through the rings into another dimension.
 */
public record RingsTeleporter(Vector3d posFinal, float newYaw, @Nullable List<Entity> passengers) {

    public DimensionTransition createTransition(ServerLevel destWorld, Entity entity) {
        return new DimensionTransition(
                destWorld,
                new Vec3(posFinal.x(), posFinal.y(), posFinal.z()),
                Vec3.ZERO,
                newYaw,
                entity.getXRot(),
                placed -> {
                    TeleportHelper.setRotationAndPosition(placed, newYaw, posFinal);
                    if (passengers != null) {
                        for (Entity passenger : passengers) {
                            passenger.startRiding(placed);
                        }
                    }
                });
    }
}
