package com.syntaxphoenix.realisticapi.event.schematic;

import com.syntaxphoenix.realisticapi.schematic.Schematic;

public class SchematicLoadEvent extends SchematicEvent {

	public SchematicLoadEvent(Schematic schematic) {
		super(schematic);
	}

}
