package com.syntaxphoenix.realisticapi.schematic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.syntaxphoenix.realisticapi.RealisticApi;
import com.syntaxphoenix.realisticapi.RealisticApiHandler;
import com.syntaxphoenix.realisticapi.event.schematic.*;
import com.syntaxphoenix.realisticapi.utils.LoadingStatus;
import com.syntaxphoenix.syntaxapi.event.EventManager;
import com.syntaxphoenix.syntaxapi.logging.LogType;
import com.syntaxphoenix.syntaxapi.threading.SynThreadPool;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class SchematicStorage extends RealisticApiHandler {
	
	/**
	 * Constructs a SchematicStorage
	 * 
	 * @param api - the api of RWG
	 */
	public SchematicStorage(RealisticApi api) {
		super(api);
	}
	
	private final ArrayList<Schematic> schematics = new ArrayList<>();
	private final SynThreadPool threadPool = new SynThreadPool(api.newThreadReporter(), 2, 8, "SchematicLoader");
	
	/**
	 * Register a schematic
	 * 
	 * @param schematic - Schematic that should be registered
	 * 
	 * @return if schematic was registered or not
	 */
	public boolean register(Schematic schematic) {
		Property<String> name = schematic.getProperties().findProperty("name").parseString();
		if(name == null)
			return false;
		return register(name.getValue(), schematic);
	}

	/**
	 * Register a schematic
	 * 
	 * @param name - name that the schematic should have
	 * @param schematic - Schematic that should be registered
	 * 
	 * @return if schematic was registered or not
	 */
	public boolean register(String name, Schematic schematic) {
		name = name.replace(' ', '_').toUpperCase();
		if(schematics.contains(schematic) || containsUpped(name))
			return false;
 		schematic.setName(name);
		return schematics.add(schematic);
	}

	/**
	 * Checks if a name exists
	 * 
	 * @param name - name in uppercase with not whitespace to check
	 * 
	 * @return if the name already existed or not
	 */
	private boolean containsUpped(String name) {
		return schematics.stream().anyMatch(schematic -> schematic.getName().equals(name));
	}

	/**
	 * Checks if a name exists
	 * 
	 * @param name - name to check
	 * 
	 * @return if the name already existed or not
	 */
	public boolean contains(String name) {
		return containsUpped(name.replace(' ', '_').toUpperCase());
	}

	/**
	 * Search for an Schematic
	 * 
	 * @param name - name in uppercase with not whitespace to search for
	 * 
	 * @return Optional that could contain a Schematic
	 */
	private Optional<Schematic> getByNameUpped(String name) {
		return schematics.stream().filter(schematic -> schematic.getName().equals(name)).findFirst();
	}


	/**
	 * Search for an Schematic
	 * 
	 * @param name - name to search for
	 * 
	 * @return Optional that could contain a Schematic
	 */
	public Optional<Schematic> getByName(String name) {
		name = name.replace(' ', '_').toUpperCase();
		return getByNameUpped(name);
	}
	
	/**
	 * Load all schematics
	 */
	@SuppressWarnings("unchecked")
	public LoadingStatus load() {
		if(schematics.isEmpty()) {
			return LoadingStatus.EMPTY;
		}
		List<Schematic> schematics = (List<Schematic>) this.schematics.clone();
		LoadingStatus status = new LoadingStatus(schematics.size());
		threadPool.submit(() -> {
			EventManager events = api.getEventManager();
			for(Schematic schematic : schematics) {
				try {
					SchematicEvent event = new SchematicLoadEvent(schematic);
					events.call(event);
					if(event.isCancelled()) {
						status.cancel();
						continue;
					}
					((FiledSchematic) schematic).save();
					status.success();
				} catch(RuntimeException exception) {
					api.getLogger().log(LogType.WARNING, exception);
					status.failed();
				}
				continue;
			}
		});
		return status;
	}
	
	/**
	 * Save all schematics
	 */
	@SuppressWarnings("unchecked")
	public LoadingStatus save() {
		if(schematics.isEmpty()) {
			return LoadingStatus.EMPTY;
		}
		List<Schematic> schematics = (List<Schematic>) this.schematics.clone();
		LoadingStatus status = new LoadingStatus(schematics.size());
		threadPool.submit(() -> {
			EventManager events = api.getEventManager();
			for(Schematic schematic : schematics) {
				if(schematic instanceof FiledSchematic) {
					try {
						SchematicEvent event = new SchematicSaveEvent(schematic);
						events.call(event);
						if(event.isCancelled()) {
							status.cancel();
							continue;
						}
						((FiledSchematic) schematic).save();
						status.success();
					} catch(RuntimeException exception) {
						api.getLogger().log(LogType.WARNING, exception);
						status.failed();
					}
					continue;
				}
				status.skip();
			}
		});
		return status;
	}
	
}
