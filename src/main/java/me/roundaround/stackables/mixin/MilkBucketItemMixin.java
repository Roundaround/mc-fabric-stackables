package me.roundaround.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin {
  @Inject(method = "finishUsing", at = @At(value = "RETURN"), cancellable = true)
  private void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> info) {
    if (!(user instanceof PlayerEntity) || ((PlayerEntity)user).getAbilities().creativeMode) {
      return;
    }

    if (!info.getReturnValue().getItem().equals(Items.MILK_BUCKET)) {
      return;
    }

    PlayerEntity playerEntity = (PlayerEntity) user;
    ItemStack output = new ItemStack(Items.BUCKET);
    if (!playerEntity.getInventory().insertStack(output)) {
      playerEntity.dropItem(output, false);
    }

    info.setReturnValue(stack);
  }
}
