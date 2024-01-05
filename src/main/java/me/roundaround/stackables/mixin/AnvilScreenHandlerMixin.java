package me.roundaround.stackables.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
  @Unique
  private final AtomicInteger resultCount = new AtomicInteger();

  @Inject(method = "updateResult", at = @At("RETURN"))
  private void afterUpdateResult(CallbackInfo info) {
    // Make a note of how many items are in the resulting output stack. We'll use this later to
    // determine how many items to remove from the inputs.
    resultCount.set(((AnvilScreenHandler) (Object) this).getSlot(2).getStack().getCount());
  }

  @Redirect(
      method = "onTakeOutput", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V",
      ordinal = 0
  )
  )
  private void removeItemsFromFirstInputSlot(
      Inventory inventory,
      int slot,
      ItemStack stack,
      @Share("resultCountSnapshot") LocalIntRef resultCountSnapshot) {
    // Because of the multi-threaded nature of inventory updates, "updateResult" for the next quick
    // move call can potentially be called BETWEEN modifying the first input here and modifying the
    // second input below. In order to guarantee both stacks are decremented by the same amount,
    // store the result count in a local variable and use that to decrement both stacks.
    resultCountSnapshot.set(resultCount.get());
    decrementOrRemove(inventory, slot, resultCountSnapshot.get());
  }

  @Redirect(
      method = "onTakeOutput", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V",
      ordinal = 3
  )
  )
  private void removeItemsFromSecondInputSlot(
      Inventory inventory,
      int slot,
      ItemStack stack,
      @Share("resultCountSnapshot") LocalIntRef resultCountSnapshot) {
    decrementOrRemove(inventory, slot, resultCountSnapshot.get());
  }

  @Unique
  private void decrementOrRemove(
      Inventory inventory, int slot, int amount) {
    ItemStack currentStack = inventory.getStack(slot);

    if (currentStack.getCount() > amount) {
      ItemStack copy = currentStack.copy();
      copy.decrement(amount);
      inventory.setStack(slot, copy);
      return;
    }

    inventory.setStack(slot, ItemStack.EMPTY);
  }

  @ModifyExpressionValue(
      method = "updateResult", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/inventory/Inventory;getStack(I)Lnet/minecraft/item/ItemStack;",
      ordinal = 0
  )
  )
  private ItemStack tweakFirstInputStack(ItemStack stack) {
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
  private ItemStack tweakSecondInputStack(ItemStack stack) {
    // If either input is an enchanted book, then we can only use one at a time. We do a little
    // swaperoo to make the updateResult method think there's only one book in the second slot.
    if (stack.getCount() > 1 && stack.isOf(Items.ENCHANTED_BOOK)) {
      ItemStack copy = stack.copy();
      copy.setCount(1);
      return copy;
    }

    return stack;
  }
}
