package me.kc00l.travelingbelt;

import com.mojang.logging.LogUtils;
import me.kc00l.travelingbelt.setup.Config;
import me.kc00l.travelingbelt.setup.ModSetup;
import me.kc00l.travelingbelt.setup.Registration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TravelingBelt.MODID)
public class TravelingBelt {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "travelingbelt";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public TravelingBelt() {
        // Register deferred registry
        Registration.init();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(ModSetup::init);
        ModSetup.TABS.register(modEventBus);
    }
}
