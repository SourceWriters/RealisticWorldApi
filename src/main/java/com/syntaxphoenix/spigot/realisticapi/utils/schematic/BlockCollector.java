package com.syntaxphoenix.spigot.realisticapi.utils.schematic;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.syntaxphoenix.spigot.realisticapi.data.block.ABlock;
import com.syntaxphoenix.spigot.realisticapi.data.block.BlockProcessor;
import com.syntaxphoenix.spigot.realisticapi.schematic.Schematic;
import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridMap;

public class BlockCollector {

	public static final BlockPicker DEFAULT_PICKER = (world, x, y, z) -> world.getBlockAt(x, y, z);

	private final World world;
	private final BlockLocation current;
	private final BlockLocation[] points;

	private BlockPicker picker = DEFAULT_PICKER;

	public BlockCollector(World world, BlockLocation current, BlockLocation... points) {
		this.world = world;
		this.points = points;
		this.current = current;
	}

	public BlockCollector(Location current, Location... points) {
		this.world = current.getWorld();
		this.current = convert(current);
		this.points = convert(points);
	}
	
	/*
	 * BlockPicker Setter / Getter
	 */
	public BlockPicker getBlockPicker() {
		return picker;
	}
	
	public BlockCollector setBlockPicker(BlockPicker picker) {
		this.picker = picker;
		return this;
	}

	/*
	 * Getter
	 */

	public World getWorld() {
		return world;
	}

	public BlockLocation getLocation() {
		return current;
	}

	public BlockLocation[] getPoints() {
		return points;
	}

	/*
	 * Collectors
	 */

	public BlockCollector fill(Schematic schematic) {
		return fillSquared(schematic, 0, 1);
	}

	public BlockCollector fillSquared(Schematic schematic, int start, int end) {
		BlockLocation loc1 = points[start], loc2 = points[end];
		int x1 = integer(loc1.getNormalizedX()), x2 = integer(loc2.getNormalizedX()),
				cx = integer(current.getNormalizedX());
		int y1 = integer(loc1.getNormalizedY()), y2 = integer(loc2.getNormalizedY()),
				cy = integer(current.getNormalizedY());
		int z1 = integer(loc1.getNormalizedZ()), z2 = integer(loc2.getNormalizedZ()),
				cz = integer(current.getNormalizedZ());

		int buffer = 0;
		if (x1 > x2) {
			buffer = x2;
			x2 = x1;
			x2 = buffer;
		}
		if (y1 > y2) {
			buffer = y2;
			y2 = y1;
			y2 = buffer;
		}
		if (z1 > z2) {
			buffer = z2;
			z2 = z1;
			z2 = buffer;
		}

		BlockProcessor processor = schematic.getBlockProcessor();
		GridMap<ABlock> blocks = schematic.getBlockGrid();

		for (int x = x1; x <= x2; x++)
			for (int y = y1; y <= y2; y++)
				for (int z = z1; z <= z2; z++)
					blocks.set(y - cy, x - cx, z - cz, processor.process(picker.pick(world, x, y, z)));
		return this;
	}

	/*
	 * Function Adapter
	 */

	@FunctionalInterface
	public static interface BlockPicker {
		public Block pick(World world, int x, int y, int z);
	}

	/*
	 * Utils
	 */

	/**
	 * Util to get int from long safely
	 * 
	 * @param number to convert
	 * 
	 * @return converted integer
	 */
	public static int integer(Long number) {
		if (number > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (number < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return number.intValue();
	}

	/**
	 * Util to convert org.bukkit.Location to
	 * com.syntaxphoenix.syntaxapi.utils.position.BlockLocation
	 * 
	 * @param location the location to convert from
	 * 
	 * @return the converted location
	 */
	public static BlockLocation convert(Location location) {
		return new BlockLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	/**
	 * Util to convert org.bukkit.Location[] to
	 * com.syntaxphoenix.syntaxapi.utils.position.BlockLocation[]
	 * 
	 * @param locations the location array to convert from
	 * 
	 * @return the converted location array
	 */
	public static BlockLocation[] convert(Location[] locations) {
		BlockLocation[] output = new BlockLocation[locations.length];
		if (output.length == 0)
			return output;
		for (int index = 0; index < output.length; index++)
			output[index] = convert(locations[0]);
		return output;
	}

}
