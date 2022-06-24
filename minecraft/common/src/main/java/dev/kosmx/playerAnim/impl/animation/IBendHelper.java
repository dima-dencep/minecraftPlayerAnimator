package dev.kosmx.playerAnim.impl.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import dev.kosmx.playerAnim.core.util.Pair;
import dev.kosmx.playerAnim.impl.Helper;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;

import javax.annotation.Nullable;

public interface IBendHelper {

    IBendHelper INSTANCE = Helper.isBendEnabled() ? new BendHelper() : new DummyBendable();
    static void rotateMatrixStack(PoseStack matrices, Pair<Float, Float> pair){
        float offset = 0.375f;
        matrices.translate(0, offset, 0);
        float bend = pair.getRight();
        float axisf = - pair.getLeft();
        Vector3f axis = new Vector3f((float) Math.cos(axisf), 0, (float) Math.sin(axisf));
        //return this.setRotation(axis.getRadialQuaternion(bend));
        matrices.mulPose(axis.rotation(bend));
        matrices.translate(0, - offset, 0);
    }

    void bend(ModelPart modelPart, float a, float b);

    void bend(ModelPart modelPart, @Nullable Pair<Float, Float> pair);

    void initBend(ModelPart modelPart, Direction direction);

    class DummyBendable implements IBendHelper {

        @Override
        public void bend(ModelPart modelPart, float a, float b) {

        }

        @Override
        public void bend(ModelPart modelPart, @org.jetbrains.annotations.Nullable Pair<Float, Float> pair) {

        }

        @Override
        public void initBend(ModelPart modelPart, Direction direction) {

        }
    }
}
