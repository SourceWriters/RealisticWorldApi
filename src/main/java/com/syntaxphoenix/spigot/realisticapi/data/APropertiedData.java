package com.syntaxphoenix.spigot.realisticapi.data;

import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;

public abstract class APropertiedData extends AData {
	
	protected final AProperties<?> properties;
	
	public APropertiedData(AProperties<?> properties) {
		this.properties = properties;
	}
	
	public AProperties<?> getProperties() {
		return properties;
	}

}
