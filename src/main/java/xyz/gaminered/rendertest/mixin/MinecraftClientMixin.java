package xyz.gaminered.rendertest.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.gaminered.rendertest.client.RendertestClient;
import xyz.gaminered.rendertest.item.RendertestItems;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Shadow @Nullable public ClientPlayerEntity player;

	@Inject(method = "doItemUse", at = @At("HEAD"))
	private void doItemUseHead(CallbackInfo ci) {
		assert this.player != null;
		if (!this.player.getStackInHand(Hand.MAIN_HAND).isOf(RendertestItems.BOX_SELECT_ITEM)) return;
		BlockHitResult hitResult = (BlockHitResult) this.player.raycast(100D, 0F, false);
		BlockPos pos = hitResult.getBlockPos();
		RendertestClient.box.setFirstPos(pos);
	}

	@Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
	private void doAttackHead(CallbackInfoReturnable<Boolean> cir) {
		assert this.player != null;
		if (!this.player.getStackInHand(Hand.MAIN_HAND).isOf(RendertestItems.BOX_SELECT_ITEM)) return;
		BlockHitResult hitResult = (BlockHitResult) this.player.raycast(100D, 0F, false);
		BlockPos pos = hitResult.getBlockPos();
		RendertestClient.box.setSecondPos(pos);

		cir.setReturnValue(false);
	}

	@Inject(method = "handleBlockBreaking", at = @At("HEAD"), cancellable = true)
	private void handleBlockBreakingHead(boolean breaking, CallbackInfo ci) {
		if (!breaking) return;
		assert this.player != null;
		if (!this.player.getStackInHand(Hand.MAIN_HAND).isOf(RendertestItems.BOX_SELECT_ITEM)) return;

		ci.cancel();
	}
}
