package com.syntaxphoenix.spigot.realisticapi;

import com.syntaxphoenix.spigot.realisticapi.addon.RealisticAddon;
import com.syntaxphoenix.spigot.realisticapi.schematic.SchematicStorage;
import com.syntaxphoenix.spigot.realisticapi.utils.CatchedException;
import com.syntaxphoenix.spigot.realisticapi.utils.Patches;
import com.syntaxphoenix.spigot.realisticapi.version.MinecraftVersion;
import com.syntaxphoenix.syntaxapi.addon.AddonManager;
import com.syntaxphoenix.syntaxapi.addon.DefaultAddonManager;
import com.syntaxphoenix.syntaxapi.event.EventManager;
import com.syntaxphoenix.syntaxapi.logging.SynLogger;
import com.syntaxphoenix.syntaxapi.threading.SynThreadPool;
import com.syntaxphoenix.syntaxapi.threading.SynThreadReporter;
import com.syntaxphoenix.syntaxapi.version.VersionManager;

public abstract class RealisticApi {
	
	protected final SynLogger logger;
	protected final MinecraftVersion version;
	protected final EventManager eventManager;
	protected final SchematicStorage schematicStorage;
	protected final AddonManager<RealisticAddon> addonManager;
	protected final VersionManager<MinecraftVersion> versionManager;
	
	public RealisticApi(SynLogger logger, MinecraftVersion version) {
		this.logger = logger;
		this.version = version;
		this.eventManager = new EventManager(logger);
		this.schematicStorage = new SchematicStorage(this);
		this.addonManager = new DefaultAddonManager<>(RealisticAddon.class, logger);
		this.versionManager = new VersionManager<>();
		
		Patches.apply(this);
		
	}
	
	/*
	 * 
	 */
	
	public final SynThreadReporter newThreadReporter() {
		return new SynThreadReporter() {
			@Override
			public void catchFail(Throwable throwable, SynThreadPool pool, Thread thread, Runnable command) {
				logger.log(new CatchedException("There were a problem while execusting a task on \"" + thread.getName() + "\"", throwable));
			}
		};
	}
	
	/* 
	 * 
	 */
	
	public final SynLogger getLogger() {
		return logger;
	}
	
	public final MinecraftVersion getVersion() {
		return version;
	}
	
	public final EventManager getEventManager() {
		return eventManager;
	}
	
	public final AddonManager<RealisticAddon> getAddonManager() {
		return addonManager;
	}
	
	public final VersionManager<MinecraftVersion> getVersionManager() {
		return versionManager;
	}
	
}
