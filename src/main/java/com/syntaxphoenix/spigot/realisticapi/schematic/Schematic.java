package com.syntaxphoenix.spigot.realisticapi.schematic;

import java.util.HashMap;
import java.util.Map.Entry;

import com.syntaxphoenix.spigot.realisticapi.data.block.BlockProcessor;
import com.syntaxphoenix.spigot.realisticapi.data.block.ABlock;
import com.syntaxphoenix.spigot.realisticapi.data.entity.EntityProcessor;
import com.syntaxphoenix.spigot.realisticapi.data.entity.AEntity;
import com.syntaxphoenix.spigot.realisticapi.data.property.RealisticProperties;
import com.syntaxphoenix.spigot.realisticapi.data.property.AProperties;
import com.syntaxphoenix.spigot.realisticapi.data.property.AProperty;
import com.syntaxphoenix.spigot.realisticapi.data.property.PropertiesProcessor;
import com.syntaxphoenix.spigot.realisticapi.generation.IWriteable;
import com.syntaxphoenix.spigot.realisticapi.utils.exception.InvalidSchematicException;
import com.syntaxphoenix.syntaxapi.logging.ILogger;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.utils.NbtStorage;
import com.syntaxphoenix.syntaxapi.utils.data.Property;
import com.syntaxphoenix.syntaxapi.utils.java.Strings;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridLayer;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridMap;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridRow;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridType;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridValue;

public class Schematic implements NbtStorage<NbtCompound> {
	
	protected final GridMap<ABlock> blockGrid = new GridMap<>(GridType.GRID_3D);
	protected final GridMap<AEntity> entityGrid = new GridMap<>(GridType.GRID_3D);
	protected AProperties<?> properties;

	protected PropertiesProcessor propertiesProcessor;
	protected EntityProcessor entityProcessor;
	protected BlockProcessor blockProcessor;
	protected ILogger logger;

	public Schematic() {
		this.properties = new RealisticProperties();
	}

	public Schematic(AProperties<?> properties) {
		this.properties = properties;
	}

	/**
	 * Get the properties
	 * 
	 * @return saved properties
	 */
	public final AProperties<?> getProperties() {
		return properties;
	}

	/**
	 * Get the schematic's name
	 * 
	 * @return empty string or in properties set name
	 */
	public final String getName() {
		AProperty<String> name = properties.findProperty("name").parseString();
		return name == null ? "" : name.getValue();
	}

	/**
	 * Set the schematic's name
	 * 
	 * @param name - new schematic name
	 */
	public final void setName(String name) {
		properties.setProperty(Property.create("name", name));
	}

	/**
	 * Get the block GridMap
	 * 
	 * @return GridMap that contains all saved blocks
	 */
	public final GridMap<ABlock> getBlockGrid() {
		return blockGrid;
	}

	/**
	 * Get the entity GridMap
	 * 
	 * @return GridMap that contains all saved entities
	 */
	public final GridMap<AEntity> getEntityGrid() {
		return entityGrid;
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
	 * Sets the logger
	 * 
	 * @param logger - logger to set
	 * 
	 * @return this schematic
	 */
	public Schematic setLogger(ILogger logger) {
		this.logger = logger;
		return this;
	}

	/**
	 * Get the property processor
	 * 
	 * @return current property processor or null
	 */
	public PropertiesProcessor getPropertyProcessor() {
		return propertiesProcessor;
	}

	/**
	 * Sets the property processor
	 * 
	 * @param property - property processor to set
	 * 
	 * @return this schematic
	 */
	public Schematic setPropertyProcessor(PropertiesProcessor property) {
		this.propertiesProcessor = property;
		return this;
	}

	/**
	 * Get the block processor
	 * 
	 * @return current block processor or null
	 */
	public BlockProcessor getBlockProcessor() {
		return blockProcessor;
	}

	/**
	 * Sets the block processor
	 * 
	 * @param block - block processor to set
	 * 
	 * @return this schematic
	 */
	public Schematic setBlockProcessor(BlockProcessor block) {
		this.blockProcessor = block;
		return this;
	}

	/**
	 * Get the entity processor
	 * 
	 * @return current entity processor or null
	 */
	public EntityProcessor getEntityProcessor() {
		return entityProcessor;
	}

	/**
	 * Sets the entity processor
	 * 
	 * @param entity - entity processor to set
	 * 
	 * @return this schematic
	 */
	public Schematic setEntityProcessor(EntityProcessor entity) {
		this.entityProcessor = entity;
		return this;
	}

	public Schematic spawn(IWriteable data) {
		return this;
	}

	public Schematic paste(IWriteable data) {
		return this;
	}

	/**
	 * Load from nbt
	 * 
	 * @param nbt - NbtCompound that should be loaded from
	 */
	@Override
	public void fromNbt(NbtCompound nbt) {
		blockGrid.clear();
		entityGrid.clear();
		NbtCompound types = nbt.getCompound("types");

		HashMap<Integer, ABlock> blockIds = new HashMap<>();
		NbtCompound blocks = types.getCompound("block");
		for (String key : blocks.getKeys()) {
			if (Strings.isNumeric(key)) {
				blockIds.put(Integer.parseInt(key), blockProcessor.process(blocks.getCompound(key)));
			}
		}

		HashMap<Integer, AEntity> entityIds = new HashMap<>();
		NbtCompound entities = types.getCompound("entity");
		for (String key : entities.getKeys()) {
			if (Strings.isNumeric(key)) {
				entityIds.put(Integer.parseInt(key), entityProcessor.process(entities.getCompound(key)));
			}
		}

		NbtCompound layers = nbt.getCompound("blocks");
		for (String key1 : layers.getKeys()) {
			if (!Strings.isNumeric(key1)) {
				continue;
			}
			int y = Integer.parseInt(key1);
			NbtCompound rows = layers.getCompound(key1);
			for (String key2 : rows.getKeys()) {
				if (!Strings.isNumeric(key2)) {
					continue;
				}
				int x = Integer.parseInt(key2);
				NbtCompound values = rows.getCompound(key2);
				for (String key3 : values.getKeys()) {
					if (!Strings.isNumeric(key2)) {
						continue;
					}
					blockGrid.set(y, x, Integer.parseInt(key3), blockIds.get(values.getInt(key3)));
				}
			}
		}

		layers = nbt.getCompound("entities");
		for (String key1 : layers.getKeys()) {
			if (!Strings.isNumeric(key1)) {
				continue;
			}
			int y = Integer.parseInt(key1);
			NbtCompound rows = layers.getCompound(key1);
			for (String key2 : rows.getKeys()) {
				if (!Strings.isNumeric(key2)) {
					continue;
				}
				int x = Integer.parseInt(key2);
				NbtCompound values = rows.getCompound(key2);
				for (String key3 : values.getKeys()) {
					if (!Strings.isNumeric(key2)) {
						continue;
					}
					entityGrid.set(y, x, Integer.parseInt(key3), entityIds.get(values.getInt(key3)));
				}
			}
		}
		properties = propertiesProcessor.process(nbt.getCompound("properties"));
	}

	/**
	 * Save to nbt
	 * 
	 * @return resulting NbtCompound
	 */
	@Override
	public NbtCompound asNbt() {
		NbtCompound compound = new NbtCompound();

		compound.set("height", blockGrid.getHeight());
		compound.set("width", blockGrid.getWidth());
		compound.set("depth", blockGrid.getDepth());

		HashMap<NbtCompound, Integer> typeIds = new HashMap<>();
		int id = 0;

		NbtCompound blocks = new NbtCompound();
		for (GridLayer<ABlock> layer : blockGrid.getLayers()) {
			NbtCompound rows = new NbtCompound();
			for (GridRow<ABlock> row : layer.getRows()) {
				NbtCompound values = new NbtCompound();
				for (GridValue<ABlock> value : row.getValues()) {
					NbtCompound data = value.getValue().asNbt();
					int typeId;
					if (typeIds.containsKey(data)) {
						typeId = typeIds.get(data);
					} else {
						typeIds.put(data, (typeId = id));
						id++;
					}
					values.set("" + value.getZ(), typeId);
				}
				rows.set("" + row.getX(), values);
			}
			blocks.set("" + layer.getY(), rows);
		}

		NbtCompound blockTypes = new NbtCompound();
		for (Entry<NbtCompound, Integer> entry : typeIds.entrySet()) {
			blockTypes.set("" + entry.getValue(), entry.getKey());
		}
		typeIds.clear();
		id = 0;

		NbtCompound entities = new NbtCompound();
		for (GridLayer<AEntity> layer : entityGrid.getLayers()) {
			NbtCompound rows = new NbtCompound();
			for (GridRow<AEntity> row : layer.getRows()) {
				NbtCompound values = new NbtCompound();
				for (GridValue<AEntity> value : row.getValues()) {
					NbtCompound data = value.getValue().asNbt();
					int typeId;
					if (typeIds.containsKey(data)) {
						typeId = typeIds.get(data);
					} else {
						typeIds.put(data, (typeId = id));
						id++;
					}
					values.set("" + value.getZ(), typeId);
				}
				rows.set("" + row.getX(), values);
			}
			entities.set("" + layer.getY(), rows);
		}

		NbtCompound entityTypes = new NbtCompound();
		for (Entry<NbtCompound, Integer> entry : typeIds.entrySet()) {
			entityTypes.set("" + entry.getValue(), entry.getKey());
		}
		typeIds.clear();
		id = 0;

		NbtCompound types = new NbtCompound();
		types.set("block", blockTypes);
		types.set("entity", entityTypes);

		compound.set("types", types);
		compound.set("blocks", blocks);
		compound.set("entities", entities);
		compound.set("properties", properties.asNbt());

		return compound;
	}

	/**
	 * 
	 * Schematic Exception Utility
	 * 
	 * @Desc Used to easily throw / log exceptions
	 * 
	 * @param throwable to log / throw as cause
	 */
	protected void invalid(Throwable throwable) throws InvalidSchematicException {
		if(logger == null)
			throw new InvalidSchematicException(throwable);
		logger.log(throwable);
	}
	
	/**
	 * 
	 * Schematic Exception Utility
	 * 
	 * @Desc Used to easily throw / log exceptions
	 * 
	 * @param message to display
	 * @param throwable to log / throw as cause
	 */
	protected void invalid(String message, Throwable throwable) throws InvalidSchematicException {
		if(logger == null)
			throw new InvalidSchematicException(message, throwable);
		logger.log(throwable);
	}
	
	/**
	 * 
	 * Schematic Exception Utility
	 * 
	 * @Desc Used to easily throw / log exceptions
	 * 
	 * @param message to display
	 * @param throwable to log / throw as cause
	 */
	protected void invalid(String message) throws InvalidSchematicException {
		throw new InvalidSchematicException(message);
	}

	/**
	 * 
	 * Schematic Builder Utility
	 * 
	 * @Desc Used to easily create / load schematics
	 * 
	 */
	public static final class Builder extends SchematicBuilder {

		public Builder() {
			super(null, null, null);
		}

		public Builder(ILogger logger) {
			super(logger, null, null, null);
		}

		public Builder(PropertiesProcessor property, EntityProcessor entity, BlockProcessor block) {
			super(property, entity, block);
		}

		public Builder(ILogger logger, PropertiesProcessor property, EntityProcessor entity, BlockProcessor block) {
			super(logger, property, entity, block);
		}

	}

}
