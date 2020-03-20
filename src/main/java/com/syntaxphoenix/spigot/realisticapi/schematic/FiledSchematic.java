package com.syntaxphoenix.spigot.realisticapi.schematic;

import java.io.File;

import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;

public abstract class FiledSchematic extends Schematic {
	
	protected File file;
	
	public FiledSchematic(File file) {
		super();
		this.file = file;
	}
	
	public FiledSchematic(AProperties<?> properties, File file) {
		super(properties);
		this.file = file;
	}
	
	public abstract void load() throws RuntimeException;
	
	public abstract void save() throws RuntimeException;
	
}
