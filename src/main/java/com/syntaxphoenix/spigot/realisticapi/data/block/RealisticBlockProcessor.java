package com.syntaxphoenix.spigot.realisticapi.data.block;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import com.syntaxphoenix.spigot.realisticapi.data.RealisticProcessors;
import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticProperties;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;

public class RealisticBlockProcessor extends BlockProcessor {

	/*
	 * Coming soon
	 */

	@Override
	public RealisticBlock process(NbtCompound compound) {
		if (!compound.hasKey("properties") || !compound.hasKey("data"))
			return null;
		RealisticProperties properties = RealisticProcessors.PROPERTIES.process(compound.getCompound("properties"));
		BlockData data;
		try {
			data = Bukkit.createBlockData(compound.getString("data"));
		} catch (IllegalArgumentException iae) {
			return null;
		}
		return new RealisticBlock(properties, data);
	}

	@Override
	public RealisticBlock process(BlockData blockData) {
		return new RealisticBlock(blockData);
	}

	@Override
	public RealisticBlock process(Material material) {
		return process(Bukkit.createBlockData(material));
	}

	@Override
	public RealisticBlock process(String blockData) {
		return process(Bukkit.createBlockData(blockData));
	}

	@Override
	public RealisticBlock process(Block block) {
		return process(block.getBlockData());
	}

}
