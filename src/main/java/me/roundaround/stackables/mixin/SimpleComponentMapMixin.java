package me.roundaround.stackables.mixin;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import me.roundaround.stackables.util.SimpleComponentMapExtensions;
import net.minecraft.component.ComponentType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(targets = "net.minecraft.component.ComponentMap$Builder$SimpleComponentMap")
public abstract class SimpleComponentMapMixin implements SimpleComponentMapExtensions {
  @Unique
  private boolean stackablesDirty = false;

  @Final
  @Shadow
  private Reference2ObjectMap<ComponentType<?>, Object> map;

  @Override
  public <T> void stackables$set(ComponentType<? extends T> type, T value) {
    this.stackablesDirty = true;
    this.map.put(type, value);
  }

  @Override
  public boolean stackables$isDirty() {
    return this.stackablesDirty;
  }
}
