package com.syntaxphoenix.spigot.realisticapi.schematic;

import static com.syntaxphoenix.spigot.realisticapi.data.RealisticProcessors.*;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.World;

import com.syntaxphoenix.spigot.realisticapi.data.block.BlockProcessor;
import com.syntaxphoenix.spigot.realisticapi.data.entity.EntityProcessor;
import com.syntaxphoenix.spigot.realisticapi.data.property.PropertiesProcessor;
import com.syntaxphoenix.spigot.realisticapi.utils.schematic.BlockCollector;
import com.syntaxphoenix.syntaxapi.logging.ILogger;
import com.syntaxphoenix.syntaxapi.utils.position.BlockLocation;

public abstract class SchematicBuilder {

	public static final SchematicBuilder DEFAULT = new Schematic.Builder();

	private final PropertiesProcessor property;
	private final EntityProcessor entity;
	private final BlockProcessor block;
	private final ILogger logger;

	public SchematicBuilder(PropertiesProcessor property, EntityProcessor entity, BlockProcessor block) {
		this(null, property, entity, block);
	}

	public SchematicBuilder(ILogger logger, PropertiesProcessor property, EntityProcessor entity,
			BlockProcessor block) {
		this.logger = logger;
		this.property = property == null ? PROPERTIES : property;
		this.entity = entity == null ? ENTITY : entity;
		this.block = block == null ? BLOCK : block;
	}

	/**
	 * Get the logger
	 * 
	 * @return current logger or null
	 */
	public ILogger getLogger() {
		return logger;
	}

	/**
	 * Get the property processor
	 * 
	 * @return current property processor or null
	 */
	public PropertiesProcessor getPropertyProcessor() {
		return property;
	}

	/**
	 * Get the block processor
	 * 
	 * @return current block processor or null
	 */
	public BlockProcessor getBlockProcessor() {
		return block;
	}

	/**
	 * Get the entity processor
	 * 
	 * @return current entity processor or null
	 */
	public EntityProcessor getEntityProcessor() {
		return entity;
	}

	/**
	 * Apply defaults
	 * 
	 * @param schematic to apply the settings to
	 * 
	 * @return schematic with settings applied
	 */
	private <E extends Schematic> E apply(E schematic) {
		schematic.setBlockProcessor(block);
		schematic.setEntityProcessor(entity);
		schematic.setPropertyProcessor(property);
		schematic.setLogger(logger);
		return schematic;
	}

	/**
	 * Fill with blocks
	 * 
	 * @param schematic to fill
	 * @param collector that collects blocks for the schematic
	 * 
	 * @return filled schematic
	 */
	private <E extends Schematic> E fill(E schematic, BlockCollector collector) {
		collector.fill(schematic);
		return schematic;
	}

	/**
	 * Load schematics from folder
	 * 
	 * @param folder to load schematics from
	 * 
	 * @return schematic array
	 */
	public NbtSchematic[] load(File folder) {
		if (folder == null || !folder.isDirectory())
			return new NbtSchematic[0];
		return Arrays.stream(folder.listFiles(file -> file.isFile() && !file.isHidden())).map(file -> loadPlain(file))
				.toArray(size -> new NbtSchematic[size]);
	}

	/**
	 * Load a plain schematic from a file
	 * 
	 * @param file to load
	 * 
	 * @return schematic or null
	 */
	public PlainSchematic loadPlain(File file) {
		if (file == null || !file.exists())
			return null;
		PlainSchematic schematic = new PlainSchematic(file);
		schematic.setName(extractName(file));
		return apply(schematic);
	}

	/**
	 * Load a nbt schematic from a file
	 * 
	 * @param file to load
	 * 
	 * @return schematic or null
	 */
	public NbtSchematic loadNbt(File file) {
		if (file == null || !file.exists())
			return null;
		NbtSchematic schematic = new NbtSchematic(file);
		schematic.setName(extractName(file));
		return apply(schematic);
	}

	/**
	 * Creates a schematic with defined settings
	 * 
	 * @param name of the schematic
	 * 
	 * @return created schematic
	 */
	public Schematic create(String name) {
		Schematic schematic = new Schematic();
		schematic.setName(name);
		return apply(schematic);
	}

	/**
	 * Creates a schematic with defined settings and fill it with blocks
	 * 
	 * @param name      of the schematic
	 * @param collector that collects blocks for the schematic
	 * 
	 * @return created schematic
	 */
	public Schematic create(String name, BlockCollector collector) {
		return fill(create(name), collector);
	}

	/**
	 * Creates a schematic with defined settings and fill it with blocks
	 * 
	 * @param name    of the schematic
	 * @param current location from where the schematic should relatively saved from
	 * @param points  points that define the schematic
	 * 
	 * @return created schematic
	 */
	public Schematic create(String name, Location current, Location[] points) {
		return create(name, new BlockCollector(current, points));
	}

	/**
	 * Creates a schematic with defined settings and fill it with blocks
	 * 
	 * @param name    of the schematic
	 * @param world   from where the blocks should be get from
	 * @param current location from where the schematic should relativly saved from
	 * @param points  points that define the schematic
	 * 
	 * @return created schematic
	 */
	public Schematic create(String name, World world, BlockLocation current, BlockLocation[] points) {
		return create(name, new BlockCollector(world, current, points));
	}

	/**
	 * Util to extract a schematic name from a file
	 * 
	 * @param file to extract the name from
	 * 
	 * @return extracted name
	 */
	public static String extractName(File file) {
		String name = file.getName();
		if (!name.contains("."))
			return name;
		String[] parts = name.split("\\.");
		return name.substring(name.length() - parts[parts.length - 1].length(), name.length());
	}

}
