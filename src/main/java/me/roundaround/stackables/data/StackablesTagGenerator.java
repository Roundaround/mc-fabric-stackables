package me.roundaround.stackables.data;

import me.roundaround.stackables.compat.StackablesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class StackablesTagGenerator extends FabricTagProvider.ItemTagProvider {
  public StackablesTagGenerator(
      FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
    super(output, completableFuture);
  }

  @Override
  protected void configure(RegistryWrapper.WrapperLookup lookup) {
    getOrCreateTagBuilder(StackablesTags.POTIONS)
        .add(Items.POTION)
        .add(Items.LINGERING_POTION)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.SPLASH_POTIONS)
        .add(Items.SPLASH_POTION)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.SOUPS)
        .add(Items.MUSHROOM_STEW)
        .add(Items.RABBIT_STEW)
        .add(Items.BEETROOT_SOUP)
        .add(Items.SUSPICIOUS_STEW)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.ENCHANTED_BOOKS)
        .add(Items.ENCHANTED_BOOK)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.BOATS)
        .addOptionalTag(ItemTags.BOATS)
        .addOptionalTag(ItemTags.CHEST_BOATS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.MINECARTS)
        .add(Items.MINECART)
        .add(Items.CHEST_MINECART)
        .add(Items.FURNACE_MINECART)
        .add(Items.TNT_MINECART)
        .add(Items.HOPPER_MINECART)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.BUCKETS)
        .add(Items.POWDER_SNOW_BUCKET)
        .addOptionalTag(ConventionalItemTags.WATER_BUCKETS)
        .addOptionalTag(ConventionalItemTags.LAVA_BUCKETS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.EMPTY_BUCKETS)
        .addOptionalTag(ConventionalItemTags.EMPTY_BUCKETS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.BEDS)
        .addOptionalTag(ItemTags.BEDS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.MUSIC_DISCS)
        .addOptionalTag(ItemTags.MUSIC_DISCS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.INSTRUMENTS)
        .add(Items.GOAT_HORN)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.BANNER_PATTERNS)
        .add(Items.FLOWER_BANNER_PATTERN)
        .add(Items.CREEPER_BANNER_PATTERN)
        .add(Items.SKULL_BANNER_PATTERN)
        .add(Items.MOJANG_BANNER_PATTERN)
        .add(Items.GLOBE_BANNER_PATTERN)
        .add(Items.PIGLIN_BANNER_PATTERN)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.HORSE_EQUIPMENT)
        .add(Items.SADDLE)
        .add(Items.LEATHER_HORSE_ARMOR)
        .add(Items.IRON_HORSE_ARMOR)
        .add(Items.GOLDEN_HORSE_ARMOR)
        .add(Items.DIAMOND_HORSE_ARMOR)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.CAKES)
        .add(Items.CAKE)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.TOTEMS)
        .add(Items.TOTEM_OF_UNDYING)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.ARMOR_STANDS)
        .add(Items.ARMOR_STAND)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.BANNERS)
        .addOptionalTag(ItemTags.BANNERS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.SIGNS)
        .addOptionalTag(ItemTags.SIGNS)
        .addOptionalTag(ItemTags.HANGING_SIGNS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.THROWABLES)
        .add(Items.SNOWBALL)
        .add(Items.EGG)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.ENDER_PEARLS)
        .add(Items.ENDER_PEARL)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.DRINKABLES)
        .add(Items.HONEY_BOTTLE)
        .addOptionalTag(ConventionalItemTags.MILK_BUCKETS)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.SIGNED_BOOKS)
        .add(Items.WRITTEN_BOOK)
        .setReplace(true);

    getOrCreateTagBuilder(StackablesTags.DECORATED_POTS)
        .add(Items.DECORATED_POT)
        .setReplace(true);
  }
}
