package com.syntaxphoenix.spigot.realisticapi.schematic;

import java.io.File;
import java.io.IOException;

import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.utils.CatchedException;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtNamedTag;
import com.syntaxphoenix.syntaxapi.nbt.NbtType;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtDeserializer;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtSerializer;

public class NbtSchematic extends FiledSchematic {
	
	/**
	 * Constructs a loadable nbt file schematic
	 * 
	 * @param file - schematic file
	 */
	public NbtSchematic(File file) {
		super(file);
	}
	
	/**
	 * Constructs a loadable nbt file schematic
	 * 
	 * @param file - schematic file
	 * @param properties - schematic propertytype
	 */
	public NbtSchematic(AProperties<?> properties, File file) {
		super(properties, file);
	}

	/**
	 * Load the schematic's data from the file
	 */
	public void load() throws RuntimeException {
		NbtNamedTag tag = null;
		try {
			tag = NbtDeserializer.COMPRESSED.fromFile(file);
		} catch (IOException e) {
			throw new CatchedException(e);
		}
		if (tag == null) {
			return;
		}
		if (tag.getTag().getType() == NbtType.COMPOUND) {
			fromNbt((NbtCompound) tag.getTag());
		}
	}

	/**
	 * Save the schematic's data to the file
	 */
	public void save() throws RuntimeException {
		NbtNamedTag tag = new NbtNamedTag("root", asNbt());
		try {
			NbtSerializer.COMPRESSED.toFile(tag, file);
		} catch (IOException e) {
			if (getLogger() == null) {
				e.printStackTrace();
			} else {
				getLogger().log(e);
			}
		}
	}

}
