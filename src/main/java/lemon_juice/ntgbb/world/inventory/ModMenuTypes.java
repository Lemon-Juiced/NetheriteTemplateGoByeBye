package lemon_juice.ntgbb.world.inventory;

import lemon_juice.ntgbb.NetheriteTemplateGoByeBye;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, NetheriteTemplateGoByeBye.MOD_ID);

    public static final RegistryObject<MenuType<LegacySmithingMenu>> LEGACY_SMITHING = register("legacy_smithing", () -> IForgeMenuType.create(LegacySmithingMenu::new));

    private static <T extends AbstractContainerMenu> MenuType<T> register(String s, MenuType.MenuSupplier<T> supplier) {
        return Registry.register(BuiltInRegistries.MENU, s, new MenuType<>(supplier, FeatureFlags.VANILLA_SET));
    }

    public static void register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }

}
