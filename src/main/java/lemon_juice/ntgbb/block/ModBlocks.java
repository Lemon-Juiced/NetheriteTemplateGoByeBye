package lemon_juice.ntgbb.block;

import lemon_juice.ntgbb.NetheriteTemplateGoByeBye;
import lemon_juice.ntgbb.block.custom.LegacySmithingTableBlock;
import lemon_juice.ntgbb.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NetheriteTemplateGoByeBye.MOD_ID);

    public static final RegistryObject<Block> LEGACY_SMITHING_TABLE = registerBlock("legacy_smithing_table", () -> new LegacySmithingTableBlock(BlockBehaviour.Properties.copy(Blocks.SMITHING_TABLE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    /******************************** Registry ********************************/
    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
