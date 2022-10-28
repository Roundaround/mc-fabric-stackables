package me.roundaround.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.block.JukeboxBlock;
import net.minecraft.item.ItemStack;

@Mixin(JukeboxBlock.class)
public abstract class JukeboxBlockMixin {
  @ModifyArg(method = "setRecord", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/JukeboxBlockEntity;setRecord(Lnet/minecraft/item/ItemStack;)V", ordinal = 0))
  private ItemStack adjustRecordStack(ItemStack old) {
    old.setCount(1);
    return old;
  }
}
