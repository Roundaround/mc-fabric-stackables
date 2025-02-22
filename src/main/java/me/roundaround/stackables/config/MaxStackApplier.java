package me.roundaround.stackables.config;

import me.roundaround.roundalib.config.option.BooleanConfigOption;
import me.roundaround.roundalib.config.option.IntConfigOption;
import me.roundaround.roundalib.util.Observable;
import me.roundaround.stackables.compat.StackablesTags;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;

public final class MaxStackApplier {
  private final ArrayList<Callback> callbacks = new ArrayList<>();
  private final ArrayList<Observable.Subscription> subscriptions = new ArrayList<>();
  private final ArrayList<Item> dirtyItems = new ArrayList<>();

  private static MaxStackApplier instance;

  private MaxStackApplier() {
    CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> this.callbacks.forEach(Callback::execute));
  }

  public static MaxStackApplier getInstance() {
    if (instance == null) {
      instance = new MaxStackApplier();
    }
    return instance;
  }

  public void init() {
    if (!this.callbacks.isEmpty() || !this.subscriptions.isEmpty() || !this.dirtyItems.isEmpty()) {
      this.clear();
    }

    StackablesConfig config = StackablesConfig.getInstance();

    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.potions, config.potionCount, StackablesTags.POTIONS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled,
        config.splashPotions,
        config.splashPotionCount,
        StackablesTags.SPLASH_POTIONS
    );
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.soups, config.soupCount, StackablesTags.SOUPS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled,
        config.enchantedBooks,
        config.enchantedBookCount,
        StackablesTags.ENCHANTED_BOOKS
    );
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.boats, config.boatCount, StackablesTags.BOATS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled,
        config.minecarts,
        config.minecartCount,
        StackablesTags.MINECARTS
    );
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.buckets, config.bucketCount, StackablesTags.BUCKETS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.beds, config.bedCount, StackablesTags.BEDS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.discs, config.discCount, StackablesTags.MUSIC_DISCS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled,
        config.instruments,
        config.instrumentCount,
        StackablesTags.INSTRUMENTS
    );
    this.syncTagMaxStackSizeWithConfig(config.modEnabled,
        config.patterns,
        config.patternCount,
        StackablesTags.BANNER_PATTERNS
    );
    this.syncTagMaxStackSizeWithConfig(config.modEnabled,
        config.horseEquipments,
        config.horseEquipmentCount,
        StackablesTags.HORSE_EQUIPMENT
    );
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.cakes, config.cakeCount, StackablesTags.CAKES);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.totems, config.totemCount, StackablesTags.TOTEMS);

    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.emptyBucketCount, StackablesTags.EMPTY_BUCKETS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.armorStandCount, StackablesTags.ARMOR_STANDS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.bannerCount, StackablesTags.BANNERS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.signCount, StackablesTags.SIGNS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.throwableCount, StackablesTags.THROWABLES);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.enderPearlCount, StackablesTags.ENDER_PEARLS);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.drinkableCount, StackablesTags.DRINKABLES);
    this.syncTagMaxStackSizeWithConfig(config.modEnabled, config.signedBookCount, StackablesTags.SIGNED_BOOKS);

    this.subscriptions.add(config.modEnabled.savedValue.subscribe(() -> {
      for (Callback callback : this.callbacks) {
        callback.execute();
      }
    }));
  }

  public void clear() {
    this.callbacks.clear();

    for (Observable.Subscription subscription : this.subscriptions) {
      subscription.unsubscribe();
    }
    this.subscriptions.clear();

    for (Item item : this.dirtyItems) {
      item.stackables$resetMaxCount();
    }
    this.dirtyItems.clear();
  }

  private void syncTagMaxStackSizeWithConfig(
      BooleanConfigOption modEnabled, BooleanConfigOption toggleOption, IntConfigOption countOption, TagKey<Item> tag
  ) {
    Callback callback = () -> {
      int count = modEnabled.getValue() && toggleOption.getValue() ? countOption.getValue() : 1;
      this.setMaxCountForTag(tag, count);
    };
    this.subscriptions.add(toggleOption.savedValue.subscribe(callback::execute));
    this.subscriptions.add(countOption.savedValue.subscribe(callback::execute));
    this.callbacks.add(callback);
  }

  private void syncTagMaxStackSizeWithConfig(
      BooleanConfigOption modEnabled, IntConfigOption countOption, TagKey<Item> tag
  ) {
    Callback callback = () -> {
      int count = modEnabled.getValue() ? countOption.getValue() : 1;
      this.setMaxCountForTag(tag, count);
    };
    this.subscriptions.add(countOption.savedValue.subscribe(callback::execute));
    this.callbacks.add(callback);
  }

  private void setMaxCountForTag(TagKey<Item> tag, int count) {
    Registries.ITEM.getOrCreateEntryList(tag)
        .stream()
        .map(RegistryEntry::value)
        .forEach((item) -> item.stackables$setMaxCount(count));
  }

  @FunctionalInterface
  interface Callback {
    void execute();
  }
}
