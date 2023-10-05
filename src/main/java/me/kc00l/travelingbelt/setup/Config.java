package me.kc00l.travelingbelt.setup;

import me.kc00l.travelingbelt.TravelingBelt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Config {
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

    public static final String CATEGORY_GENERAL = "general";
    //public static final String CATEGORY_OTHER = "other";
    //public static final String SUBCATEGORY_NAME = "NAME";
    //public static ForgeConfigSpec.IntValue VALUE_NAME;
    //public static ForgeConfigSpec.IntValue OTHER_VALUE_NAME;

    // add config items here

    public static void register() {
        //registerServerConfigs();
        registerCommonConfigs();
        //registerClientConfigs();
    }

    private static void registerServerConfigs() {
        //GeneratorConfig.registerServerConfig(SERVER_BUILDER);
        //PowergenConfig.registerServerConfig(SERVER_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
    }

    private static void registerCommonConfigs() {
        COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        generalConfig();
        COMMON_BUILDER.pop();

        //COMMON_BUILDER.comment("Other settings").push(CATEGORY_OTHER);
        //otherConfig();
        //COMMON_BUILDER.pop();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }

    private static void registerClientConfigs() {
        //PowergenConfig.registerClientConfig(CLIENT_BUILDER);
        //ManaConfig.registerClientConfig(CLIENT_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }

    private static void generalConfig() {
        //A_CONFIG_INT_PARAM = COMMON_BUILDER.comment("int param config comment").defineInRange("aRange", 32, 1, 64);
    }

    //private static void otherConfig() {}

    private static void travelingBeltConfig() {}

    private static void clientConfig() {}

    private static void serverConfig() {}
}
