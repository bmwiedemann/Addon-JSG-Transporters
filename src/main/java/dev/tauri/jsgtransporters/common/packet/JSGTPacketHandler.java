package dev.tauri.jsgtransporters.common.packet;

import dev.tauri.jsg.core.common.packet.SimplePacketHandler;
import dev.tauri.jsgtransporters.JSGTransporters;
import dev.tauri.jsgtransporters.common.packet.packets.CPButtonClickedToServer;
import dev.tauri.jsgtransporters.common.packet.packets.SaveRingsSettingsToServer;
import dev.tauri.jsg.core.common.packet.TargetPoint;
import dev.tauri.jsg.core.mapping.JSGMapping;
import net.minecraft.server.level.ServerPlayer;

@SuppressWarnings("unused")
public class JSGTPacketHandler {

    private static final SimplePacketHandler HANDLER = new SimplePacketHandler(JSGMapping.rl(JSGTransporters.MOD_ID, "main"), "1.0");

    public static void sendToServer(Object packet) {
        HANDLER.sendToServer(packet);
    }

    public static void sendToClient(Object packet, TargetPoint point) {
        HANDLER.sendToClient(packet, point);
    }

    public static void sendTo(Object packet, ServerPlayer player) {
        HANDLER.sendTo(packet, player);
    }

    public static void init() {
        // to server
        HANDLER.registerPacketToServer(CPButtonClickedToServer.class);
        HANDLER.registerPacketToServer(SaveRingsSettingsToServer.class);

        // to client
    }
}
