package me.roundaround.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.item.Item;

@Mixin(Item.class)
public interface ItemAccessor {
  @Accessor("maxCount")
  void setMaxCount(int newMaxCount);
}
