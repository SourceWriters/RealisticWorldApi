package com.syntaxphoenix.spigot.realisticapi.version;

import com.syntaxphoenix.syntaxapi.version.VersionAnalyzer;

public class MinecraftAnalyzer implements VersionAnalyzer {

	@Override
	public MinecraftVersion analyze(String formatted) {
		String[] parts;
		boolean bukkit = false;
		if (formatted.contains(".")) {
			parts = formatted.split("\\.");
		} else if (formatted.contains("_")) {
			bukkit = true;
			parts = formatted.split("_");
		} else {
			return MinecraftVersion.NONE;
		}
		if (bukkit && parts.length == 3) {
			MinecraftVersion version = new MinecraftVersion();
			version.setMajor(Integer.parseInt(parts[0]));
			version.setMinor(Integer.parseInt(parts[1]));
			return version;
		} else if (!bukkit && (parts.length == 2 || parts.length == 3)) {
			MinecraftVersion version = new MinecraftVersion();
			version.setMajor(Integer.parseInt(parts[0]));
			version.setMinor(Integer.parseInt(parts[1]));
			version.setPatch(parts.length == 3 ? Integer.parseInt(parts[2]) : 0);
			return version;
		} else {
			return MinecraftVersion.NONE;
		}
	}

}
