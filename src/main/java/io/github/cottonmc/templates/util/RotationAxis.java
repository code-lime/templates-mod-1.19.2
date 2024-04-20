package io.github.cottonmc.templates.util;

import net.minecraft.util.math.*;

@FunctionalInterface
public interface RotationAxis {
    RotationAxis NEGATIVE_X = f -> Quaternion.fromEulerXyz(-f, 0, 0);
    RotationAxis POSITIVE_X = f -> Quaternion.fromEulerXyz(f, 0, 0);
    RotationAxis NEGATIVE_Y = f -> Quaternion.fromEulerXyz(0, -f, 0);
    RotationAxis POSITIVE_Y = f -> Quaternion.fromEulerXyz(0, f, 0);
    RotationAxis NEGATIVE_Z = f -> Quaternion.fromEulerXyz(0, 0, -f);
    RotationAxis POSITIVE_Z = f -> Quaternion.fromEulerXyz(0, 0, f);

    static RotationAxis of(Vec3f vector3f) {
        return f -> MathMigrate.rotationAxis(Quaternion.IDENTITY, f, vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    Quaternion rotation(float var1);

    default Quaternion rotationDegrees(float f) {
        return this.rotation(f * ((float)Math.PI / 180));
    }
}

