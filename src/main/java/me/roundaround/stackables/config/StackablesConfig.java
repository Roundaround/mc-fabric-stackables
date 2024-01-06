package me.roundaround.stackables.config;

import me.roundaround.roundalib.config.ModConfig;
import me.roundaround.roundalib.config.option.BooleanConfigOption;
import me.roundaround.roundalib.config.option.IntConfigOption;
import me.roundaround.roundalib.shadow.nightconfig.core.Config;
import me.roundaround.stackables.StackablesMod;

import java.util.Optional;

public class StackablesConfig extends ModConfig {
  public final BooleanConfigOption MOD_ENABLED;
  public final BooleanConfigOption POTIONS;
  public final IntConfigOption POTION_COUNT;
  public final BooleanConfigOption SPLASH_POTIONS;
  public final IntConfigOption SPLASH_POTION_COUNT;
  public final IntConfigOption SPLASH_POTION_DELAY;
  public final BooleanConfigOption SOUPS;
  public final IntConfigOption SOUP_COUNT;
  public final BooleanConfigOption ENCHANTED_BOOKS;
  public final IntConfigOption ENCHANTED_BOOK_COUNT;
  public final BooleanConfigOption BOATS;
  public final IntConfigOption BOAT_COUNT;
  public final BooleanConfigOption MINECARTS;
  public final IntConfigOption MINECART_COUNT;
  public final BooleanConfigOption BUCKETS;
  public final IntConfigOption BUCKET_COUNT;
  public final BooleanConfigOption BEDS;
  public final IntConfigOption BED_COUNT;
  public final BooleanConfigOption DISCS;
  public final IntConfigOption DISC_COUNT;
  public final BooleanConfigOption INSTRUMENTS;
  public final IntConfigOption INSTRUMENT_COUNT;
  public final BooleanConfigOption PATTERNS;
  public final IntConfigOption PATTERN_COUNT;
  public final BooleanConfigOption HORSE_EQUIPMENTS;
  public final IntConfigOption HORSE_EQUIPMENT_COUNT;
  public final BooleanConfigOption CAKES;
  public final IntConfigOption CAKE_COUNT;
  public final BooleanConfigOption TOTEMS;
  public final IntConfigOption TOTEM_COUNT;
  public final BooleanConfigOption DECORATED_POTS;
  public final IntConfigOption DECORATED_POT_COUNT;

  public final IntConfigOption EMPTY_BUCKET_COUNT;
  public final IntConfigOption ARMOR_STAND_COUNT;
  public final IntConfigOption BANNER_COUNT; // All colors
  public final IntConfigOption SIGN_COUNT; // All wood types
  public final IntConfigOption THROWABLE_COUNT;
  public final IntConfigOption ENDER_PEARL_COUNT;
  public final IntConfigOption DRINKABLE_COUNT;
  public final IntConfigOption SIGNED_BOOK_COUNT;

  public StackablesConfig() {
    super(StackablesMod.MOD_ID, ModConfig.options(StackablesMod.MOD_ID).setConfigVersion(2));

    MOD_ENABLED = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "modEnabled",
            "stackables.mod_enabled.label")
        .setComment("Simple toggle for the mod! Set to false to disable.")
        .build());

    POTIONS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "potions",
        "stackables.potions.label").setComment("Whether to allow potions to stack.").build());

    POTION_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "potionCount",
            "stackables.potion_count.label")
        .setComment("Maximum stack size for potions.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    SPLASH_POTIONS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "splashPotions",
            "stackables.splash_potions.label")
        .setComment("Whether to allow splash potions to stack.")
        .build());

    SPLASH_POTION_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "splashPotionCount",
            "stackables.splash_potion_count.label")
        .setComment("Maximum stack size for splash potions.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    SPLASH_POTION_DELAY = registerConfigOption(IntConfigOption.builder(this,
            "splashPotionDelay",
            "stackables.splash_potion_delay.label")
        .setComment("The delay/cooldown to add to throwing splash potions in game ticks.")
        .setMinValue(0)
        .setMaxValue(20)
        .setDefaultValue(0)
        .build());

    SOUPS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "soups",
        "stackables.soups.label").setComment("Whether to allow soups to stack.").build());

    SOUP_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "soupCount",
            "stackables.soup_count.label")
        .setComment("Maximum stack size for soups.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    ENCHANTED_BOOKS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "enchantedBooks",
            "stackables.enchanted_books.label")
        .setComment("Whether to allow enchanted books to stack.")
        .build());

    ENCHANTED_BOOK_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "enchantedBookCount",
            "stackables.enchanted_book_count.label")
        .setComment("Maximum stack size for enchanted books.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    BUCKETS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "buckets",
        "stackables.buckets.label").setComment("Whether to allow full buckets to stack.").build());

    BUCKET_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "bucketCount",
            "stackables.bucket_count.label")
        .setComment("Maximum stack size for full buckets.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    BOATS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "boats",
        "stackables.boats.label").setComment("Whether to allow boats to stack.").build());

    BOAT_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "boatCount",
            "stackables.boat_count.label")
        .setComment("Maximum stack size for boats.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(64)
        .build());

    MINECARTS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "minecarts",
        "stackables.minecarts.label").setComment("Whether to allow minecarts to stack.").build());

    MINECART_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "minecartCount",
            "stackables.minecart_count.label")
        .setComment("Maximum stack size for minecarts.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(64)
        .build());

    BEDS =
        registerConfigOption(BooleanConfigOption.yesNoBuilder(this, "beds", "stackables.beds.label")
            .setComment("Whether to allow beds to stack.")
            .build());

    BED_COUNT =
        registerConfigOption(IntConfigOption.builder(this, "bedCount", "stackables.bed_count.label")
            .setComment("Maximum stack size for beds.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(64)
            .build());

    DISCS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "discs",
        "stackables.discs.label").setComment("Whether to allow music discs to stack.").build());

    DISC_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "discCount",
            "stackables.disc_count.label")
        .setComment("Maximum stack size for music discs.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    INSTRUMENTS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "instruments",
            "stackables.instruments.label")
        .setComment("Whether to allow instruments (i.e. goat horns) to stack.")
        .build());

    INSTRUMENT_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "instrumentCount",
            "stackables.instrument_count.label")
        .setComment("Maximum stack size for instruments (i.e. goat horns).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    PATTERNS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "patterns",
            "stackables.patterns.label")
        .setComment("Whether to allow banner patterns to stack.")
        .build());

    PATTERN_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "patternCount",
            "stackables.pattern_count.label")
        .setComment("Maximum stack size for banner patterns.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    HORSE_EQUIPMENTS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
            "horseEquipments",
            "stackables.horse_equipments.label")
        .setComment("Whether to allow horse equipments (i.e. saddles and armor) to stack.")
        .build());

    HORSE_EQUIPMENT_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "horseEquipmentCount",
            "stackables.horse_equipment_count.label")
        .setComment("Maximum stack size for horse equipment (i.e. saddles and armor).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    CAKES = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "cakes",
        "stackables.cakes.label").setComment("Whether to allow cakes to stack.").build());

    CAKE_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "cakeCount",
            "stackables.cake_count.label")
        .setComment("Maximum stack size for cakes.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    TOTEMS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "totems",
        "stackables.totems.label").setComment("Whether to allow totems to stack.").build());

    TOTEM_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "totemCount",
            "stackables.totem_count.label")
        .setComment("Maximum stack size for totems.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    DECORATED_POTS = registerConfigOption(BooleanConfigOption.yesNoBuilder(this,
        "decoratedPots",
        "stackables.decorated_pots.label").setComment("Whether to allow decorated pots to stack.").build());

    DECORATED_POT_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "decoratedPotCount",
            "stackables.decorated_pot_count.label")
        .setComment("Maximum stack size for decorated pots.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(64)
        .build());

    EMPTY_BUCKET_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "emptyBucketCount",
            "stackables.empty_bucket_count.label")
        .setComment("Maximum stack size for empty buckets.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    ARMOR_STAND_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "armorStandCount",
            "stackables.armor_stand_count.label")
        .setComment("Maximum stack size for armor stands.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    BANNER_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "bannerCount",
            "stackables.banner_count.label")
        .setComment("Maximum stack size for banners.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    SIGN_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "signCount",
            "stackables.sign_count.label")
        .setComment("Maximum stack size for signs.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    THROWABLE_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "throwableCount",
            "stackables.throwable_count.label")
        .setComment("Maximum stack size for throwables (i.e. snowballs and chicken eggs).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    ENDER_PEARL_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "enderPearlCount",
            "stackables.ender_pearl_count.label")
        .setComment("Maximum stack size for ender pearls.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    DRINKABLE_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "drinkableCount",
            "stackables.drinkable_count.label")
        .setComment("Maximum stack size for drinkables (i.e. honey bottles and milk buckets).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    SIGNED_BOOK_COUNT = registerConfigOption(IntConfigOption.builder(this,
            "signedBookCount",
            "stackables.signed_book_count.label")
        .setComment("Maximum stack size for signed books.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());
  }

  @Override
  protected boolean updateConfigVersion(int version, Config config) {
    if (version == 1) {
      // Horns got renamed to instruments
      getBooleanFromOldId(config, "horns").ifPresent(value -> {
        setBoolean(config, "instruments", value);
      });
      getIntegerFromOldId(config, "hornCount").ifPresent(value -> {
        setInteger(config, "instrumentCount", value);
      });

      // Saddles got renamed to horseEquipment
      getBooleanFromOldId(config, "saddles").ifPresent(value -> {
        setBoolean(config, "horseEquipments", value);
      });
      getIntegerFromOldId(config, "saddleCount").ifPresent(value -> {
        setInteger(config, "horseEquipmentCount", value);
      });

      // Empty buckets is a new option, default it to the value of buckets if it exists
      getIntegerFromOldId(config, "bucketCount").ifPresent(value -> {
        setInteger(config, "emptyBucketCount", value);
      });

      // Eggs and snowballs got grouped into throwables
      Optional<Integer> eggCount = getIntegerFromOldId(config, "eggCount");
      Optional<Integer> snowballCount = getIntegerFromOldId(config, "snowballCount");
      int maxPreviousCount = Math.max(eggCount.orElse(-1), snowballCount.orElse(-1));
      if (maxPreviousCount != -1) {
        setInteger(config, "throwableCount", maxPreviousCount);
      }

      // Honey bottles and milk buckets got grouped into drinkables
      getIntegerFromOldId(config, "honeyBottleCount").ifPresent(value -> {
        setInteger(config, "drinkableCount", value);
      });

      return true;
    }

    return false;
  }

  private static void setBoolean(Config config, String id, boolean value) {
    String path = String.format("%s.%s", StackablesMod.MOD_ID, id);
    config.set(path, value);
  }

  private static void setInteger(Config config, String id, int value) {
    String path = String.format("%s.%s", StackablesMod.MOD_ID, id);
    config.set(path, value);
  }

  private static Optional<Boolean> getBooleanFromOldId(Config config, String oldId) {
    String path = String.format("%s.%s", StackablesMod.MOD_ID, oldId);
    if (!config.contains(path)) {
      return Optional.empty();
    }
    boolean value = config.get(path);
    config.remove(path);
    return Optional.of(value);
  }

  private static Optional<Integer> getIntegerFromOldId(Config config, String oldId) {
    String path = String.format("%s.%s", StackablesMod.MOD_ID, oldId);
    if (!config.contains(path)) {
      return Optional.empty();
    }
    int value = config.get(path);
    config.remove(path);
    return Optional.of(value);
  }
}
