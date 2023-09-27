package me.roundaround.stackables.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SuspiciousStewItem.class)
public abstract class SuspiciousStewItemMixin {
  @Inject(method = "finishUsing", at = @At(value = "RETURN"), cancellable = true)
  private void finishUsing(
      ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> info) {
    if (!(user instanceof PlayerEntity playerEntity) ||
        ((PlayerEntity) user).getAbilities().creativeMode) {
      return;
    }

    ItemStack output = info.getReturnValue();
    if (!output.getItem().equals(Items.BOWL) || stack.isEmpty()) {
      return;
    }

    if (!playerEntity.getInventory().insertStack(output)) {
      playerEntity.dropItem(output, false);
    }

    info.setReturnValue(stack);
  }
}
