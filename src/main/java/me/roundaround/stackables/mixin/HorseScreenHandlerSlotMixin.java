package me.roundaround.stackables.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

@Mixin(targets = "net.minecraft.screen.HorseScreenHandler$1")
public abstract class HorseScreenHandlerSlotMixin extends Slot {
  public HorseScreenHandlerSlotMixin(Inventory inventory, int index, int x, int y) {
    super(inventory, index, x, y);
  }

  @Override
  public int getMaxItemCount() {
    return 1;
  }
}
