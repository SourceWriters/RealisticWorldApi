package com.syntaxphoenix.spigot.realisticapi.generation;

import org.bukkit.generator.ChunkGenerator.ChunkData;

import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public class ChunkRestrictedWriter extends ARestrictedWriter {

	public ChunkRestrictedWriter(ChunkData data) {
		super(new ChunkWriter(data));
	}

	@Override
	public boolean canBeWritten(BlockLocation location, IWriteable writeable) {
		long x = location.getNormalizedX();
		if(x > 15 || x < 0)
			return false;
		long y = location.getNormalizedY();
		if(y > 255 || y < 0)
			return false;
		long z = location.getNormalizedZ();
		if(z > 15 || z < 0)
			return false;
		return true;
	}

}
