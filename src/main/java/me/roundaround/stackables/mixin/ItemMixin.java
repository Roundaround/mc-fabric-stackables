package me.roundaround.stackables.mixin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.roundaround.stackables.StackablesMod;
import me.roundaround.stackables.util.ItemExtensions;
import me.roundaround.stackables.util.SimpleComponentMapExtensions;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.IdentityHashMap;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemExtensions {
  @Unique
  private static final IdentityHashMap<ComponentMap, Item> ALL_TOUCHED_MAPS = new IdentityHashMap<>();
  @Unique
  private static final IdentityHashMap<Item, ComponentMap> ALL_TOUCHED_ITEMS = new IdentityHashMap<>();

  @Unique
  private int vanillaMaxCount = -1;

  @Shadow
  public abstract ComponentMap getComponents();

  @Shadow
  public abstract String toString();

  @Shadow
  public abstract int getMaxCount();

  @Override
  public void stackables$setMaxCount(int maxCount) {
    ComponentMap baseMap = this.getComponents();
    if (!(baseMap instanceof SimpleComponentMapExtensions map)) {
      StackablesMod.LOGGER.warn("Item discovered not using SimpleComponentMap: {}", this);
      return;
    }

    Item self = (Item) (Object) this;
    ALL_TOUCHED_MAPS.put(baseMap, self);
    ALL_TOUCHED_ITEMS.put(self, baseMap);

    if (ALL_TOUCHED_MAPS.size() != ALL_TOUCHED_ITEMS.size()) {
      StackablesMod.LOGGER.warn("Shared component map or item reference detected: {}", this);

      if (self == Items.TOTEM_OF_UNDYING) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject root = new JsonObject();

        JsonArray mapToItem = new JsonArray();
        ALL_TOUCHED_MAPS.forEach((touchedMap, touchedItem) -> mapToItem.add(touchedItem.toString()));
        root.add("mapToItem", mapToItem);

        JsonArray itemToMap = new JsonArray();
        ALL_TOUCHED_ITEMS.forEach((touchedItem, touchedMap) -> itemToMap.add(touchedItem.toString()));
        root.add("itemToMap", itemToMap);

        try {
          Path path = FabricLoader.getInstance().getGameDir().resolve("stackables_maps_dump.json");
          Files.write(path, gson.toJson(root).getBytes());
        } catch (IOException ignored) {
          // Swallow
        }
      }
    }

    if (this.vanillaMaxCount == -1) {
      this.vanillaMaxCount = this.getMaxCount();
    }

    // Never lower maxCount below vanilla
    map.stackables$set(DataComponentTypes.MAX_STACK_SIZE, Math.max(this.vanillaMaxCount, maxCount));
  }

  @Override
  public void stackables$resetMaxCount() {
    ComponentMap baseMap = this.getComponents();
    if (!(baseMap instanceof SimpleComponentMapExtensions map) || this.vanillaMaxCount == -1) {
      return;
    }
    map.stackables$set(DataComponentTypes.MAX_STACK_SIZE, this.vanillaMaxCount);
  }

  @Override
  public boolean stackables$isMaxCountDirty() {
    return this.vanillaMaxCount != -1 && this.vanillaMaxCount != this.getMaxCount();
  }
}
