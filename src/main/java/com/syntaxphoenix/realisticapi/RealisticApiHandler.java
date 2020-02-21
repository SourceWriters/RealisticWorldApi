package com.syntaxphoenix.realisticapi;

public abstract class RealisticApiHandler {
	
	protected final RealisticApi api;
	
	public RealisticApiHandler(RealisticApi api) {
		this.api = api;
	}
	
	public final RealisticApi getApi() {
		return api;
	}

}
