package de.lucalabs.enchantmentlevels.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @Redirect(
            method = "updateResult",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"))
    private int getUncappedLevel(Enchantment enchantment) {
        // don't allow stuff like Infinity II
        if (enchantment.getMaxLevel() == 1) {
            return 1;
        }
        return Integer.MAX_VALUE;
    }
}
