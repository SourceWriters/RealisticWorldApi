package com.syntaxphoenix.spigot.realisticapi.event.schematic;

import com.syntaxphoenix.spigot.realisticapi.event.RealisticCancelableEvent;
import com.syntaxphoenix.spigot.realisticapi.schematic.FiledSchematic;
import com.syntaxphoenix.spigot.realisticapi.schematic.Schematic;

public abstract class SchematicEvent extends RealisticCancelableEvent {
	
	protected final Schematic schematic;
	
	public SchematicEvent(Schematic schematic) {
		this.schematic = schematic;
	}
	
	public Schematic getSchematic() {
		return schematic;
	}
	
	public boolean hasFile() {
		return schematic instanceof FiledSchematic;
	}

}
