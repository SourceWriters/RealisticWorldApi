package com.syntaxphoenix.spigot.realisticapi.generation;

import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public class RegionRestrictedWriter extends ARestrictedWriter {

	private final long minX, maxX;
	private final long minY, maxY;
	private final long minZ, maxZ;

	/*
	 * 
	 */

	public RegionRestrictedWriter(IDataWriter writer) {
		this(writer, 0, 0, 0);
	}

	public RegionRestrictedWriter(IDataWriter writer, long maxX, long maxY, long maxZ) {
		this(writer, 0, maxX, 0, maxY, 0, maxZ);
	}

	public RegionRestrictedWriter(IDataWriter writer, long minX, long maxX, long minY, long maxY, long minZ,
			long maxZ) {
		super(writer);
		this.minX = minX < maxX ? minX : maxX;
		this.maxX = minX < maxX ? maxX : minX;
		this.minY = minY < maxY ? minY : maxY;
		this.maxY = minY < maxY ? maxY : minY;
		this.minZ = minZ < maxZ ? minZ : maxZ;
		this.maxZ = minZ < maxZ ? maxZ : minZ;
	}

	/*
	 * 
	 */

	@Override
	public boolean canBeWritten(BlockLocation location, IWriteable writeable) {
		long x = location.getNormalizedX();
		if (maxX != 0)
			if (!(minX <= x && x <= maxX))
				return false;
		long y = location.getNormalizedY();
		if (maxY != 0)
			if (!(minY <= y && y <= maxY))
				return false;
		long z = location.getNormalizedZ();
		if (maxZ != 0)
			if (!(minZ <= z && z <= maxZ))
				return false;
		return true;
	}

}
