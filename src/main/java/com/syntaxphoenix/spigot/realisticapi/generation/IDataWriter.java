package com.syntaxphoenix.spigot.realisticapi.generation;

import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public interface IDataWriter {

	public boolean isSupported(DataType type);

	public boolean isSupported(IWriteable writeable);

	public boolean isSupported(Class<? extends IWriteable> clazz);

	public void write(BlockLocation location, IWriteable writeable);

}
