package com.syntaxphoenix.spigot.realisticapi.data.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticProperties;
import com.syntaxphoenix.spigot.realisticapi.generation.IDataWriter;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public class RealisticEntity extends AEntity {

	protected EntityType type;

	public RealisticEntity() {
		this(EntityType.PIG);
	}

	protected RealisticEntity(EntityType type) {
		this(new RealisticProperties(), type);
	}

	protected RealisticEntity(AProperties<?> properties, EntityType type) {
		super(properties);
		this.type = type;
	}

	/*
	 * Coming soon
	 */

	@Override
	public NbtCompound asNbt() {
		NbtCompound compound = new NbtCompound();

		compound.set("properties", properties.asNbt());
		compound.set("type", type.name());

		return compound;
	}

	@Override
	public void spawn(Location location) {
		location.getWorld().spawnEntity(location, type);
	}

	@Override
	public boolean write(IDataWriter writer) {
		if (!writer.isSupported(this))
			return false;
		writer.write(new BlockLocation(0, 0, 0), this);
		return true;
	}

}
