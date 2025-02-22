package me.roundaround.stackables.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
  @Final
  @Shadow
  private Item item;

  @Shadow
  public abstract boolean isEmpty();

  @Shadow
  public abstract ComponentMap getComponents();

  @Shadow
  public abstract ComponentMap getDefaultComponents();

  @ModifyReturnValue(method = "getMaxCount", at = @At("RETURN"))
  private int adjustMaxCount(int original) {
    if (this.isEmpty() || this.item == null) {
      return original;
    }

    if (this.item.stackables$isMaxCountDirty() && !this.hasDefaultMaxCount()) {
      return this.item.getMaxCount();
    }
    return original;
  }

  @Unique
  private boolean hasDefaultMaxCount() {
    // Pull from component map directly to avoid a stack overflow with the getMaxCount mixin
    return Objects.equals(
        this.getComponents().getOrDefault(DataComponentTypes.MAX_STACK_SIZE, 1),
        this.getDefaultComponents().getOrDefault(DataComponentTypes.MAX_STACK_SIZE, 1)
    );
  }
}
