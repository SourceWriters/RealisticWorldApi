package com.syntaxphoenix.spigot.realisticapi.schematic;

import java.io.File;
import java.io.IOException;

import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.utils.CatchedException;
import com.syntaxphoenix.syntaxapi.config.yaml.YamlConfig;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class PlainSchematic extends NbtSchematic {

	
	/**
	 * Constructs a loadable nbt schematic that supports to load old RWG schematic format
	 * 
	 * @param file - schematic file
	 */
	public PlainSchematic(File file) {
		super(file);
	}
	
	/**
	 * Constructs a loadable nbt schematic that supports to load old RWG schematic format
	 * 
	 * @param file - schematic file
	 * @param properties - schematic propertytype
	 */
	public PlainSchematic(AProperties<?> properties, File file) {
		super(properties, file);
	}

	/**
	 * Load the schematic's data from the file
	 */
	@Override
	public void load() throws RuntimeException {
		// Load all snbts (Syntax Named Binary Tag Schematic) files with NbtSchematic
		if (file.getName().endsWith("snbts")) {
			super.load();
		} else {
			YamlConfig config = new YamlConfig();
			try {
				config.load(file);
			} catch (IOException e) {
				throw new CatchedException(e);
			}
			
			/*
			 * TODO: Import old format from RWG
			 */
			
			if (!properties.containsProperty("name"))
				properties.addProperty(Property.create("name", file.getName().split("\\.")[0].replace(' ', '_')));
			
		}
	}

	@Override
	public void save() throws RuntimeException {
		file = new File(file.getPath(), file.getName().replace(file.getName().split("\\.")[1], ".rnbt"));
		super.save();
	}

}
