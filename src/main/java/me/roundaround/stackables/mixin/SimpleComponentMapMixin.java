package me.roundaround.stackables.mixin;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import me.roundaround.stackables.util.SimpleComponentMapExtensions;
import net.minecraft.component.DataComponentType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net.minecraft.component.ComponentMap$Builder$SimpleComponentMap")
public abstract class SimpleComponentMapMixin implements SimpleComponentMapExtensions {
  @Final
  @Shadow
  private Reference2ObjectMap<DataComponentType<?>, Object> map;

  @Override
  public <T> void stackables$set(DataComponentType<? extends T> type, T value) {
    this.map.put(type, value);
  }
}
