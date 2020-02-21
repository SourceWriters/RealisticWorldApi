package com.syntaxphoenix.realisticapi.utils;

public class LoadingStatus {

	public static final LoadingStatus EMPTY = new LoadingStatus(true);

	public static final LoadingStatus create() {
		return new LoadingStatus(false);
	};

	private int total = 0;
	private int failed = 0;
	private int success = 0;
	private int skipped = 0;
	private int cancelled = 0;

	private int marked = 0;

	private boolean loaded = false;

	/**
	 * Constructs a LoadingStatus with an specific loaded state
	 * 
	 * @param loaded - defines if it was loaded or not
	 */
	private LoadingStatus(boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * Constructs a LoadingStatus
	 * 
	 * @param total - starting total amount of objects to load
	 */
	public LoadingStatus(int total) {
		this.total = total;
	}

	/**
	 * Set the loading to done
	 */
	public void done() {
		loaded = true;
	}

	/**
	 * Checks if loading is completly done
	 * 
	 * @return if loading is done
	 */
	public boolean isLoaded() {
		return loaded;
	}

	/**
	 * Marks one object as successful
	 * 
	 * @return if it was marked or not
	 */
	public boolean success() {
		if (loaded || !mark())
			return false;
		success++;
		return true;
	}

	/**
	 * Marks one object as successful
	 * 
	 * @return if it was marked or not
	 */
	public boolean failed() {
		if (loaded || !mark())
			return false;
		failed++;
		return true;
	}

	/**
	 * Marks one object as skipped
	 * 
	 * @return if it was marked or not
	 */
	public boolean skip() {
		if (loaded || !mark())
			return false;
		skipped++;
		return true;
	}

	/**
	 * Marks one object as cancelled
	 * 
	 * @return if it was marked or not
	 */
	public boolean cancel() {
		if (loaded || !mark())
			return false;
		cancelled++;
		return true;
	}

	/**
	 * Marks an object
	 * 
	 * @return if it was marked or not
	 */
	private boolean mark() {
		if (marked == total)
			return false;
		marked++;
		return true;
	}

	/**
	 * Add one object to total objects to load
	 */
	public void add() {
		add(1);
	}

	/**
	 * Add amount to total objects to load
	 *
	 * @param amount - amount to add
	 */
	public void add(int amount) {
		if (loaded)
			return;
		total += amount;
	}

	/**
	 * Add LoadingStatus to this status
	 * 
	 * @param status - LoadingStatus to add
	 */
	public void add(LoadingStatus status) {
		if (loaded)
			return;
		total += status.total;
		failed += status.failed;
		marked += status.marked;
		success += status.success;
		skipped += status.skipped;
		cancelled += status.cancelled;
	}

	/**
	 * Get the total amount of objects to load
	 * 
	 * @return the total amount
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * Get the total amount of objects that were marked
	 * 
	 * @return the marked amount
	 */
	public int getMarked() {
		return marked;
	}

	/**
	 * Get the total amount of objects that failed to load
	 * 
	 * @return the failed amount
	 */
	public int getFailed() {
		return failed;
	}

	/**
	 * Get the amount of objects that we successfully loaded
	 * 
	 * @return the successful amount
	 */
	public int getSuccess() {
		return success;
	}

	/**
	 * Get the amount of objects that were not loaded
	 * 
	 * @return the not loaded amount
	 */
	public int getSkipped() {
		return skipped;
	}

	/**
	 * Get the amount of objects that were cancelled on load
	 * 
	 * @return the cancelled amount
	 */
	public int getCancelled() {
		return cancelled;
	}

}
