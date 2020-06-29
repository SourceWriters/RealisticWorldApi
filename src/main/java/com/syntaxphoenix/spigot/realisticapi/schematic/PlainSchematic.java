package com.syntaxphoenix.spigot.realisticapi.schematic;

import static com.syntaxphoenix.spigot.realisticapi.utils.schematic.PlainSerailizer.parseLocation;

import java.io.File;
import java.io.IOException;

import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.utils.structure.StructurePosition;
import com.syntaxphoenix.syntaxapi.config.BaseSection;
import com.syntaxphoenix.syntaxapi.config.yaml.YamlConfig;
import com.syntaxphoenix.syntaxapi.logging.LogTypeId;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class PlainSchematic extends NbtSchematic {

	/**
	 * Constructs a loadable nbt schematic that supports to load old RWG schematic
	 * format
	 * 
	 * @param file - schematic file
	 */
	public PlainSchematic(File file) {
		super(file);
	}

	/**
	 * Constructs a loadable nbt schematic that supports to load old RWG schematic
	 * format
	 * 
	 * @param file       - schematic file
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
				if (logger != null)
					logger.log(LogTypeId.WARNING,
							"Wasn't able to load schematic as plain from file '" + file.getName() + "'!",
							"Falling back to nbt schematic...");
				super.load();
				return;
			}

			/*
			 * TODO: Import old format from RWG
			 */

			if (blockProcessor != null) {
				BaseSection section = config.getSection("blocks");
				if (section != null)
					for (String location : section.getKeys())
						blockGrid.set(parseLocation(location),
								blockProcessor.process(section.get(location, String.class)));
			}

			if (entityProcessor != null) {
				BaseSection section = config.getSection("mobs");
				if (section != null)
					for (String location : section.getKeys())
						entityGrid.set(parseLocation(location),
								entityProcessor.process(section.get(location, String.class)));
			}

			if (config.contains("schematic")) {
				BaseSection schematic = config.getSection("schematic");

				if (schematic.contains("positioning")) {
					BaseSection position = schematic.getSection("positioning");
					
					if (position.contains("position"))
						properties.setProperty(Property.create("position",
								StructurePosition.valueOf(position.get("position", String.class))));
					
					int[] range = new int[] {0, 256};
					if(position.contains("minY"))
						range[0] = position.get("minY", Number.class).intValue();
					if(position.contains("maxY"))
						range[1] = position.get("maxY", Number.class).intValue();
					properties.setProperty(Property.create("positionRange", range));
					
				}
				
				if(schematic.contains("basement") && blockProcessor != null)
					properties.setProperty(Property.create("basement", blockProcessor.process(schematic.get("basement", String.class))));

			}

			/*
			 * Ensure that schematic got a name
			 */

			properties.addProperty(Property.create("name", file.getName().split("\\.")[0].replace(' ', '_')));

		}
	}

	@Override
	public void save() throws RuntimeException {
		file = new File(file.getPath(), file.getName().replace(file.getName().split("\\.")[1], ".snbts"));
		super.save();
	}

}
