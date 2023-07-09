package lemon_juice.ntgbb.creativetab;

import lemon_juice.ntgbb.NetheriteTemplateGoByeBye;
import lemon_juice.ntgbb.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NetheriteTemplateGoByeBye.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NTGBB_TAB = CREATIVE_MODE_TABS.register("ntgbb", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ntgbb"))
            .icon(() -> new ItemStack(Blocks.SMITHING_TABLE))
            .build());

    public static void registerTabs(BuildCreativeModeTabContentsEvent event){
        if(event.getTab() == NTGBB_TAB.get()){
            event.accept(ModBlocks.LEGACY_SMITHING_TABLE.get());
        }
    }

    /******************************** Registry ********************************/
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
