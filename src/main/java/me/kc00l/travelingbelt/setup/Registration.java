package me.kc00l.travelingbelt.setup;

import me.kc00l.travelingbelt.common.blockentities.ModificationAltarBE;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.kc00l.travelingbelt.TravelingBelt.MODID;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        CONTAINERS.register(bus);
    }

    //Blocks
    public static final RegistryObject<Block> ModificationAltar = BLOCKS.register("modification_altar",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Item> ModificationAltar_ITEM = ITEMS.register("modification_altar",
            () -> new BlockItem(ModificationAltar.get(), new Item.Properties()));

    //BlockEntities
    public static final RegistryObject<BlockEntityType<ModificationAltarBE>> ModificationAltar_BE = BLOCK_ENTITIES.register("modification_altar",
            () -> BlockEntityType.Builder.of(ModificationAltarBE::new, ModificationAltar.get()).build(null));

    //Items
    public static final RegistryObject<Item> Traveling_Belt = ITEMS.register("traveling_belt", TravelingBelt::new);

    //Containers
    public static final RegistryObject<MenuType<ModificationAltarContainer>> ModificationAltar_Container = CONTAINERS.register('modification_altar',
            () -> IForgeMenuType.create((windowId, inv, data) -> new ModificationAltarContainer(windowId, inv, data)));
}
