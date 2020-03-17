package com.syntaxphoenix.spigot.realisticapi.generation;

import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public abstract class ARestrictedWriter implements IDataWriter {
	
	private final IDataWriter writer;
	
	public ARestrictedWriter(IDataWriter writer) {
		this.writer = writer;
	}
	
	/*
	 * 
	 */
	
	@Override
	public boolean isSupported(DataType type) {
		return writer.isSupported(type);
	}
	
	@Override
	public boolean isSupported(IWriteable writeable) {
		return writer.isSupported(writeable);
	}
	
	@Override
	public boolean isSupported(Class<? extends IWriteable> clazz) {
		return writer.isSupported(clazz);
	}
	
	/*
	 * 
	 */

	@Override
	public void write(BlockLocation location, IWriteable writeable) {
		if(!writer.isSupported(writeable))
			return;
		writer.write(location, writeable);
	}
	
	/*
	 * 
	 */
	
	public abstract boolean canBeWritten(BlockLocation location, IWriteable writeable);
	
}
