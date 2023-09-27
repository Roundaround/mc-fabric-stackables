package me.roundaround.stackables.mixin;

import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$8")
public abstract class FilledBucketDispenserBehaviorMixin {
  @Inject(
      method = "dispenseSilently", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/item/FluidModificationItem;onEmptied(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/math/BlockPos;)V",
      shift = At.Shift.AFTER
  ), cancellable = true
  )
  private void onDispenseSilently(
      BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> info) {
    stack.decrement(1);

    if (stack.isEmpty()) {
      info.setReturnValue(new ItemStack(Items.BUCKET));
      return;
    }

    DispenserBlockEntity dispenser = pointer.getBlockEntity();

    if (dispenser.addToFirstFreeSlot(new ItemStack(Items.BUCKET)) >= 0) {
      info.setReturnValue(stack);
    }

    for (int i = 0; i < dispenser.size(); i++) {
      ItemStack slotStack = dispenser.getStack(i).copy();
      if (ItemStack.canCombine(slotStack, new ItemStack(Items.BUCKET))) {
        slotStack.increment(1);
        dispenser.setStack(i, slotStack);
        info.setReturnValue(stack);
        return;
      }
    }

    info.setReturnValue(stack);
  }
}
