package com.syntaxphoenix.spigot.realisticapi.data.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.syntaxphoenix.spigot.realisticapi.data.DataProcessor;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public abstract class EntityProcessor extends DataProcessor {

	@Override
	public abstract AEntity process(NbtCompound compound);
	
	public abstract AEntity process(EntityType entityType);
	
	public abstract AEntity process(Entity entity);

}
