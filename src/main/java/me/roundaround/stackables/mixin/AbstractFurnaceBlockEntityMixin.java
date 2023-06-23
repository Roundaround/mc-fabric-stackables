package me.roundaround.stackables.mixin;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
  @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V", shift = At.Shift.AFTER))
  private static void afterDecrementFuel(
      World world,
      BlockPos pos,
      BlockState state,
      AbstractFurnaceBlockEntity blockEntity,
      CallbackInfo ci) {
    ItemStack fuel = blockEntity.getStack(1);
    Item byproduct = fuel.getItem().getRecipeRemainder();

    if (byproduct == null || fuel.isEmpty()) {
      return;
    }

    Direction direction = state.get(AbstractFurnaceBlock.FACING);
    Position position = getOutputLocation(pos, state);
    ItemDispenserBehavior.spawnItem(world, new ItemStack(byproduct), 6, direction, position);
  }

  private static Position getOutputLocation(BlockPos pos, BlockState state) {
    Direction direction = state.get(AbstractFurnaceBlock.FACING);
    double x = pos.getX() + 0.7 * direction.getOffsetX();
    double y = pos.getY() + 0.7 * direction.getOffsetY();
    double z = pos.getZ() + 0.7 * direction.getOffsetZ();
    return new PositionImpl(x, y, z);
  }
}
