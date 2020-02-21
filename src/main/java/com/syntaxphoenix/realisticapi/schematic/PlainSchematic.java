package com.syntaxphoenix.realisticapi.schematic;

import java.io.File;
import java.io.IOException;

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
	 * Load the schematic's data from the file
	 */
	@Override
	public void load() {
		
		
		if (file.getName().endsWith("snbt")) {
			super.load();
		} else {
			YamlConfig config = new YamlConfig();
			try {
				config.load(file);
			} catch (IOException e) {
				if (getLogger() == null) {
					e.printStackTrace();
				} else {
					getLogger().log(e);
				}
			}
			
			/*
			 * TODO: Import old format from RWG
			 */
			
			if (!properties.containsProperty("name"))
				properties.addProperty(Property.create("name", file.getName().split("\\.")[0].replace(' ', '_')));
			
		}
	}

	@Override
	public void save() {
		file = new File(file.getPath(), file.getName().replace(file.getName().split("\\.")[1], ".rnbt"));
		super.save();
	}

}
