package com.syntaxphoenix.spigot.realisticapi.generation;

import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public class TypeRestrictedWriter extends ARestrictedWriter {
	
	private final DataType type;
	
	public TypeRestrictedWriter(IDataWriter writer, DataType type) {
		super(writer);
		this.type = type;
	}

	@Override
	public boolean canBeWritten(BlockLocation location, IWriteable writeable) {
		return writeable.getDataType() == type;
	}

}
