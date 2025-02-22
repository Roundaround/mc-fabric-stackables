package me.roundaround.stackables.util;

import net.minecraft.component.ComponentType;

public interface SimpleComponentMapExtensions {
  default <T> void stackables$set(ComponentType<? extends T> type, T value) {
  }

  default boolean stackables$isDirty() {
    return false;
  }
}
