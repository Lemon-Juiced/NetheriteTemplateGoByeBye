package lemon_juice.ntgbb.world.inventory;

import lemon_juice.ntgbb.block.ModBlocks;
import lemon_juice.ntgbb.world.item.crafting.LegacyUpgradeRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LegacySmithingMenu extends ItemCombinerMenu {
    private final Level level;
    public static final int INPUT_SLOT = 0;
    public static final int ADDITIONAL_SLOT = 1;
    public static final int RESULT_SLOT = 2;
    private static final int INPUT_SLOT_X_PLACEMENT = 27;
    private static final int ADDITIONAL_SLOT_X_PLACEMENT = 76;
    private static final int RESULT_SLOT_X_PLACEMENT = 134;
    private static final int SLOT_Y_PLACEMENT = 47;
    @javax.annotation.Nullable
    private LegacyUpgradeRecipe selectedRecipe;
    private final List<LegacyUpgradeRecipe> recipes;

    public LegacySmithingMenu(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(ModMenuTypes.LEGACY_SMITHING.get(), i, inventory, containerLevelAccess);
        this.level = inventory.player.level();
        this.recipes = this.level.getRecipeManager().<Container, SmithingRecipe>getAllRecipesFor(RecipeType.SMITHING).stream().filter((p_266879_) -> {
            return p_266879_ instanceof LegacyUpgradeRecipe;
        }).map((p_266816_) -> {
            return (LegacyUpgradeRecipe)p_266816_;
        }).toList();
    }

    public LegacySmithingMenu(int i, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        this(i, inventory, ContainerLevelAccess.NULL);
    }

    protected boolean mayPickup(Player player, boolean b) {
        return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
    }

    protected void onTake(Player player, ItemStack itemStack) {
        itemStack.onCraftedBy(player.level(), player, itemStack.getCount());
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
        this.access.execute((p_267191_, p_267098_) -> {
            p_267191_.levelEvent(1044, p_267098_, 0);
        });
    }

    private void shrinkStackInSlot(int i) {
        ItemStack itemstack = this.inputSlots.getItem(i);
        itemstack.shrink(1);
        this.inputSlots.setItem(i, itemstack);
    }

    protected boolean isValidBlock(BlockState blockState) {
        return blockState.is(ModBlocks.LEGACY_SMITHING_TABLE.get());
    }

    public void createResult() {
        List<LegacyUpgradeRecipe> list = this.level.getRecipeManager().getRecipesFor(RecipeType.SMITHING, this.inputSlots, this.level).stream().filter((p_267116_) -> {
            return p_267116_ instanceof LegacyUpgradeRecipe;
        }).map((p_266971_) -> {
            return (LegacyUpgradeRecipe)p_266971_;
        }).toList();
        if (list.isEmpty()) {
            this.resultSlots.setItem(0, ItemStack.EMPTY);
        } else {
            LegacyUpgradeRecipe legacyupgraderecipe = list.get(0);
            ItemStack itemstack = legacyupgraderecipe.assemble(this.inputSlots, this.level.registryAccess());
            if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
                this.selectedRecipe = legacyupgraderecipe;
                this.resultSlots.setRecipeUsed(legacyupgraderecipe);
                this.resultSlots.setItem(0, itemstack);
            }
        }

    }

    protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create().withSlot(0, 27, 47, (p_266883_) -> {
            return true;
        }).withSlot(1, 76, 47, (p_267323_) -> {
            return true;
        }).withResultSlot(2, 134, 47).build();
    }

    public int getSlotToQuickMoveTo(ItemStack itemStack) {
        return this.shouldQuickMoveToAdditionalSlot(itemStack) ? 1 : 0;
    }

    protected boolean shouldQuickMoveToAdditionalSlot(ItemStack itemStack) {
        return this.recipes.stream().anyMatch((p_267065_) -> {
            return p_267065_.isAdditionIngredient(itemStack);
        });
    }

    public boolean canTakeItemForPickAll(ItemStack itemStack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(itemStack, slot);
    }
}
