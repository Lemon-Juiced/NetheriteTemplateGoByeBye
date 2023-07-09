package lemon_juice.ntgbb.world.item.crafting;

import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializer {
    static RecipeSerializer<LegacyUpgradeRecipe> SMITHING = RecipeSerializer.register("smithing", new LegacyUpgradeRecipe.Serializer());
}
