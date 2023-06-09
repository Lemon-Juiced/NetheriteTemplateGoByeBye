package lemon_juice.ntgbb.item;

import lemon_juice.ntgbb.NetheriteTemplateGoByeBye;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetheriteTemplateGoByeBye.MOD_ID);

    public static final RegistryObject<Item> BETTER_TEMPLATE = ITEMS.register("better_template", () -> new ModTemplateItem(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
