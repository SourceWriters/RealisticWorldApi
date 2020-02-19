package com.syntaxphoenix.realisticapi;

import com.syntaxphoenix.realisticapi.addon.RealisticAddon;
import com.syntaxphoenix.realisticapi.version.MinecraftVersion;
import com.syntaxphoenix.realisticapi.version.MinecraftVersionManager;
import com.syntaxphoenix.syntaxapi.addon.AddonManager;
import com.syntaxphoenix.syntaxapi.addon.DefaultAddonManager;
import com.syntaxphoenix.syntaxapi.event.EventManager;
import com.syntaxphoenix.syntaxapi.logging.SynLogger;
import com.syntaxphoenix.syntaxapi.version.VersionManager;

public abstract class RealisticApi {
	
	protected final SynLogger logger;
	protected final MinecraftVersion version;
	protected final EventManager eventManager;
	protected final AddonManager<RealisticAddon> addonManager;
	protected final VersionManager<MinecraftVersion> versionManager;
	
	public RealisticApi(SynLogger logger, MinecraftVersion version) {
		this.logger = logger;
		this.version = version;
		this.eventManager = new EventManager(logger);
		this.addonManager = new DefaultAddonManager<>(RealisticAddon.class, logger);
		this.versionManager = new MinecraftVersionManager();
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
