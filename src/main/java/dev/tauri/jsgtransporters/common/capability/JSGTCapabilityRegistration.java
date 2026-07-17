package dev.tauri.jsgtransporters.common.capability;

import dev.tauri.jsg.core.JSGCore;
import dev.tauri.jsgtransporters.JSGTransporters;
import dev.tauri.jsgtransporters.common.blockentity.rings.RingsAbstractBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

/**
 * Central capability registration replacing the 1.20.1 getCapability overrides
 * (mirrors the main mod's JSGCapabilityRegistration).
 */
@EventBusSubscriber(modid = JSGTransporters.MOD_ID)
public class JSGTCapabilityRegistration {
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        for (var holder : JSGTransporters.REGISTRY_HELPER.be().getEntries()) {
            BlockEntityType<?> type = holder.get();

            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, type, (be, side) ->
                    be instanceof RingsAbstractBE rings ? rings.getEnergyStorage() : null);

            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, type, (be, side) ->
                    be instanceof RingsAbstractBE rings ? rings.getItemHandler() : null);

            // CC:Tweaked peripherals (no-op when CC is absent)
            JSGCore.ccWrapper.registerPeripheralBE(event, type);
        }
    }
}
