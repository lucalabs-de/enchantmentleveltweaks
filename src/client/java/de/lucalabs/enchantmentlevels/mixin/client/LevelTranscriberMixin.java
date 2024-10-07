package de.lucalabs.enchantmentlevels.mixin.client;

import de.lucalabs.enchantmentlevels.utils.Numerals;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(Enchantment.class)
abstract class LevelTranscriberMixin {
    @Redirect(
            method = "getName",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;)Lnet/minecraft/text/MutableText;"))
    private MutableText computeRomanNumeralFromLevelKey(String key) {
        Pattern pattern = Pattern.compile("enchantment\\.level\\.(\\d*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(key);

        if (matcher.find()) {
            int level = Integer.parseInt(matcher.group(1));
            String romanNumeral = Numerals.computeRomanNumeral(level);
            return MutableText.of(new LiteralTextContent(romanNumeral));
        }

        return Text.translatable(key);
    }
}