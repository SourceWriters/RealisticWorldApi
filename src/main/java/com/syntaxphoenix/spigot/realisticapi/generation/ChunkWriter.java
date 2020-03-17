package com.syntaxphoenix.spigot.realisticapi.generation;

import org.bukkit.generator.ChunkGenerator.ChunkData;

import com.syntaxphoenix.spigot.realisticapi.data.block.ABlock;
import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public class ChunkWriter implements IDataWriter {

	private final ChunkData data;

	ChunkWriter(ChunkData data) {
		this.data = data;
	}

	@Override
	public boolean isSupported(DataType type) {
		return type.ordinal() == 0;
	}

	@Override
	public boolean isSupported(IWriteable writeable) {
		return isSupported(writeable.getDataType()) && isSupported(writeable.getClass());
	}

	@Override
	public boolean isSupported(Class<? extends IWriteable> clazz) {
		return ABlock.class.isAssignableFrom(clazz);
	}

	@Override
	public void write(BlockLocation location, IWriteable writeable) {
		if (!isSupported(writeable))
			return;
		((ABlock) writeable).setBlock(data, (int) location.getNormalizedX(), (int) location.getNormalizedY(),
				(int) location.getNormalizedZ());
	}

}
