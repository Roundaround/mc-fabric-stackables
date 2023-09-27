package me.roundaround.stackables.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
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
