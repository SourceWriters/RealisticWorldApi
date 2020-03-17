package com.syntaxphoenix.spigot.realisticapi.generation;

public interface IWriteable {

	public boolean write(IDataWriter writer);

	public DataType getDataType();

}
