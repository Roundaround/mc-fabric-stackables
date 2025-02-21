package me.roundaround.stackables.mixin;

import me.roundaround.stackables.config.StackablesConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThrowablePotionItem.class)
public abstract class ThrowablePotionItemMixin extends PotionItem {
  private ThrowablePotionItemMixin(Settings settings) {
    super(settings);
  }

  @Inject(method = "use", at = @At(value = "RETURN"))
  public void use(
      World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> info
  ) {
    StackablesConfig config = StackablesConfig.getInstance();
    if (!config.modEnabled.getPendingValue() || !config.splashPotions.getPendingValue() ||
        config.splashPotionCount.getPendingValue() == 1 || config.splashPotionDelay.getPendingValue() == 0) {
      return;
    }

    user.getItemCooldownManager().set((ThrowablePotionItem) (Object) this, config.splashPotionDelay.getPendingValue());
  }
}
