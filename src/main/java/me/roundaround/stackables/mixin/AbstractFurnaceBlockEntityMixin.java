package me.roundaround.stackables.mixin;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
  @Inject(
      method = "tick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V", shift = At.Shift.AFTER)
  )
  private static void afterDecrementFuel(
      World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci
  ) {
    ItemStack fuel = blockEntity.getStack(1);
    Item byproduct = fuel.getItem().getRecipeRemainder();

    if (byproduct == null || fuel.isEmpty() || !(world instanceof ServerWorld serverWorld)) {
      return;
    }

    BlockState virtualDispenserState = Blocks.DISPENSER.getDefaultState()
        .with(DispenserBlock.FACING, state.get(AbstractFurnaceBlock.FACING));
    BlockPointer blockPointer = new BlockPointer(serverWorld,
        pos,
        virtualDispenserState,
        new DispenserBlockEntity(pos, state)
    );
    new ItemDispenserBehavior().dispense(blockPointer, new ItemStack(byproduct));
  }
}
