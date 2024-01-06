package me.roundaround.stackables.compat;

import me.roundaround.stackables.StackablesMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public abstract class StackablesTags {
  public static final TagKey<Item> POTIONS = register("potions");
  public static final TagKey<Item> SPLASH_POTIONS = register("splash_potions");
  public static final TagKey<Item> SOUPS = register("soups");
  public static final TagKey<Item> ENCHANTED_BOOKS = register("enchanted_books");
  public static final TagKey<Item> BOATS = register("boats");
  public static final TagKey<Item> MINECARTS = register("minecarts");
  public static final TagKey<Item> BUCKETS = register("buckets");
  public static final TagKey<Item> BEDS = register("beds");
  public static final TagKey<Item> MUSIC_DISCS = register("music_discs");
  public static final TagKey<Item> INSTRUMENTS = register("instruments");
  public static final TagKey<Item> BANNER_PATTERNS = register("banner_patterns");
  public static final TagKey<Item> HORSE_EQUIPMENT = register("horse_equipment");
  public static final TagKey<Item> CAKES = register("cakes");
  public static final TagKey<Item> TOTEMS = register("totems");
  public static final TagKey<Item> EMPTY_BUCKETS = register("empty_buckets");
  public static final TagKey<Item> ARMOR_STANDS = register("armor_stands");
  public static final TagKey<Item> BANNERS = register("banners");
  public static final TagKey<Item> SIGNS = register("signs");
  public static final TagKey<Item> THROWABLES = register("throwables");
  public static final TagKey<Item> ENDER_PEARLS = register("ender_pearls");
  public static final TagKey<Item> DRINKABLES = register("drinkables");
  public static final TagKey<Item> SIGNED_BOOKS = register("signed_books");
  public static final TagKey<Item> DECORATED_POTS = register("decorated_pots");

  private static TagKey<Item> register(String id) {
    return TagKey.of(RegistryKeys.ITEM, new Identifier(StackablesMod.MOD_ID, id));
  }
}
