package me.roundaround.stackables.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
  @Redirect(
      method = "onTakeOutput", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V",
      ordinal = 0
  )
  )
  private void altSetStackFirstSlot(Inventory inventory, int slot, ItemStack stack) {
    decrementOrRemove(inventory, slot, stack);
  }

  @Redirect(
      method = "onTakeOutput", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V",
      ordinal = 3
  )
  )
  private void altSetStackSecondSlot(Inventory inventory, int slot, ItemStack stack) {
    decrementOrRemove(inventory, slot, stack);
  }

  @ModifyExpressionValue(
      method = "updateResult", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/inventory/Inventory;getStack(I)Lnet/minecraft/item/ItemStack;",
      ordinal = 0
  )
  )
  private static ItemStack tweakFirstInputStack(ItemStack stack) {
    // If either input is an enchanted book, then we can only use one at a time. We do a little
    // swaperoo to make the updateResult method think there's only one book in the first slot.
    if (stack.getCount() > 1 && stack.isOf(Items.ENCHANTED_BOOK)) {
      ItemStack copy = stack.copy();
      copy.setCount(1);
      return copy;
    }

    return stack;
  }

  @ModifyExpressionValue(
      method = "updateResult", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/inventory/Inventory;getStack(I)Lnet/minecraft/item/ItemStack;",
      ordinal = 1
  )
  )
  private static ItemStack tweakSecondInputStack(ItemStack stack) {
    // Same as above, but for the second slot.
    if (stack.getCount() > 1 && stack.isOf(Items.ENCHANTED_BOOK)) {
      ItemStack copy = stack.copy();
      copy.setCount(1);
      return copy;
    }

    return stack;
  }

  @Unique
  private static void decrementOrRemove(Inventory inventory, int slot, ItemStack newStack) {
    ItemStack currentStack = inventory.getStack(slot);

    if (currentStack.getCount() > 1) {
      ItemStack copy = currentStack.copy();
      copy.decrement(1);
      inventory.setStack(slot, copy);
      return;
    }

    inventory.setStack(slot, newStack);
  }
}
