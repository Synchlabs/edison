package com.Synchlabs.edison.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class GlowShardItem extends Item {

	public GlowShardItem() {
		super(new FabricItemSettings());
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		BlockPos pos = player.getBlockPos().offset(player.getHorizontalFacing()); // Get block position in front of the player

		// Check if the targeted block is air, if so, find the nearest non-air block
		if (world.getBlockState(pos).isAir()) {
			BlockHitResult blockHitResult = raycast(world, player);
			if (blockHitResult != null && blockHitResult.getType() == HitResult.Type.BLOCK) {
				pos = blockHitResult.getBlockPos();
			}
		}

		BlockState state = world.getBlockState(pos);

		// Modify the block's state accordingly
		BlockState modifiedState = state;

		if (modifiedState.contains(Properties.POWERED)) {
			modifiedState = modifiedState.with(Properties.POWERED, !state.get(Properties.POWERED));
		}
		if (modifiedState.contains(Properties.LIT)) {
			modifiedState = modifiedState.with(Properties.LIT, !state.get(Properties.LIT));
		}
		if (modifiedState.contains(Properties.OPEN)) {
			modifiedState = modifiedState.with(Properties.OPEN, !state.get(Properties.OPEN));
		}
		if (modifiedState.contains(Properties.TRIGGERED)) {
			modifiedState = modifiedState.with(Properties.TRIGGERED, !state.get(Properties.TRIGGERED));
		}

		// Set the modified block state if any property was changed
		if (modifiedState != state) {
			world.setBlockState(pos, modifiedState);
			return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
		}

		return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
	}

	// Helper method for raycasting
	private BlockHitResult raycast(World world, PlayerEntity player) {
		float f = player.getPitch();
		float g = player.getYaw();
		Vec3d vec3d = player.getCameraPosVec(1.0F);
		float h = MathHelper.cos(-g * 0.017453292F - 3.1415927F);
		float i = MathHelper.sin(-g * 0.017453292F - 3.1415927F);
		float j = -MathHelper.cos(-f * 0.017453292F);
		float k = MathHelper.sin(-f * 0.017453292F);
		float l = i * j;
		float n = h * j;
		Vec3d vec3d2 = vec3d.add(l * 5.0D, k * 5.0D, n * 5.0D);
		return world.raycast(new RaycastContext(vec3d, vec3d2, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
	}
}
