package me.roundaround.stackables.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBucketItem.class)
public abstract class PowderSnowBucketItemMixin {
  @Inject(method = "useOnBlock", at = @At(value = "HEAD"))
  private void beforeUseOnBlock(
      ItemUsageContext context,
      CallbackInfoReturnable<ActionResult> info,
      @Share("originalStack") LocalRef<ItemStack> originalStackRef) {
    originalStackRef.set(context.getStack().copy());
  }

  @Redirect(
      method = "useOnBlock", at = @At(
      value = "INVOKE",
      target = "Lnet/minecraft/entity/player/PlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"
  )
  )
  private void altSetStackInHand(
      PlayerEntity player,
      Hand hand,
      ItemStack stack,
      @Share("originalStack") LocalRef<ItemStack> originalStackRef) {
    ItemStack originalStack = originalStackRef.get();
    if (originalStack == null || originalStack.getCount() == 1) {
      player.setStackInHand(hand, stack);
      return;
    }

    if (!player.getInventory().insertStack(stack)) {
      player.dropItem(stack, false);
    }
  }
}
