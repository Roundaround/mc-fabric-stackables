package me.roundaround.stackables.mixin;

import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(JukeboxBlockEntity.class)
public class JukeboxBlockEntityMixin {
  @ModifyVariable(method = "setStack", at = @At("HEAD"), ordinal = 0, argsOnly = true)
  private ItemStack modifyStack(ItemStack stack) {
    ItemStack copy = stack.copy();
    copy.setCount(1);
    return copy;
  }

  @ModifyVariable(method = "setDisc", at = @At("HEAD"), ordinal = 0, argsOnly = true)
  private ItemStack modifyDisc(ItemStack stack) {
    ItemStack copy = stack.copy();
    copy.setCount(1);
    return copy;
  }
}
