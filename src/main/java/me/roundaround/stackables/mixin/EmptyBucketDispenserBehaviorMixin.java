package me.roundaround.stackables.mixin;

import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$9")
public abstract class EmptyBucketDispenserBehaviorMixin {
  private Item filledBucketItem = null;

  @ModifyVariable(method = "dispenseSilently", at = @At("STORE"), ordinal = 0)
  private Item checkItem(Item item) {
    this.filledBucketItem = item;
    return item;
  }

  @Inject(
      method = "dispenseSilently", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/item/ItemStack;decrement(I)V",
      shift = At.Shift.AFTER
  ), cancellable = true
  )
  private void onDispenseSilently(
      BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> info) {
    Item filledItem = this.filledBucketItem;
    this.filledBucketItem = null;

    if (stack.isEmpty()) {
      // Fall back to vanilla behavior
      return;
    }

    if (filledItem == null) {
      // Something went wrong, fall back to vanilla behavior
      return;
    }

    DispenserBlockEntity dispenser = pointer.getBlockEntity();

    if (dispenser.addToFirstFreeSlot(new ItemStack(filledItem)) >= 0) {
      info.setReturnValue(stack);
      return;
    }

    for (int i = 0; i < dispenser.size(); i++) {
      ItemStack slotStack = dispenser.getStack(i).copy();
      if (ItemStack.canCombine(slotStack, new ItemStack(filledItem))) {
        slotStack.increment(1);
        dispenser.setStack(i, slotStack);
        info.setReturnValue(stack);
        return;
      }
    }

    info.setReturnValue(stack);
  }
}
