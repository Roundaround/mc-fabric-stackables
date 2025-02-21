package me.roundaround.stackables.config;

import me.roundaround.roundalib.config.ConfigPath;
import me.roundaround.roundalib.config.manage.ModConfigImpl;
import me.roundaround.roundalib.config.manage.store.WorldScopedFileStore;
import me.roundaround.roundalib.config.option.BooleanConfigOption;
import me.roundaround.roundalib.config.option.IntConfigOption;
import me.roundaround.roundalib.nightconfig.core.Config;
import me.roundaround.stackables.StackablesMod;

import java.util.Optional;

public class StackablesConfig extends ModConfigImpl implements WorldScopedFileStore {
  private static StackablesConfig instance;

  public BooleanConfigOption modEnabled;
  public BooleanConfigOption potions;
  public IntConfigOption potionCount;
  public BooleanConfigOption splashPotions;
  public IntConfigOption splashPotionCount;
  public IntConfigOption splashPotionDelay;
  public BooleanConfigOption soups;
  public IntConfigOption soupCount;
  public BooleanConfigOption enchantedBooks;
  public IntConfigOption enchantedBookCount;
  public BooleanConfigOption boats;
  public IntConfigOption boatCount;
  public BooleanConfigOption minecarts;
  public IntConfigOption minecartCount;
  public BooleanConfigOption buckets;
  public IntConfigOption bucketCount;
  public BooleanConfigOption beds;
  public IntConfigOption bedCount;
  public BooleanConfigOption discs;
  public IntConfigOption discCount;
  public BooleanConfigOption instruments;
  public IntConfigOption instrumentCount;
  public BooleanConfigOption patterns;
  public IntConfigOption patternCount;
  public BooleanConfigOption horseEquipments;
  public IntConfigOption horseEquipmentCount;
  public BooleanConfigOption cakes;
  public IntConfigOption cakeCount;
  public BooleanConfigOption totems;
  public IntConfigOption totemCount;

  public IntConfigOption emptyBucketCount;
  public IntConfigOption armorStandCount;
  public IntConfigOption bannerCount; // All colors
  public IntConfigOption signCount; // All wood types
  public IntConfigOption throwableCount;
  public IntConfigOption enderPearlCount;
  public IntConfigOption drinkableCount;
  public IntConfigOption signedBookCount;

  public StackablesConfig() {
    super(StackablesMod.MOD_ID, 2);
  }

  public static StackablesConfig getInstance() {
    if (instance == null) {
      instance = new StackablesConfig();
    }
    return instance;
  }

  @Override
  protected void registerOptions() {
    this.modEnabled = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("modEnabled"))
        .setComment("Simple toggle for the mod! Set to false to disable.")
        .setDefaultValue(true)
        .build());

    this.potions = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("potions"))
        .setComment("Whether to allow potions to stack.")
        .setDefaultValue(true)
        .build());

    this.potionCount = this.register(IntConfigOption.builder(ConfigPath.of("potionCount"))
        .setComment("Maximum stack size for potions.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.splashPotions = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("splashPotions"))
        .setComment("Whether to allow splash potions to stack.")
        .setDefaultValue(true)
        .build());

    this.splashPotionCount = this.register(IntConfigOption.builder(ConfigPath.of("splashPotionCount"))
        .setComment("Maximum stack size for splash potions.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.splashPotionDelay = this.register(IntConfigOption.builder(ConfigPath.of("splashPotionDelay"))
        .setComment("The delay/cooldown to add to throwing splash potions in game ticks.")
        .setMinValue(0)
        .setMaxValue(20)
        .setDefaultValue(0)
        .build());

    this.soups = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("soups"))
        .setComment("Whether to allow soups to stack.")
        .setDefaultValue(true)
        .build());

    this.soupCount = this.register(IntConfigOption.builder(ConfigPath.of("soupCount"))
        .setComment("Maximum stack size for soups.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.enchantedBooks = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("enchantedBooks"))
        .setComment("Whether to allow enchanted books to stack.")
        .setDefaultValue(true)
        .build());

    this.enchantedBookCount = this.register(IntConfigOption.builder(ConfigPath.of("enchantedBookCount"))
        .setComment("Maximum stack size for enchanted books.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.buckets = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("buckets"))
        .setComment("Whether to allow full buckets to stack.")
        .setDefaultValue(true)
        .build());

    this.bucketCount = this.register(IntConfigOption.builder(ConfigPath.of("bucketCount"))
        .setComment("Maximum stack size for full buckets.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.boats = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("boats"))
        .setComment("Whether to allow boats to stack.")
        .setDefaultValue(true)
        .build());

    this.boatCount = this.register(IntConfigOption.builder(ConfigPath.of("boatCount"))
        .setComment("Maximum stack size for boats.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(64)
        .build());

    this.minecarts = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("minecarts"))
        .setComment("Whether to allow minecarts to stack.")
        .setDefaultValue(true)
        .build());

    this.minecartCount = this.register(IntConfigOption.builder(ConfigPath.of("minecartCount"))
        .setComment("Maximum stack size for minecarts.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(64)
        .build());

    this.beds = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("beds"))
        .setComment("Whether to allow beds to stack.")
        .setDefaultValue(true)
        .build());

    this.bedCount = this.register(IntConfigOption.builder(ConfigPath.of("bedCount"))
        .setComment("Maximum stack size for beds.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(64)
        .build());

    this.discs = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("discs"))
        .setComment("Whether to allow music discs to stack.")
        .setDefaultValue(true)
        .build());

    this.discCount = this.register(IntConfigOption.builder(ConfigPath.of("discCount"))
        .setComment("Maximum stack size for music discs.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.instruments = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("instruments"))
        .setComment("Whether to allow instruments (i.e. goat horns) to stack.")
        .setDefaultValue(true)
        .build());

    this.instrumentCount = this.register(IntConfigOption.builder(ConfigPath.of("instrumentCount"))
        .setComment("Maximum stack size for instruments (i.e. goat horns).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.patterns = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("patterns"))
        .setComment("Whether to allow banner patterns to stack.")
        .setDefaultValue(true)
        .build());

    this.patternCount = this.register(IntConfigOption.builder(ConfigPath.of("patternCount"))
        .setComment("Maximum stack size for banner patterns.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.horseEquipments = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("horseEquipments"))
        .setComment("Whether to allow horse equipments (i.e. saddles and armor) to stack.")
        .setDefaultValue(true)
        .build());

    this.horseEquipmentCount = this.register(IntConfigOption.builder(ConfigPath.of("horseEquipmentCount"))
        .setComment("Maximum stack size for horse equipment (i.e. saddles and armor).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.cakes = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("cakes"))
        .setComment("Whether to allow cakes to stack.")
        .setDefaultValue(true)
        .build());

    this.cakeCount = this.register(IntConfigOption.builder(ConfigPath.of("cakeCount"))
        .setComment("Maximum stack size for cakes.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.totems = this.register(BooleanConfigOption.yesNoBuilder(ConfigPath.of("totems"))
        .setComment("Whether to allow totems to stack.")
        .setDefaultValue(true)
        .build());

    this.totemCount = this.register(IntConfigOption.builder(ConfigPath.of("totemCount"))
        .setComment("Maximum stack size for totems.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.emptyBucketCount = this.register(IntConfigOption.builder(ConfigPath.of("emptyBucketCount"))
        .setComment("Maximum stack size for empty buckets.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.armorStandCount = this.register(IntConfigOption.builder(ConfigPath.of("armorStandCount"))
        .setComment("Maximum stack size for armor stands.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.bannerCount = this.register(IntConfigOption.builder(ConfigPath.of("bannerCount"))
        .setComment("Maximum stack size for banners.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.signCount = this.register(IntConfigOption.builder(ConfigPath.of("signCount"))
        .setComment("Maximum stack size for signs.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.throwableCount = this.register(IntConfigOption.builder(ConfigPath.of("throwableCount"))
        .setComment("Maximum stack size for throwables (i.e. snowballs and chicken eggs).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.enderPearlCount = this.register(IntConfigOption.builder(ConfigPath.of("enderPearlCount"))
        .setComment("Maximum stack size for ender pearls.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.drinkableCount = this.register(IntConfigOption.builder(ConfigPath.of("drinkableCount"))
        .setComment("Maximum stack size for drinkables (i.e. honey bottles and milk buckets).")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());

    this.signedBookCount = this.register(IntConfigOption.builder(ConfigPath.of("signedBookCount"))
        .setComment("Maximum stack size for signed books.")
        .setMinValue(1)
        .setMaxValue(64)
        .setDefaultValue(16)
        .build());
  }

  @Override
  public boolean performConfigUpdate(int version, Config config) {
    if (version == 1) {
      // Horns got renamed to instruments
      getBooleanFromOldId(config, "horns").ifPresent(value -> setBoolean(config, "instruments", value));
      getIntegerFromOldId(config, "hornCount").ifPresent(value -> setInteger(config, "instrumentCount", value));

      // Saddles got renamed to horseEquipment
      getBooleanFromOldId(config, "saddles").ifPresent(value -> setBoolean(config, "horseEquipments", value));
      getIntegerFromOldId(config, "saddleCount").ifPresent(value -> setInteger(config, "horseEquipmentCount", value));

      // Empty buckets is a new option, default it to the value of buckets if it exists
      getIntegerFromOldId(config, "bucketCount").ifPresent(value -> setInteger(config, "emptyBucketCount", value));

      // Eggs and snowballs got grouped into throwables
      Optional<Integer> eggCount = getIntegerFromOldId(config, "eggCount");
      Optional<Integer> snowballCount = getIntegerFromOldId(config, "snowballCount");
      int maxPreviousCount = Math.max(eggCount.orElse(-1), snowballCount.orElse(-1));
      if (maxPreviousCount != -1) {
        setInteger(config, "throwableCount", maxPreviousCount);
      }

      // Honey bottles and milk buckets got grouped into drinkables
      getIntegerFromOldId(config, "honeyBottleCount").ifPresent(value -> setInteger(config, "drinkableCount", value));

      return true;
    }

    return false;
  }

  @Override
  public void syncWithStore() {
    super.syncWithStore();
    MaxStackApplier.getInstance().init();
  }

  @Override
  public void clear() {
    super.clear();
    MaxStackApplier.getInstance().clear();
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
