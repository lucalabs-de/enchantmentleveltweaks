package de.lucalabs.enchantmentlevels.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
abstract class EnchantmentHelperMixin {
	@Inject(at = @At("RETURN"), method = "getLevelFromNbt", cancellable = true)
	private static void returnNonClampedLevel(NbtCompound nbt, CallbackInfoReturnable<Integer> cir) {
		// > returnNonClampedLevel
		// > Look inside
		// > Clamped
		cir.setReturnValue(MathHelper.clamp(nbt.getInt("lvl"), 0, 3999));
	}
}