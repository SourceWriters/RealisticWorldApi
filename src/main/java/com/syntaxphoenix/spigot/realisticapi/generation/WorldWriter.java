package com.syntaxphoenix.spigot.realisticapi.generation;

import org.bukkit.World;

import com.syntaxphoenix.spigot.realisticapi.data.block.ABlock;
import com.syntaxphoenix.spigot.realisticapi.data.entity.AEntity;
import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public class WorldWriter implements IDataWriter {
	
	private final World world;
	
	public WorldWriter(World world) {
		this.world = world;
	}

	@Override
	public boolean isSupported(DataType type) {
		if (type.ordinal() <= 1)
			return true;
		return false;
	}

	@Override
	public boolean isSupported(IWriteable writeable) {
		return isSupported(writeable.getDataType()) && isSupported(writeable.getClass());
	}

	@Override
	public boolean isSupported(Class<? extends IWriteable> clazz) {
		return ABlock.class.isAssignableFrom(clazz) || AEntity.class.isAssignableFrom(clazz);
	}

	@Override
	public void write(BlockLocation location, IWriteable writeable) {
		if(!isSupported(writeable))
			return;
		int type = writeable.getDataType().ordinal();
		if(type == 0) {
			((ABlock) writeable).setBlock(world, (int) location.getNormalizedX(), (int) location.getNormalizedY(), (int) location.getNormalizedZ());
		} else if(type == 1) {
			((AEntity) writeable).spawn(world, (int) location.getNormalizedX(), (int) location.getNormalizedY(), (int) location.getNormalizedZ());
		}
	}


}
