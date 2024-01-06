package me.roundaround.stackables;

import me.roundaround.roundalib.config.option.BooleanConfigOption;
import me.roundaround.roundalib.config.option.IntConfigOption;
import me.roundaround.stackables.compat.StackablesTags;
import me.roundaround.stackables.config.StackablesConfig;
import me.roundaround.stackables.mixin.ItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public final class StackablesMod implements ModInitializer {
  public static final String MOD_ID = "stackables";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
  public static final StackablesConfig CONFIG = new StackablesConfig();

  private static final HashMap<Identifier, Integer> VANILLA_MAX_COUNTS = new HashMap<>();
  private static final ArrayList<Callback> CALLBACKS = new ArrayList<>();

  @Override
  public void onInitialize() {
    CONFIG.init();

    syncTagMaxStackSizeWithConfig(CONFIG.POTIONS, CONFIG.POTION_COUNT, StackablesTags.POTIONS);
    syncTagMaxStackSizeWithConfig(CONFIG.SPLASH_POTIONS,
        CONFIG.SPLASH_POTION_COUNT,
        StackablesTags.SPLASH_POTIONS);
    syncTagMaxStackSizeWithConfig(CONFIG.SOUPS, CONFIG.SOUP_COUNT, StackablesTags.SOUPS);
    syncTagMaxStackSizeWithConfig(CONFIG.ENCHANTED_BOOKS,
        CONFIG.ENCHANTED_BOOK_COUNT,
        StackablesTags.ENCHANTED_BOOKS);
    syncTagMaxStackSizeWithConfig(CONFIG.BOATS, CONFIG.BOAT_COUNT, StackablesTags.BOATS);
    syncTagMaxStackSizeWithConfig(CONFIG.MINECARTS,
        CONFIG.MINECART_COUNT,
        StackablesTags.MINECARTS);
    syncTagMaxStackSizeWithConfig(CONFIG.BUCKETS, CONFIG.BUCKET_COUNT, StackablesTags.BUCKETS);
    syncTagMaxStackSizeWithConfig(CONFIG.BEDS, CONFIG.BED_COUNT, StackablesTags.BEDS);
    syncTagMaxStackSizeWithConfig(CONFIG.DISCS, CONFIG.DISC_COUNT, StackablesTags.MUSIC_DISCS);
    syncTagMaxStackSizeWithConfig(CONFIG.INSTRUMENTS,
        CONFIG.INSTRUMENT_COUNT,
        StackablesTags.INSTRUMENTS);
    syncTagMaxStackSizeWithConfig(CONFIG.PATTERNS,
        CONFIG.PATTERN_COUNT,
        StackablesTags.BANNER_PATTERNS);
    syncTagMaxStackSizeWithConfig(CONFIG.HORSE_EQUIPMENTS,
        CONFIG.HORSE_EQUIPMENT_COUNT,
        StackablesTags.HORSE_EQUIPMENT);
    syncTagMaxStackSizeWithConfig(CONFIG.CAKES, CONFIG.CAKE_COUNT, StackablesTags.CAKES);
    syncTagMaxStackSizeWithConfig(CONFIG.TOTEMS, CONFIG.TOTEM_COUNT, StackablesTags.TOTEMS);
    syncTagMaxStackSizeWithConfig(CONFIG.DECORATED_POTS,
        CONFIG.DECORATED_POT_COUNT,
        StackablesTags.DECORATED_POTS);

    syncTagMaxStackSizeWithConfig(CONFIG.EMPTY_BUCKET_COUNT, StackablesTags.EMPTY_BUCKETS);
    syncTagMaxStackSizeWithConfig(CONFIG.ARMOR_STAND_COUNT, StackablesTags.ARMOR_STANDS);
    syncTagMaxStackSizeWithConfig(CONFIG.BANNER_COUNT, StackablesTags.BANNERS);
    syncTagMaxStackSizeWithConfig(CONFIG.SIGN_COUNT, StackablesTags.SIGNS);
    syncTagMaxStackSizeWithConfig(CONFIG.THROWABLE_COUNT, StackablesTags.THROWABLES);
    syncTagMaxStackSizeWithConfig(CONFIG.ENDER_PEARL_COUNT, StackablesTags.ENDER_PEARLS);
    syncTagMaxStackSizeWithConfig(CONFIG.DRINKABLE_COUNT, StackablesTags.DRINKABLES);
    syncTagMaxStackSizeWithConfig(CONFIG.SIGNED_BOOK_COUNT, StackablesTags.SIGNED_BOOKS);

    CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
      CALLBACKS.forEach(Callback::execute);
    });

    CONFIG.MOD_ENABLED.subscribeToValueChanges(null, (prev, curr) -> {
      CALLBACKS.forEach(Callback::execute);
    });
  }

  private void syncTagMaxStackSizeWithConfig(
      BooleanConfigOption toggleOption, IntConfigOption countOption, TagKey<Item> tag) {
    Callback callback = () -> setMaxCountForTag(tag, toggleOption, countOption);
    toggleOption.subscribeToValueChanges(null, (prev, curr) -> callback.execute());
    countOption.subscribeToValueChanges(null, (prev, curr) -> callback.execute());
    CALLBACKS.add(callback);
  }

  private void syncTagMaxStackSizeWithConfig(IntConfigOption countOption, TagKey<Item> tag) {
    Callback callback = () -> setMaxCountForTag(tag, countOption);
    countOption.subscribeToValueChanges(null, (prev, curr) -> callback.execute());
    CALLBACKS.add(callback);
  }

  private void setMaxCountForTag(TagKey<Item> tag, int count) {
    Registries.ITEM.getOrCreateEntryList(tag).stream().forEach(entry -> {
      // Never set the max count to less than the vanilla max count.
      int vanillaMaxCount = getVanillaMaxCount(entry);
      ((ItemAccessor) entry.value()).setMaxCount(Math.max(count, vanillaMaxCount));
    });
  }

  private void setMaxCountForTag(
      TagKey<Item> tag, BooleanConfigOption toggleOption, IntConfigOption countOption) {
    int count =
        CONFIG.MOD_ENABLED.getValue() && toggleOption.getValue() ? countOption.getValue() : 1;
    setMaxCountForTag(tag, count);
  }

  private void setMaxCountForTag(TagKey<Item> tag, IntConfigOption countOption) {
    int count = CONFIG.MOD_ENABLED.getValue() ? countOption.getValue() : 1;
    setMaxCountForTag(tag, count);
  }

  private int getVanillaMaxCount(RegistryEntry<Item> entry) {
    Optional<RegistryKey<Item>> key = entry.getKey();
    if (key.isEmpty()) {
      return -1;
    }
    Identifier id = key.get().getValue();
    if (!VANILLA_MAX_COUNTS.containsKey(id)) {
      VANILLA_MAX_COUNTS.put(id, entry.value().getMaxCount());
    }
    return VANILLA_MAX_COUNTS.get(id);
  }

  @FunctionalInterface
  interface Callback {
    void execute();
  }
}
