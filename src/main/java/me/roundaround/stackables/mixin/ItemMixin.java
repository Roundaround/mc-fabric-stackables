package me.roundaround.stackables.mixin;

import me.roundaround.stackables.util.ItemExtensions;
import me.roundaround.stackables.util.SimpleComponentMapExtensions;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemExtensions {
  @Shadow
  public abstract ComponentMap getComponents();

  @Override
  public void stackables$setMaxCount(int maxCount) {
    ComponentMap baseMap = this.getComponents();
    if (!(baseMap instanceof SimpleComponentMapExtensions map)) {
      return;
    }
    map.stackables$set(DataComponentTypes.MAX_STACK_SIZE, maxCount);
  }
}
