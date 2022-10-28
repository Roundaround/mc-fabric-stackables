package me.roundaround.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PowderSnowBucketItem;
import net.minecraft.util.Hand;

@Mixin(PowderSnowBucketItem.class)
public abstract class PowderSnowBucketItemMixin {
  @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V"))
  private void altSetStackInHand(PlayerEntity player, Hand hand, ItemStack stack) {
    if (player.getStackInHand(hand).isEmpty()) {
      player.setStackInHand(hand, stack);
    }

    if (!player.getInventory().insertStack(stack)) {
      player.dropItem(stack, false);
    }
  }
}
