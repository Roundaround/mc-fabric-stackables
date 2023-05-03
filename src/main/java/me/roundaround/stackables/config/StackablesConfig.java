package me.roundaround.stackables.config;

import me.roundaround.roundalib.config.ModConfig;
import me.roundaround.roundalib.config.option.BooleanConfigOption;
import me.roundaround.roundalib.config.option.IntConfigOption;
import me.roundaround.stackables.StackablesMod;

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
  public final BooleanConfigOption HORNS;
  public final IntConfigOption HORN_COUNT;
  public final BooleanConfigOption PATTERNS;
  public final IntConfigOption PATTERN_COUNT;
  public final BooleanConfigOption SADDLES;
  public final IntConfigOption SADDLE_COUNT;
  public final BooleanConfigOption CAKES;
  public final IntConfigOption CAKE_COUNT;
  // public final IntConfigOption ARMOR_STAND_COUNT;
  // public final IntConfigOption BANNER_COUNT; // All colors
  // public final IntConfigOption BOTTLE_COUNT; // Includes honey bottles
  // public final IntConfigOption SIGN_COUNT; // All wood types
  // public final IntConfigOption SNOWBALL_COUNT;
  // public final IntConfigOption EGG_COUNT;
  // public final IntConfigOption ENDER_PEARL_COUNT;
  // public final IntConfigOption BOOK_AND_QUILL_COUNT;

  public StackablesConfig() {
    super(StackablesMod.MOD_ID);

    MOD_ENABLED = registerConfigOption(
        BooleanConfigOption
            .builder(this, "modEnabled", "stackables.mod_enabled.label")
            .setComment("Simple toggle for the mod! Set to false to disable.")
            .build());

    POTIONS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "potions", "stackables.potions.label")
            .setComment("Whether to allow regular & lingering potions to stack.")
            .build());

    POTION_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "potionCount", "stackables.potion_count.label")
            .setComment("Maximum stack size for regular & lingering potions.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    SPLASH_POTIONS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "splashPotions", "stackables.splash_potions.label")
            .setComment("Whether to allow splash potions to stack.")
            .build());

    SPLASH_POTION_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "splashPotionCount", "stackables.splash_potion_count.label")
            .setComment("Maximum stack size for splash potions.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    SPLASH_POTION_DELAY = registerConfigOption(
        IntConfigOption
            .builder(this, "splashPotionDelay", "stackables.splash_potion_delay.label")
            .setComment("The delay/cooldown to add to throwing splash potions,", "in game ticks.")
            .setMinValue(0)
            .setMaxValue(20)
            .setDefaultValue(0)
            .build());

    SOUPS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "soups", "stackables.soups.label")
            .setComment("Whether to allow soups to stack.")
            .build());

    SOUP_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "soupCount", "stackables.soup_count.label")
            .setComment("Maximum stack size for soups.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    ENCHANTED_BOOKS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "enchantedBooks", "stackables.enchanted_books.label")
            .setComment("Whether to allow enchanted books to stack.")
            .build());

    ENCHANTED_BOOK_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "enchantedBookCount", "stackables.enchanted_book_count.label")
            .setComment("Maximum stack size for enchanted books.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    BUCKETS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "buckets", "stackables.buckets.label")
            .setComment("Whether to allow full buckets to stack.")
            .build());

    BUCKET_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "bucketCount", "stackables.bucket_count.label")
            .setComment("Maximum stack size for full buckets.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    BOATS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "boats", "stackables.boats.label")
            .setComment("Whether to allow boats to stack.")
            .build());

    BOAT_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "boatCount", "stackables.boat_count.label")
            .setComment("Maximum stack size for boats.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(64)
            .build());

    MINECARTS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "minecarts", "stackables.minecarts.label")
            .setComment("Whether to allow minecarts to stack.")
            .build());

    MINECART_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "minecartCount", "stackables.minecart_count.label")
            .setComment("Maximum stack size for minecarts.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(64)
            .build());

    BEDS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "beds", "stackables.beds.label")
            .setComment("Whether to allow beds to stack.")
            .build());

    BED_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "bedCount", "stackables.bed_count.label")
            .setComment("Maximum stack size for beds.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(64)
            .build());

    DISCS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "discs", "stackables.discs.label")
            .setComment("Whether to allow music discs to stack.")
            .build());

    DISC_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "discCount", "stackables.disc_count.label")
            .setComment("Maximum stack size for music discs.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    HORNS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "horns", "stackables.horns.label")
            .setComment("Whether to allow goat horns to stack.")
            .build());

    HORN_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "hornCount", "stackables.horn_count.label")
            .setComment("Maximum stack size for goat horns.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    PATTERNS = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "patterns", "stackables.patterns.label")
            .setComment("Whether to allow banner patterns to stack.")
            .build());

    PATTERN_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "patternCount", "stackables.pattern_count.label")
            .setComment("Maximum stack size for banner patterns.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    SADDLES = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "saddles", "stackables.saddles.label")
            .setComment("Whether to allow saddles to stack.")
            .build());

    SADDLE_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "saddleCount", "stackables.saddle_count.label")
            .setComment("Maximum stack size for saddles.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());

    CAKES = registerConfigOption(
        BooleanConfigOption
            .yesNoBuilder(this, "cakes", "stackables.cakes.label")
            .setComment("Whether to allow cakes to stack.")
            .build());

    CAKE_COUNT = registerConfigOption(
        IntConfigOption
            .builder(this, "cakeCount", "stackables.cake_count.label")
            .setComment("Maximum stack size for cakes.")
            .setMinValue(1)
            .setMaxValue(64)
            .setDefaultValue(16)
            .build());
  }
}
