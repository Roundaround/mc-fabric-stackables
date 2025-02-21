package me.roundaround.stackables.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$16")
public abstract class EmptyBucketDispenserBehaviorMixin {
  @Inject(
      method = "dispenseSilently", at = @At(
      value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V", shift = At.Shift.AFTER
  ), cancellable = true
  )
  private void onDispenseSilently(
      BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> info, @Local Item item
  ) {
    if (stack.isEmpty()) {
      // Fall back to vanilla behavior
      return;
    }

    DispenserBlockEntity dispenser = pointer.blockEntity();

    if (dispenser.addToFirstFreeSlot(new ItemStack(item)) >= 0) {
      info.setReturnValue(stack);
      return;
    }

    for (int i = 0; i < dispenser.size(); i++) {
      ItemStack slotStack = dispenser.getStack(i).copy();
      if (ItemStack.areItemsAndComponentsEqual(slotStack, new ItemStack(item))) {
        slotStack.increment(1);
        dispenser.setStack(i, slotStack);
        info.setReturnValue(stack);
        return;
      }
    }

    info.setReturnValue(stack);
  }
}
