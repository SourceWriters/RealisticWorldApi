package com.syntaxphoenix.realisticapi.version;

import com.syntaxphoenix.syntaxapi.version.DefaultVersion;
import com.syntaxphoenix.syntaxapi.version.Version;
import com.syntaxphoenix.syntaxapi.version.VersionAnalyzer;

public class MinecraftVersion extends DefaultVersion {
	
	public static final MinecraftVersion NONE = new MinecraftVersion(false);
	public static final MinecraftAnalyzer ANALYZER = new MinecraftAnalyzer(); 
	
	private final boolean valid;
	
	/*
	 * 
	 */
	
	private MinecraftVersion(boolean valid) {
		this.valid = valid;
	}
	
	public MinecraftVersion() {
		super();
		this.valid = true;
	}
	
	public MinecraftVersion(int major, int minor, int patch) {
		super(major, minor, patch);
		this.valid = true;
	}
	
	/*
	 * 
	 */
	
	public final boolean isValid() {
		return valid;
	}
	
	/*
	 * 
	 */
	
	@Override
	protected Version setMajor(int major) {
		return super.setMajor(major);
	}
	
	@Override
	protected Version setMinor(int minor) {
		return super.setMinor(minor);
	}
	
	@Override
	protected Version setPatch(int patch) {
		return super.setPatch(patch);
	}
	
	/*
	 * 
	 */

	@Override
	protected MinecraftVersion init(int major, int minor, int patch) {
		return new MinecraftVersion(major, minor, patch);
	}

	@Override
	public VersionAnalyzer getAnalyzer() {
		return ANALYZER;
	}
	
	/*
	 * 
	 */
	
	public static MinecraftVersion fromString(String versionString) {
		return ANALYZER.analyze(versionString);
	}
	
	public static MinecraftVersion[] fromStringArray(String... versionStrings) {
		MinecraftVersion[] versions = new MinecraftVersion[versionStrings.length];
		int index = 0;
		for(String versionString : versionStrings) {
			versions[index] = ANALYZER.analyze(versionString);
			index++;
		}
		return versions;
	}

}
