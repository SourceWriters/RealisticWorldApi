package com.syntaxphoenix.realisticapi.event.schematic;

import com.syntaxphoenix.realisticapi.event.RealisticCancelableEvent;
import com.syntaxphoenix.realisticapi.schematic.FiledSchematic;
import com.syntaxphoenix.realisticapi.schematic.Schematic;

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
