package com.syntaxphoenix.spigot.realisticapi;

import com.syntaxphoenix.spigot.realisticapi.schematic.SchematicStorage;
import com.syntaxphoenix.spigot.realisticapi.utils.exception.CatchedException;
import com.syntaxphoenix.spigot.realisticapi.version.MinecraftVersion;
import com.syntaxphoenix.syntaxapi.event.EventManager;
import com.syntaxphoenix.syntaxapi.logging.ILogger;
import com.syntaxphoenix.syntaxapi.service.ServiceManager;
import com.syntaxphoenix.syntaxapi.threading.SynReportThrower;
import com.syntaxphoenix.syntaxapi.threading.SynThreadReporter;
import com.syntaxphoenix.syntaxapi.version.VersionManager;
import com.syntaxphoenix.syntaxapi.version.VersionState;

public abstract class RealisticApi {

	protected final ILogger logger;
	protected final MinecraftVersion version;
	protected final EventManager eventManager;
	protected final ServiceManager serviceManager;
	protected final SchematicStorage schematicStorage;
	protected final VersionManager<MinecraftVersion> versionManager;

	public RealisticApi(ILogger logger, MinecraftVersion version) {
		this.logger = logger;
		this.version = version;
		this.eventManager = new EventManager(logger);
		this.serviceManager = new ServiceManager(logger);
		this.schematicStorage = new SchematicStorage(this);
		this.versionManager = new VersionManager<>(VersionState.NOT_COMPATIBLE, VersionState.NOT_TESTED,
				VersionState.NOT_COMPATIBLE);
	}

	/*
	 * 
	 */

	public final SynThreadReporter newThreadReporter() {
		return new SynThreadReporter() {
			@Override
			public void catchFail(Throwable throwable, SynReportThrower thrower, Thread thread, Runnable command) {
				logger.log(new CatchedException(
						"There were a problem while execusting a task on \"" + thread.getName() + "\"", throwable));
			}
		};
	}

	/* 
	 * 
	 */

	public final ILogger getLogger() {
		return logger;
	}

	public final MinecraftVersion getVersion() {
		return version;
	}

	public final EventManager getEventManager() {
		return eventManager;
	}

	public final ServiceManager getServiceManager() {
		return serviceManager;
	}

	public final SchematicStorage getSchematicStorage() {
		return schematicStorage;
	}

	public final VersionManager<MinecraftVersion> getVersionManager() {
		return versionManager;
	}

}
