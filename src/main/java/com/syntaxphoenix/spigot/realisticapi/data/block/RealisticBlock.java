package com.syntaxphoenix.spigot.realisticapi.data.block;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticProperties;
import com.syntaxphoenix.spigot.realisticapi.generation.IDataWriter;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public class RealisticBlock extends ABlock {

	protected BlockData data;

	public RealisticBlock() {
		this(Bukkit.createBlockData("minecraft:stone[]"));
	}

	protected RealisticBlock(BlockData data) {
		this(new RealisticProperties(), data);
	}

	protected RealisticBlock(AProperties<?> properties, BlockData data) {
		super(properties);
		this.data = data;
	}

	@Override
	public NbtCompound asNbt() {
		NbtCompound compound = new NbtCompound();

		compound.set("properties", properties.asNbt());
		compound.set("data", data.getAsString());

		return compound;
	}

	@Override
	public BlockData asBlockData() {
		return data;
	}

	@Override
	public void setBlock(Location location) {
		location.getBlock().setBlockData(data);
	}

	@Override
	public void setBlock(ChunkData data, int x, int y, int z) {
		data.setBlock(x, y, z, this.data);
	}

	@Override
	public boolean write(IDataWriter writer) {
		if (!writer.isSupported(this))
			return false;
		writer.write(new BlockLocation(0, 0, 0), this);
		return true;
	}

}
