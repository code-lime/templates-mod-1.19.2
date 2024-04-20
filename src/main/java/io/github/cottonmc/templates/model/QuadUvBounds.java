package io.github.cottonmc.templates.model;

import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadView;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.MathHelper;

record QuadUvBounds(float minU, float maxU, float minV, float maxV) {
	public static final int SPRITE_INDEX = 0;

	static QuadUvBounds read(QuadView quad) {
		float u0 = quad.spriteU(0, SPRITE_INDEX); float u1 = quad.spriteU(1, SPRITE_INDEX); float u2 = quad.spriteU(2, SPRITE_INDEX); float u3 = quad.spriteU(3, SPRITE_INDEX);
		float v0 = quad.spriteV(0, SPRITE_INDEX); float v1 = quad.spriteV(1, SPRITE_INDEX); float v2 = quad.spriteV(2, SPRITE_INDEX); float v3 = quad.spriteV(3, SPRITE_INDEX);
		return new QuadUvBounds(
			Math.min(Math.min(u0, u1), Math.min(u2, u3)),
			Math.max(Math.max(u0, u1), Math.max(u2, u3)),
			Math.min(Math.min(v0, v1), Math.min(v2, v3)),
			Math.max(Math.max(v0, v1), Math.max(v2, v3))
		);
	}
	
	boolean displaysSprite(Sprite sprite) {
		return sprite.getMinU() <= minU && sprite.getMaxU() >= maxU && sprite.getMinV() <= minV && sprite.getMaxV() >= maxV;
	}

	void normalizeUv(MutableQuadView quad, Sprite specialSprite) {
		float remappedMinU = norm(minU, specialSprite.getMinU(), specialSprite.getMaxU());
		float remappedMaxU = norm(maxU, specialSprite.getMinU(), specialSprite.getMaxU());
		float remappedMinV = norm(minV, specialSprite.getMinV(), specialSprite.getMaxV());
		float remappedMaxV = norm(maxV, specialSprite.getMinV(), specialSprite.getMaxV());
		quad.sprite(0, SPRITE_INDEX, MathHelper.approximatelyEquals(quad.spriteU(0, SPRITE_INDEX), minU) ? remappedMinU : remappedMaxU, MathHelper.approximatelyEquals(quad.spriteV(0, SPRITE_INDEX), minV) ? remappedMinV : remappedMaxV);
		quad.sprite(1, SPRITE_INDEX, MathHelper.approximatelyEquals(quad.spriteU(1, SPRITE_INDEX), minU) ? remappedMinU : remappedMaxU, MathHelper.approximatelyEquals(quad.spriteV(1, SPRITE_INDEX), minV) ? remappedMinV : remappedMaxV);
		quad.sprite(2, SPRITE_INDEX, MathHelper.approximatelyEquals(quad.spriteU(2, SPRITE_INDEX), minU) ? remappedMinU : remappedMaxU, MathHelper.approximatelyEquals(quad.spriteV(2, SPRITE_INDEX), minV) ? remappedMinV : remappedMaxV);
		quad.sprite(3, SPRITE_INDEX, MathHelper.approximatelyEquals(quad.spriteU(3, SPRITE_INDEX), minU) ? remappedMinU : remappedMaxU, MathHelper.approximatelyEquals(quad.spriteV(3, SPRITE_INDEX), minV) ? remappedMinV : remappedMaxV);
	}
	
	static float norm(float value, float low, float high) {
		float value2 = MathHelper.clamp(value, low, high);
		return (value2 - low) / (high - low);
	}
	
	//static float rangeRemap(float value, float low1, float high1, float low2, float high2) {
	//	float value2 = MathHelper.clamp(value, low1, high1);
	//	return low2 + (value2 - low1) * (high2 - low2) / (high1 - low1);
	//}
}
