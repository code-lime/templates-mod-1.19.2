package io.github.cottonmc.templates.util;

import net.minecraft.client.util.math.Vector3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;

public class MathMigrate {
    public static final double PI = java.lang.Math.PI;
    static final double PI2 = PI * 2.0;
    static final float PI_f = (float) java.lang.Math.PI;
    static final float PI2_f = PI_f * 2.0f;
    static final double PIHalf = PI * 0.5;
    static final float PIHalf_f = (float) (PI * 0.5);
    static final double PI_4 = PI * 0.25;
    static final double PI_INV = 1.0 / PI;

    public static Quaternion rotationAxis(Quaternion quaternion, float angle, float axisX, float axisY, float axisZ) {
        float hangle = angle / 2.0f;
        float sinAngle = MathHelper.sin(hangle);
        float invVLength = 1 / MathHelper.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);
        quaternion.set(axisX * invVLength * sinAngle,
                axisY * invVLength * sinAngle,
                axisZ * invVLength * sinAngle,
                cosFromSin(sinAngle, hangle));
        return quaternion;
    }
    public static float cosFromSin(float sin, float angle) {
        return cosFromSinInternal(sin, angle);
    }
    private static float cosFromSinInternal(float sin, float angle) {
        // sin(x)^2 + cos(x)^2 = 1
        float cos = MathHelper.sqrt(1.0f - sin * sin);
        float a = angle + PIHalf_f;
        float b = a - (int)(a / PI2_f) * PI2_f;
        if (b < 0.0)
            b = PI2_f + b;
        if (b >= PI_f)
            return -cos;
        return cos;
    }

    public static Vector3d vector3d(double value) {
        return new Vector3d(value, value, value);
    }
    public static Vector3d vector3d(Vector3d value) {
        return new Vector3d(value.x, value.y, value.z);
    }
}
