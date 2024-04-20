package io.github.cottonmc.templates.model;

import io.github.cottonmc.templates.api.TemplatesClientApi;
import io.github.cottonmc.templates.util.RotationAxis;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix4f;

public class SlopeBaseMesh {
	public static final int SPRITE_INDEX = QuadUvBounds.SPRITE_INDEX;

	/**
	 * @see RetexturingBakedModel for why these values were chosen
	 */
	public static final int TAG_SLOPE = Direction.UP.ordinal() + 1;
	public static final int TAG_LEFT = Direction.EAST.ordinal() + 1;
	public static final int TAG_RIGHT = Direction.WEST.ordinal() + 1;
	public static final int TAG_BACK = Direction.SOUTH.ordinal() + 1;
	public static final int TAG_BOTTOM = Direction.DOWN.ordinal() + 1;
	
	public static Mesh makeUpright() {
		Renderer renderer = TemplatesClientApi.getInstance().getFabricRenderer();
		MeshBuilder builder = renderer.meshBuilder();
		QuadEmitter qu = builder.getEmitter();
		qu.tag(TAG_SLOPE)
			.pos(0, 0f, 0f, 0f).pos(1, 0f, 1f, 1f).pos(2, 1f, 1f, 1f).pos(3, 1f, 0f, 0f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.sprite(0, SPRITE_INDEX, 0f, 0f).sprite(1, SPRITE_INDEX, 0f, 1f).sprite(2, SPRITE_INDEX, 1f, 1f).sprite(3, SPRITE_INDEX, 1f, 0f)
			.emit()
			.tag(TAG_LEFT)
			.pos(0, 1f, 0f, 0f).pos(1, 1f, 0.5f, 0.5f).pos(2, 1f, 1f, 1f).pos(3, 1f, 0f, 1f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.sprite(0, SPRITE_INDEX, 1f, 1f).sprite(1, SPRITE_INDEX, 0.5f, 0.5f).sprite(2, SPRITE_INDEX, 0f, 0f).sprite(3, SPRITE_INDEX, 0f, 1f)
			.cullFace(Direction.EAST)
			.emit()
			.tag(TAG_RIGHT)
			.pos(0, 0f, 0.5f, 0.5f).pos(1, 0, 0f, 0f).pos(2, 0f, 0f, 1f).pos(3, 0f, 1f, 1f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.sprite(0, SPRITE_INDEX, 0.5f, 0.5f).sprite(1, SPRITE_INDEX, 0f, 1f).sprite(2, SPRITE_INDEX, 1f, 1f).sprite(3, SPRITE_INDEX, 1f, 0f)
			.cullFace(Direction.WEST)
			.emit()
			.tag(TAG_BACK)
			.square(Direction.SOUTH, 0, 0, 1, 1, 0) //sets pos & cullFace
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.spriteUnitSquare(SPRITE_INDEX)
			.emit()
			.tag(TAG_BOTTOM)
			.square(Direction.DOWN, 0, 0, 1, 1, 0) //sets pos & cullFace
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.spriteUnitSquare(SPRITE_INDEX)
			.emit();
		return builder.build();
	}
	
	//My mfw (my face when) mfw face when you can't rotate blockmodels on the z axis from a blockstate file
	//Fine i will do it myself !!!
	public static Mesh makeSide() {
		Matrix4f mat = new Matrix4f(RotationAxis.POSITIVE_Z.rotationDegrees(90));
		return MeshTransformUtil.pretransformMesh(makeUpright(), MeshTransformUtil.applyMatrix(mat));
	}
	
	//looks weird since i wrote a janky script to massage a .bbmodel, some manual fixups applied
	public static Mesh makeTinyUpright() {
		Renderer renderer = TemplatesClientApi.getInstance().getFabricRenderer();
		MeshBuilder builder = renderer.meshBuilder();
		QuadEmitter qu = builder.getEmitter();
		qu.tag(TAG_LEFT)
			.pos(0, 1f, 0.25f, 0.75f).sprite(0, SPRITE_INDEX, 0.25f, 0.75f)
			.pos(1, 1f, 0.5f, 1f).sprite(1, SPRITE_INDEX, 0f, 0.5f)
			.pos(2, 1f, 0f, 1f).sprite(2, SPRITE_INDEX, 0f, 1f)
			.pos(3, 1f, 0f, 0.5f).sprite(3, SPRITE_INDEX, 0.5f, 1f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.emit()
			.tag(TAG_RIGHT)
			.pos(0, 0f, 0f, 1f).sprite(0, SPRITE_INDEX, 1f, 1f)
			.pos(1, 0f, 0.5f, 1f).sprite(1, SPRITE_INDEX, 1f, 0.5f)
			.pos(2, 0f, 0.25f, 0.75f).sprite(2, SPRITE_INDEX, 0.75f, 0.75f)
			.pos(3, 0f, 0f, 0.5f).sprite(3, SPRITE_INDEX, 0.5f, 1f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.emit()
			.tag(TAG_BOTTOM)
			.pos(0, 1f, 0f, 0.5f).sprite(0, SPRITE_INDEX, 1f, 0.5f)
			.pos(1, 1f, 0f, 1f).sprite(1, SPRITE_INDEX, 1f, 0f)
			.pos(2, 0f, 0f, 1f).sprite(2, SPRITE_INDEX, 0f, 0f)
			.pos(3, 0f, 0f, 0.5f).sprite(3, SPRITE_INDEX, 0f, 0.5f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.emit()
			.tag(TAG_BACK)
			.pos(0, 1f, 0f, 1f).sprite(0, SPRITE_INDEX, 1f, 1f)
			.pos(1, 1f, 0.5f, 1f).sprite(1, SPRITE_INDEX, 1f, 0.5f)
			.pos(2, 0f, 0.5f, 1f).sprite(2, SPRITE_INDEX, 0f, 0.5f)
			.pos(3, 0f, 0f, 1f).sprite(3, SPRITE_INDEX, 0f, 1f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.emit()
			.tag(TAG_SLOPE)
			.pos(0, 1f, 0.5f, 1f).sprite(2, SPRITE_INDEX, 0f, 0.5f) //manually permuted uvs
			.pos(1, 1f, 0f, 0.5f).sprite(3, SPRITE_INDEX, 0f, 1f)
			.pos(2, 0f, 0f, 0.5f).sprite(0, SPRITE_INDEX, 1f, 1f)
			.pos(3, 0f, 0.5f, 1f).sprite(1, SPRITE_INDEX, 1f, 0.5f)
			.spriteColor(SPRITE_INDEX, -1, -1, -1, -1)
			.emit()
		;
		return builder.build();
	}
	
	public static Mesh makeTinySide() {
		Matrix4f mat = new Matrix4f(RotationAxis.POSITIVE_Z.rotationDegrees(90));
		return MeshTransformUtil.pretransformMesh(makeTinyUpright(), MeshTransformUtil.applyMatrix(mat));
	}
}
