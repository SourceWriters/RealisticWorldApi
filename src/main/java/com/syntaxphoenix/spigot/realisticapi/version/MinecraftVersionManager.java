package com.syntaxphoenix.spigot.realisticapi.version;

import com.syntaxphoenix.syntaxapi.version.VersionManager;
import com.syntaxphoenix.syntaxapi.version.VersionState;

public class MinecraftVersionManager extends VersionManager<MinecraftVersion> {
	
	@Override
	public VersionManager<MinecraftVersion> set(VersionState state, MinecraftVersion version) {
		if(version.getMajor() < 1 || version.getMinor() < 13) {
			if(state == null) {
				return super.set(null, version);
			}
			return super.set(VersionState.NOT_COMPATIBLE, version);
		}
		return super.set(state, version);
	}

}
