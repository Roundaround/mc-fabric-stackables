package me.roundaround.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {
  @Inject(method = "getEmptiedStack", at = @At(value = "HEAD"), cancellable = true)
  private static void getEmptiedStack(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> info) {
    if (player.getAbilities().creativeMode) {
      return;
    }

    stack.decrement(1);

    ItemStack output = new ItemStack(Items.BUCKET);
    if (stack.isEmpty()) {
      info.setReturnValue(output);
      return;
    }

    if (!player.getInventory().insertStack(output)) {
      player.dropItem(output, false);
    }

    info.setReturnValue(stack);
  }
}
