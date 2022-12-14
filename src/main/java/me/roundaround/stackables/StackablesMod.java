package me.roundaround.stackables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.roundaround.stackables.config.StackablesConfig;
import me.roundaround.stackables.mixin.ItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;

public final class StackablesMod implements ModInitializer {
  public static final String MOD_ID = "stackables";
  public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
  public static final StackablesConfig CONFIG = new StackablesConfig();

  @Override
  public void onInitialize() {
    CONFIG.init();

    initAllStacking();

    CONFIG.MOD_ENABLED.subscribeToValueChanges((prev, curr) -> {
      initAllStacking();
    });

    CONFIG.POTIONS.subscribeToValueChanges((prev, curr) -> {
      initPotionStacking();
    });
    CONFIG.POTION_COUNT.subscribeToValueChanges((prev, curr) -> {
      initPotionStacking();
    });

    CONFIG.SPLASH_POTIONS.subscribeToValueChanges((prev, curr) -> {
      initSplashPotionStacking();
    });
    CONFIG.SPLASH_POTION_COUNT.subscribeToValueChanges((prev, curr) -> {
      initSplashPotionStacking();
    });

    CONFIG.SOUPS.subscribeToValueChanges((prev, curr) -> {
      initSoupStacking();
    });
    CONFIG.SOUP_COUNT.subscribeToValueChanges((prev, curr) -> {
      initSoupStacking();
    });

    CONFIG.ENCHANTED_BOOKS.subscribeToValueChanges((prev, curr) -> {
      initEnchantedBookStacking();
    });
    CONFIG.ENCHANTED_BOOK_COUNT.subscribeToValueChanges((prev, curr) -> {
      initEnchantedBookStacking();
    });

    CONFIG.BOATS.subscribeToValueChanges((prev, curr) -> {
      initBoatStacking();
    });
    CONFIG.BOAT_COUNT.subscribeToValueChanges((prev, curr) -> {
      initBoatStacking();
    });

    CONFIG.MINECARTS.subscribeToValueChanges((prev, curr) -> {
      initMinecartStacking();
    });
    CONFIG.MINECART_COUNT.subscribeToValueChanges((prev, curr) -> {
      initMinecartStacking();
    });

    CONFIG.BUCKETS.subscribeToValueChanges((prev, curr) -> {
      initBucketStacking();
    });
    CONFIG.BUCKET_COUNT.subscribeToValueChanges((prev, curr) -> {
      initBucketStacking();
    });

    CONFIG.BEDS.subscribeToValueChanges((prev, curr) -> {
      initBedStacking();
    });
    CONFIG.BED_COUNT.subscribeToValueChanges((prev, curr) -> {
      initBedStacking();
    });

    CONFIG.DISCS.subscribeToValueChanges((prev, curr) -> {
      initDiscStacking();
    });
    CONFIG.DISC_COUNT.subscribeToValueChanges((prev, curr) -> {
      initDiscStacking();
    });

    CONFIG.HORNS.subscribeToValueChanges((prev, curr) -> {
      initHornStacking();
    });
    CONFIG.HORN_COUNT.subscribeToValueChanges((prev, curr) -> {
      initHornStacking();
    });

    CONFIG.PATTERNS.subscribeToValueChanges((prev, curr) -> {
      initPatternStacking();
    });
    CONFIG.PATTERN_COUNT.subscribeToValueChanges((prev, curr) -> {
      initPatternStacking();
    });

    CONFIG.SADDLES.subscribeToValueChanges((prev, curr) -> {
      initSaddleStacking();
    });
    CONFIG.SADDLE_COUNT.subscribeToValueChanges((prev, curr) -> {
      initSaddleStacking();
    });

    CONFIG.CAKES.subscribeToValueChanges((prev, curr) -> {
      initCakeStacking();
    });
    CONFIG.CAKE_COUNT.subscribeToValueChanges((prev, curr) -> {
      initCakeStacking();
    });
  }

  private void initAllStacking() {
    initPotionStacking();
    initSplashPotionStacking();
    initSoupStacking();
    initEnchantedBookStacking();
    initBoatStacking();
    initMinecartStacking();
    initBucketStacking();
    initBedStacking();
    initDiscStacking();
    initHornStacking();
    initPatternStacking();
    initSaddleStacking();
    initCakeStacking();
  }

  private void initPotionStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.POTIONS.getValue() ? CONFIG.POTION_COUNT.getValue() : 1;
    ((ItemAccessor) Items.POTION).setMaxCount(count);
    ((ItemAccessor) Items.LINGERING_POTION).setMaxCount(count);
  }

  private void initSplashPotionStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.SPLASH_POTIONS.getValue()
        ? CONFIG.SPLASH_POTION_COUNT.getValue()
        : 1;
    ((ItemAccessor) Items.SPLASH_POTION).setMaxCount(count);
  }

  private void initSoupStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.SOUPS.getValue() ? CONFIG.SOUP_COUNT.getValue() : 1;
    ((ItemAccessor) Items.MUSHROOM_STEW).setMaxCount(count);
    ((ItemAccessor) Items.RABBIT_STEW).setMaxCount(count);
    ((ItemAccessor) Items.BEETROOT_SOUP).setMaxCount(count);
    ((ItemAccessor) Items.SUSPICIOUS_STEW).setMaxCount(count);
  }

  private void initEnchantedBookStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.ENCHANTED_BOOKS.getValue()
        ? CONFIG.ENCHANTED_BOOK_COUNT.getValue()
        : 1;
    ((ItemAccessor) Items.ENCHANTED_BOOK).setMaxCount(count);
  }

  private void initBoatStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.BOATS.getValue() ? CONFIG.BOAT_COUNT.getValue() : 1;
    ((ItemAccessor) Items.OAK_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.OAK_CHEST_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.SPRUCE_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.SPRUCE_CHEST_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.BIRCH_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.BIRCH_CHEST_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.JUNGLE_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.JUNGLE_CHEST_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.ACACIA_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.ACACIA_CHEST_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.DARK_OAK_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.DARK_OAK_CHEST_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.MANGROVE_BOAT).setMaxCount(count);
    ((ItemAccessor) Items.MANGROVE_CHEST_BOAT).setMaxCount(count);
  }

  private void initMinecartStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.MINECARTS.getValue() ? CONFIG.MINECART_COUNT.getValue() : 1;
    ((ItemAccessor) Items.MINECART).setMaxCount(count);
    ((ItemAccessor) Items.CHEST_MINECART).setMaxCount(count);
    ((ItemAccessor) Items.FURNACE_MINECART).setMaxCount(count);
    ((ItemAccessor) Items.TNT_MINECART).setMaxCount(count);
    ((ItemAccessor) Items.HOPPER_MINECART).setMaxCount(count);
  }

  private void initBucketStacking() {
    // Special case for regular buckets, since their default is 16.
    int baseCount = Math
        .max(CONFIG.MOD_ENABLED.getValue() && CONFIG.BUCKETS.getValue() ? CONFIG.BUCKET_COUNT.getValue() : 16, 16);
    ((ItemAccessor) Items.BUCKET).setMaxCount(baseCount);

    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.BUCKETS.getValue() ? CONFIG.BUCKET_COUNT.getValue() : 1;
    ((ItemAccessor) Items.WATER_BUCKET).setMaxCount(count);
    ((ItemAccessor) Items.LAVA_BUCKET).setMaxCount(count);
    ((ItemAccessor) Items.POWDER_SNOW_BUCKET).setMaxCount(count);
    ((ItemAccessor) Items.MILK_BUCKET).setMaxCount(count);
  }

  private void initBedStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.BEDS.getValue() ? CONFIG.BED_COUNT.getValue() : 1;
    ((ItemAccessor) Items.WHITE_BED).setMaxCount(count);
    ((ItemAccessor) Items.ORANGE_BED).setMaxCount(count);
    ((ItemAccessor) Items.MAGENTA_BED).setMaxCount(count);
    ((ItemAccessor) Items.LIGHT_BLUE_BED).setMaxCount(count);
    ((ItemAccessor) Items.YELLOW_BED).setMaxCount(count);
    ((ItemAccessor) Items.LIME_BED).setMaxCount(count);
    ((ItemAccessor) Items.PINK_BED).setMaxCount(count);
    ((ItemAccessor) Items.GRAY_BED).setMaxCount(count);
    ((ItemAccessor) Items.LIGHT_GRAY_BED).setMaxCount(count);
    ((ItemAccessor) Items.CYAN_BED).setMaxCount(count);
    ((ItemAccessor) Items.PURPLE_BED).setMaxCount(count);
    ((ItemAccessor) Items.BLUE_BED).setMaxCount(count);
    ((ItemAccessor) Items.BROWN_BED).setMaxCount(count);
    ((ItemAccessor) Items.GREEN_BED).setMaxCount(count);
    ((ItemAccessor) Items.RED_BED).setMaxCount(count);
    ((ItemAccessor) Items.BLACK_BED).setMaxCount(count);
  }

  private void initDiscStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.DISCS.getValue() ? CONFIG.DISC_COUNT.getValue() : 1;
    ((ItemAccessor) Items.MUSIC_DISC_13).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_CAT).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_BLOCKS).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_CHIRP).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_FAR).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_MALL).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_MELLOHI).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_STAL).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_STRAD).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_WARD).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_11).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_WAIT).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_OTHERSIDE).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_5).setMaxCount(count);
    ((ItemAccessor) Items.MUSIC_DISC_PIGSTEP).setMaxCount(count);
  }

  private void initHornStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.HORNS.getValue() ? CONFIG.HORN_COUNT.getValue() : 1;
    ((ItemAccessor) Items.GOAT_HORN).setMaxCount(count);
  }

  private void initPatternStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.PATTERNS.getValue() ? CONFIG.PATTERN_COUNT.getValue() : 1;
    ((ItemAccessor) Items.FLOWER_BANNER_PATTERN).setMaxCount(count);
    ((ItemAccessor) Items.CREEPER_BANNER_PATTERN).setMaxCount(count);
    ((ItemAccessor) Items.SKULL_BANNER_PATTERN).setMaxCount(count);
    ((ItemAccessor) Items.MOJANG_BANNER_PATTERN).setMaxCount(count);
    ((ItemAccessor) Items.GLOBE_BANNER_PATTERN).setMaxCount(count);
    ((ItemAccessor) Items.PIGLIN_BANNER_PATTERN).setMaxCount(count);
  }

  private void initSaddleStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.SADDLES.getValue() ? CONFIG.SADDLE_COUNT.getValue() : 1;
    ((ItemAccessor) Items.SADDLE).setMaxCount(count);
  }

  private void initCakeStacking() {
    int count = CONFIG.MOD_ENABLED.getValue() && CONFIG.CAKES.getValue() ? CONFIG.CAKE_COUNT.getValue() : 1;
    ((ItemAccessor) Items.CAKE).setMaxCount(count);
  }
}
