package com.syntaxphoenix.realisticapi.schematic;

import java.io.File;
import java.io.IOException;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtNamedTag;
import com.syntaxphoenix.syntaxapi.nbt.NbtType;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtDeserializer;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtSerializer;

public class NbtSchematic extends Schematic {

	private final File file;
	public NbtSchematic(File file) {
		this.file = file;
	}

	public void load() {
		NbtNamedTag tag = null;
		try {
			tag = NbtDeserializer.COMPRESSED.fromFile(file);
		} catch (IOException e) {
			if (getLogger() == null) {
				e.printStackTrace();
			} else {
				getLogger().log(e);
			}
		}
		if (tag == null) {
			return;
		}
		if (tag.getTag().getType() == NbtType.COMPOUND) {
			fromNbt((NbtCompound) tag.getTag());
		}
	}

	public void save() {
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
