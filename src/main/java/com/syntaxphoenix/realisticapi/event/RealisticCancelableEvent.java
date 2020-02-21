package com.syntaxphoenix.realisticapi.event;

import com.syntaxphoenix.syntaxapi.event.Cancelable;

public abstract class RealisticCancelableEvent extends RealisticEvent implements Cancelable {
	
	private boolean cancelled = false;

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
