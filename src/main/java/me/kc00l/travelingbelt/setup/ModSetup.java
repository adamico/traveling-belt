package me.kc00l.travelingbelt.setup;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static me.kc00l.travelingbelt.TravelingBelt.MODID;

public class ModSetup {
    public static void init(final FMLCommonSetupEvent event) {}

    public static final String TAB_NAME = "travelingbelt";
    public static DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static RegistryObject<CreativeModeTab> TAB_TRAVELINGBELT = TABS.register(TAB_NAME, () -> CreativeModeTab.builder()
            .title(Component.literal("Traveling Belt"))
            .icon(() -> new ItemStack(Registration.Traveling_Belt.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((featureFlags, output) -> {
                Registration.ITEMS.getEntries().forEach(e -> {
                    Item item = e.get();
                    output.accept(item);
                });
            })
            .build());
}
