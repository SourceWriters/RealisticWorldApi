package com.syntaxphoenix.realisticapi.schematic;

import java.util.HashMap;
import java.util.Map.Entry;

import com.syntaxphoenix.realisticapi.data.block.BlockProcessor;
import com.syntaxphoenix.realisticapi.data.block.RealBlock;
import com.syntaxphoenix.realisticapi.data.entity.EntityProcessor;
import com.syntaxphoenix.realisticapi.data.entity.RealEntity;
import com.syntaxphoenix.realisticapi.data.property.PropertyProcessor;
import com.syntaxphoenix.realisticapi.data.property.RealProperty;
import com.syntaxphoenix.realisticapi.data.property.RealisticProperty;
import com.syntaxphoenix.syntaxapi.logging.SynLogger;
import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtList;
import com.syntaxphoenix.syntaxapi.nbt.NbtType;
import com.syntaxphoenix.syntaxapi.nbt.utils.NbtStorage;
import com.syntaxphoenix.syntaxapi.utils.data.Properties;
import com.syntaxphoenix.syntaxapi.utils.data.Property;
import com.syntaxphoenix.syntaxapi.utils.java.Strings;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridLayer;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridMap;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridRow;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridType;
import com.syntaxphoenix.syntaxapi.utils.position.grid.GridValue;

public class Schematic implements NbtStorage<NbtCompound> {

	protected final GridMap<RealBlock> blockGrid = new GridMap<>(GridType.GRID_3D);
	protected final GridMap<RealEntity> entityGrid = new GridMap<>(GridType.GRID_2D);
	protected final Properties properties = new Properties();
	
	private PropertyProcessor property;
	private EntityProcessor entity;
	private BlockProcessor block;
	private SynLogger logger;
	
	/**
	 * Get the properties
	 * 
	 * @return saved properties
	 */
	public final Properties getProperties() {
		return properties;
	}
	
	/**
	 * Get the schematic's name
	 * 
	 * @return empty string or in properties set name
	 */
	public final String getName() {
		Property<String> name = properties.findProperty("name").parseString();
		return name == null ? "" : name.getValue();
	}
	
	/**
	 * Set the schematic's name
	 * 
	 * @param name - new schematic name
	 */
	public final void setName(String name) {
		
	}

	/**
	 * Get the block GridMap
	 * 
	 * @return GridMap that contains all saved blocks
	 */
	public final GridMap<RealBlock> getBlockGrid() {
		return blockGrid;
	}

	/**
	 * Get the entity GridMap
	 * 
	 * @return GridMap that contains all saved entities
	 */
	public final GridMap<RealEntity> getEntityGrid() {
		return entityGrid;
	}

	/**
	 * Get the logger
	 * 
	 * @return current logger or null
	 */
	public SynLogger getLogger() {
		return logger;
	}

	/**
	 * Sets the logger
	 * 
	 * @param logger - logger to set
	 * 
	 * @return this schematic
	 */
	public Schematic setLogger(SynLogger logger) {
		this.logger = logger;
		return this;
	}

	/**
	 * Get the property processor
	 * 
	 * @return current property processor or null
	 */
	public PropertyProcessor getPropertyProcessor() {
		return property;
	}

	/**
	 * Sets the property processor
	 * 
	 * @param property - property processor to set
	 * 
	 * @return this schematic
	 */
	public Schematic setPropertyProcessor(PropertyProcessor property) {
		this.property = property;
		return this;
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
	 * Sets the block processor
	 * 
	 * @param block - block processor to set
	 * 
	 * @return this schematic
	 */
	public Schematic setBlockProcessor(BlockProcessor block) {
		this.block = block;
		return this;
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
	 * Sets the entity processor
	 * 
	 * @param entity - entity processor to set
	 * 
	 * @return this schematic
	 */
	public Schematic setEntityProcessor(EntityProcessor entity) {
		this.entity = entity;
		return this;
	}

	/**
	 * Load from nbt
	 * 
	 * @param nbt - NbtCompound that should be loaded from
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void fromNbt(NbtCompound nbt) {
		blockGrid.clear();
		entityGrid.clear();
		properties.clearProperties();
		NbtCompound types = nbt.getCompound("types");
		
		HashMap<Integer, RealBlock> blockIds = new HashMap<>();
		NbtCompound blocks = types.getCompound("block");
		for (String key : blocks.getKeys()) {
			if (Strings.isNumeric(key)) {
				blockIds.put(Integer.parseInt(key), block.process(blocks.getCompound(key)));
			}
		}
		
		HashMap<Integer, RealEntity> entityIds = new HashMap<>();
		NbtCompound entities = types.getCompound("entity");
		for (String key : entities.getKeys()) {
			if (Strings.isNumeric(key)) {
				entityIds.put(Integer.parseInt(key), entity.process(entities.getCompound(key)));
			}
		}
		
		NbtCompound layers = new NbtCompound();
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
		
		NbtCompound rows = nbt.getCompound("entities");
		for (String key1 : rows.getKeys()) {
			if (!Strings.isNumeric(key1)) {
				continue;
			}
			int x = Integer.parseInt(key1);
			NbtCompound values = rows.getCompound(key1);
			for (String key2 : values.getKeys()) {
				if (!Strings.isNumeric(key1)) {
					continue;
				}
				entityGrid.set(x, Integer.parseInt(key2), entityIds.get(values.getInt(key2)));
			}
		}
		
		NbtList<NbtCompound> processed = (NbtList<NbtCompound>) nbt.getTagList("properties");
		if(processed.isEmpty()) {
			return;
		}
		for(NbtCompound process : processed) {
			RealProperty<?> data = property.process(process);
			if(data == null) {
				continue;
			}
			properties.addProperty(data.getProperty());
		}
		
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

		NbtCompound layers = new NbtCompound();
		for (GridLayer<RealBlock> layer : blockGrid.getLayers()) {
			NbtCompound rows = new NbtCompound();
			for (GridRow<RealBlock> row : layer.getRows()) {
				NbtCompound values = new NbtCompound();
				for (GridValue<RealBlock> value : row.getValues()) {
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
			layers.set("" + layer.getY(), rows);
		}
		
		NbtCompound blockTypes = new NbtCompound();
		for (Entry<NbtCompound, Integer> entry : typeIds.entrySet()) {
			blockTypes.set("" + entry.getValue(), entry.getKey());
		}
		typeIds.clear();
		id = 0;
		
		NbtCompound rows = new NbtCompound();
		if (entityGrid.getHeight() != 0) {
			for (GridRow<RealEntity> row : entityGrid.getLayers()[0].getRows()) {
				NbtCompound values = new NbtCompound();
				for (GridValue<RealEntity> value : row.getValues()) {
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
		}
		
		NbtCompound entityTypes = new NbtCompound();
		for (Entry<NbtCompound, Integer> entry : typeIds.entrySet()) {
			blockTypes.set("" + entry.getValue(), entry.getKey());
		}
		typeIds.clear();
		id = 0;
		
		NbtList<NbtCompound> properties = new NbtList<>(NbtType.COMPOUND);
		for(Property<?> property : this.properties.getProperties()) {
			properties.add(new RealisticProperty<>(property).asNbt());
		}
		
		NbtCompound types = new NbtCompound();
		types.set("block", blockTypes);
		types.set("entity", entityTypes);
		
		compound.set("types", types);
		compound.set("blocks", layers);
		compound.set("entities", rows);
		compound.set("properties", properties);
		
		return compound;
	}

}
