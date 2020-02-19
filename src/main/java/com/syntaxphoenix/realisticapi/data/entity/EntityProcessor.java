package com.syntaxphoenix.realisticapi.data.entity;

import org.bukkit.entity.Entity;

import com.syntaxphoenix.realisticapi.data.DataProcessor;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public abstract class EntityProcessor extends DataProcessor {

	@Override
	public abstract RealEntity process(NbtCompound compound);
	
	public abstract RealEntity process(Entity entity);

}
