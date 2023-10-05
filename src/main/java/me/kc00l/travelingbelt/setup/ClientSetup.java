package me.kc00l.travelingbelt.setup;

import me.kc00l.travelingbelt.TravelingBelt;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = TravelingBelt.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //MinecraftForge.EVENT_BUS.addListener(KeyBindings::onClientInput);

        //MinecraftForge.EVENT_BUS.register(EventKeyInput.class);

        // GUI
        event.enqueueWork(() -> {
            // Attach our container to the screen
            MenuScreens.register(Registration.ModificationAltar_Container.get(), ModificationAltarGUI::new);
        });
    }
}
