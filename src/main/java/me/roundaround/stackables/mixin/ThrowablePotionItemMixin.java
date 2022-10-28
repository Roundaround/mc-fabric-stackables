package me.roundaround.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.roundaround.stackables.StackablesMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

@Mixin(ThrowablePotionItem.class)
public abstract class ThrowablePotionItemMixin extends PotionItem {
  private ThrowablePotionItemMixin(Settings settings) {
    super(settings);
  }

  @Inject(method = "use", at = @At(value = "RETURN"))
  public void use(
      World world,
      PlayerEntity user,
      Hand hand,
      CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
    if (!StackablesMod.CONFIG.MOD_ENABLED.getValue()
        || !StackablesMod.CONFIG.SPLASH_POTIONS.getValue()
        || StackablesMod.CONFIG.SPLASH_POTION_COUNT.getValue() == 1
        || StackablesMod.CONFIG.SPLASH_POTION_DELAY.getValue() == 0) {
      return;
    }

    user.getItemCooldownManager().set((ThrowablePotionItem) (Object) this,
        StackablesMod.CONFIG.SPLASH_POTION_DELAY.getValue());
  }
}
