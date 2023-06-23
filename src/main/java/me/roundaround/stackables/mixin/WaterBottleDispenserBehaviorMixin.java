package me.roundaround.stackables.mixin;

import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPointer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$20")
public class WaterBottleDispenserBehaviorMixin {
  @Inject(method = "dispenseSilently", at = @At(value = "RETURN"), cancellable = true)
  private void onDispenseSilently(
      BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> info) {
    if (!info.getReturnValue().isOf(Items.GLASS_BOTTLE)) {
      return;
    }

    stack.decrement(1);

    if (stack.isEmpty()) {
      info.setReturnValue(new ItemStack(Items.GLASS_BOTTLE));
      return;
    }

    DispenserBlockEntity dispenser = pointer.getBlockEntity();

    if (dispenser.addToFirstFreeSlot(new ItemStack(Items.GLASS_BOTTLE)) >= 0) {
      info.setReturnValue(stack);
    }

    for (int i = 0; i < dispenser.size(); i++) {
      ItemStack slotStack = dispenser.getStack(i).copy();
      if (ItemStack.canCombine(slotStack, new ItemStack(Items.GLASS_BOTTLE))) {
        slotStack.increment(1);
        dispenser.setStack(i, slotStack);
        info.setReturnValue(stack);
        return;
      }
    }

    info.setReturnValue(stack);
  }
}
