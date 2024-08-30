package xyz.gaminered.rendertest.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.gaminered.rendertest.Rendertest;
import xyz.gaminered.rendertest.util.SelectionBox;


@Environment(EnvType.CLIENT)
public class RendertestClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(Rendertest.MOD_NAME);
	public static SelectionBox box = new SelectionBox(BlockPos.ORIGIN, BlockPos.ORIGIN);

	@Override
	public void onInitializeClient() {
		WorldRenderEvents.BEFORE_DEBUG_RENDER.register(context -> {
			MatrixStack matrices = context.matrixStack();
			VertexConsumerProvider vertexConsumers = context.consumers();
			Vec3d negativeCameraPos = context.camera().getPos().negate();
			Box box = RendertestClient.box.toBox().offset(negativeCameraPos);
			WorldRenderer.drawBox(
					matrices, context.consumers().getBuffer(RenderLayer.getLines()),
					box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ,
					0.4F, 0.9F, 0.4F, 1F
			);
//			DebugRenderer.drawBox(
//					matrices, vertexConsumers,
//					box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ,
//					1F, 1F, 1F, 0.5F
//			);
//			DebugRenderer.drawBox(
//					matrices, vertexConsumers,
//					box.maxX, box.maxY, box.maxZ, box.minX, box.minY, box.minZ,
//					0.5F, 0.5F, 0.5F, 0.7F
//			);
			WorldRenderer.drawBox(
					matrices, context.consumers().getBuffer(RenderLayer.getLines()),
					new Box(RendertestClient.box.getFirstPos()).offset(negativeCameraPos),
					1F, 0F, 0F, 1F
			);
			WorldRenderer.drawBox(
					matrices, context.consumers().getBuffer(RenderLayer.getLines()),
					new Box(RendertestClient.box.getSecondPos()).offset(negativeCameraPos),
					0F, 0F, 1F, 1F
			);
		});
	}
}
