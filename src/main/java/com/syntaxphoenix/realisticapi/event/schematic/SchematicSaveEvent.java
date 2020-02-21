package com.syntaxphoenix.realisticapi.event.schematic;

import com.syntaxphoenix.realisticapi.schematic.Schematic;

public class SchematicSaveEvent extends SchematicEvent {

	public SchematicSaveEvent(Schematic schematic) {
		super(schematic);
	}

}
