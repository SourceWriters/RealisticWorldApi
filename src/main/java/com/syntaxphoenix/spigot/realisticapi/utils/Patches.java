package com.syntaxphoenix.spigot.realisticapi.utils;

import com.syntaxphoenix.spigot.realisticapi.RealisticApi;
import com.syntaxphoenix.syntaxapi.reflections.ReflectCache;
import com.syntaxphoenix.syntaxapi.version.VersionManager;
import com.syntaxphoenix.syntaxapi.version.VersionState;

public final class Patches {

	public static final ReflectCache CACHE = new ReflectCache();

	static {
		
		CACHE.create("version", VersionManager.class).searchField("high", "higher");
		
	}

	public static void apply(RealisticApi api) {
		
		CACHE.get("version").get().setFieldValue(api.getVersionManager(), "high", VersionState.NOT_TESTED);

	}

}
