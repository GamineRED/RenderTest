package xyz.gaminered.rendertest.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class SelectionBox {
	private BlockPos firstPos;
	private BlockPos secondPos;

	public SelectionBox(BlockPos firstPos, BlockPos secondPos) {
		this.firstPos = firstPos;
		this.secondPos = secondPos;
	}

	public void setFirstPos(BlockPos pos) {
		this.firstPos = pos;
	}
	public BlockPos getFirstPos() {
		return firstPos;
	}
	public BlockPos getFirstOuterPos() {
		return this.firstPos.add(
				this.firstPos.getX() <= this.secondPos.getX() ? -1 : 0,
				this.firstPos.getY() <= this.secondPos.getY() ? -1 : 0,
				this.firstPos.getZ() <= this.secondPos.getZ() ? -1 : 0
		);
	}

	public void setSecondPos(BlockPos pos) {
		this.secondPos = pos;
	}
	public BlockPos getSecondPos() {
		return secondPos;
	}
	public BlockPos getSecondOuterPos() {
		return this.secondPos.add(
				this.firstPos.getX() <= this.secondPos.getX() ? 0 : -1,
				this.firstPos.getY() <= this.secondPos.getY() ? 0 : -1,
				this.firstPos.getZ() <= this.secondPos.getZ() ? 0 : -1
		);
	}

	public Box toBox() {
		return new Box(this.getFirstOuterPos().toCenterPos().add(0.5D, 0.5D, 0.5D), this.getSecondOuterPos().toCenterPos().add(0.5D, 0.5D, 0.5D));
	}
}
