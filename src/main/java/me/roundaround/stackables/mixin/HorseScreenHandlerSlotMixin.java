package me.roundaround.stackables.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;

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
