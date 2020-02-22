package com.syntaxphoenix.spigot.realisticapi.schematic;

import java.io.File;

public abstract class FiledSchematic extends Schematic {
	
	protected File file;
	
	public FiledSchematic(File file) {
		this.file = file;
	}
	
	public abstract void load();
	
	public abstract void save();

}
