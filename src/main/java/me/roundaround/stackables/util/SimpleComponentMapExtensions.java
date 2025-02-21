package me.roundaround.stackables.util;

import net.minecraft.component.DataComponentType;

public interface SimpleComponentMapExtensions {
  default <T> void stackables$set(DataComponentType<? extends T> type, T value) {
  }
}
