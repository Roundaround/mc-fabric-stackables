package me.roundaround.stackables.data;

import me.roundaround.stackables.compat.StackablesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class StackablesTagGenerator extends FabricTagProvider.ItemTagProvider {
  public StackablesTagGenerator(
      FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture
  ) {
    super(output, completableFuture);
  }

  @Override
  protected void configure(RegistryWrapper.WrapperLookup lookup) {
    this.getOrCreateTagBuilder(StackablesTags.POTIONS).add(Items.POTION).add(Items.LINGERING_POTION).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.SPLASH_POTIONS).add(Items.SPLASH_POTION).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.SOUPS)
        .add(Items.MUSHROOM_STEW)
        .add(Items.RABBIT_STEW)
        .add(Items.BEETROOT_SOUP)
        .add(Items.SUSPICIOUS_STEW)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.ENCHANTED_BOOKS).add(Items.ENCHANTED_BOOK).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.BOATS)
        .addOptionalTag(ItemTags.BOATS)
        .addOptionalTag(ItemTags.CHEST_BOATS)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.MINECARTS)
        .add(Items.MINECART)
        .add(Items.CHEST_MINECART)
        .add(Items.FURNACE_MINECART)
        .add(Items.TNT_MINECART)
        .add(Items.HOPPER_MINECART)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.BUCKETS)
        .add(Items.POWDER_SNOW_BUCKET)
        .addOptionalTag(ConventionalItemTags.WATER_BUCKETS)
        .addOptionalTag(ConventionalItemTags.LAVA_BUCKETS)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.EMPTY_BUCKETS)
        .addOptionalTag(ConventionalItemTags.EMPTY_BUCKETS)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.BEDS).addOptionalTag(ItemTags.BEDS).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.MUSIC_DISCS).addOptionalTag(ItemTags.MUSIC_DISCS).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.INSTRUMENTS).add(Items.GOAT_HORN).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.BANNER_PATTERNS)
        .add(Items.FLOWER_BANNER_PATTERN)
        .add(Items.CREEPER_BANNER_PATTERN)
        .add(Items.SKULL_BANNER_PATTERN)
        .add(Items.MOJANG_BANNER_PATTERN)
        .add(Items.GLOBE_BANNER_PATTERN)
        .add(Items.PIGLIN_BANNER_PATTERN)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.HORSE_EQUIPMENT)
        .add(Items.SADDLE)
        .add(Items.LEATHER_HORSE_ARMOR)
        .add(Items.IRON_HORSE_ARMOR)
        .add(Items.GOLDEN_HORSE_ARMOR)
        .add(Items.DIAMOND_HORSE_ARMOR)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.CAKES).add(Items.CAKE).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.TOTEMS).add(Items.TOTEM_OF_UNDYING).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.ARMOR_STANDS).add(Items.ARMOR_STAND).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.BANNERS).addOptionalTag(ItemTags.BANNERS).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.SIGNS)
        .addOptionalTag(ItemTags.SIGNS)
        .addOptionalTag(ItemTags.HANGING_SIGNS)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.THROWABLES).add(Items.SNOWBALL).add(Items.EGG).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.ENDER_PEARLS).add(Items.ENDER_PEARL).setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.DRINKABLES)
        .add(Items.HONEY_BOTTLE)
        .addOptionalTag(ConventionalItemTags.MILK_BUCKETS)
        .setReplace(true);

    this.getOrCreateTagBuilder(StackablesTags.SIGNED_BOOKS).add(Items.WRITTEN_BOOK).setReplace(true);
  }
}
