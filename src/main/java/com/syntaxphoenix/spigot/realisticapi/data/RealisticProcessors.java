package com.syntaxphoenix.spigot.realisticapi.data;

import com.syntaxphoenix.spigot.realisticapi.data.block.RealisticBlockProcessor;
import com.syntaxphoenix.spigot.realisticapi.data.entity.RealisticEntityProcessor;
import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticPropertyProcessor;
import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticPropertiesProcessor;

public final class RealisticProcessors {

	public static final RealisticPropertyProcessor PROPERTY = new RealisticPropertyProcessor();
	public static final RealisticPropertiesProcessor PROPERTIES = new RealisticPropertiesProcessor();
	
	public static final RealisticBlockProcessor BLOCK = new RealisticBlockProcessor();
	public static final RealisticEntityProcessor ENTITY = new RealisticEntityProcessor();

}
