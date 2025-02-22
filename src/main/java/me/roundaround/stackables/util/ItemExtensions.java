package me.roundaround.stackables.util;

public interface ItemExtensions {
  default void stackables$setMaxCount(int maxCount) {
  }

  default void stackables$resetMaxCount() {
  }

  default boolean stackables$isMaxCountDirty() {
    return false;
  }
}
