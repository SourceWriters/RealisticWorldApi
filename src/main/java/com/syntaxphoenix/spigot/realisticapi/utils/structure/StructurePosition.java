package com.syntaxphoenix.spigot.realisticapi.utils.structure;

public enum StructurePosition {
	
	OVERWORLD(0), SKY(1), UNDERGROUND(2) , UNDERWATER(3), CUSTOM(99);
	
	private final int id;
	
	private StructurePosition(int id) {
		this.id = id;
	}
	
	public final int id() {
		return id;
	}
	
}
