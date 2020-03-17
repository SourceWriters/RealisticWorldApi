package com.syntaxphoenix.spigot.realisticapi.data.property;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import com.syntaxphoenix.spigot.realisticapi.data.AData;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public abstract class AProperties<V extends AProperty<?>> extends AData {

	public void setProperties(Property<?>... properties) {
		clearProperties();
		for (Property<?> property : properties) {
			setProperty(property);
		}
	}

	public void setProperty(Property<?> property) {
		V remove;
		if ((remove = findProperty(property.getKey())) != null) {
			remove(remove);
		}
		add(property);
	}

	@SuppressWarnings("unchecked")
	public void setProperties(V... properties) {
		clearProperties();
		for (V property : properties) {
			setProperty(property);
		}
	}

	public void setProperty(V property) {
		V remove;
		if ((remove = findProperty(property.getKey())) != null) {
			remove(remove);
		}
		add(property);
	}

	public void addProperties(Property<?>... properties) {
		for (Property<?> property : properties) {
			addProperty(property);
		}
	}

	public void addProperty(Property<?> property) {
		if (!containsProperty(property.getKey())) {
			add(property);
		}
	}

	@SuppressWarnings("unchecked")
	public void addProperties(V... properties) {
		for (V property : properties) {
			addProperty(property);
		}
	}

	public void addProperty(V property) {
		if (!containsProperty(property.getKey())) {
			add(property);
		}
	}

	public void removeProperties(String... keys) {
		for (String key : keys) {
			removeProperty(key);
		}
	}

	public boolean removeProperty(String key) {
		if (isEmpty()) {
			return false;
		}
		Optional<V> optional = stream().filter(property -> property.getKey().equals(key)).findFirst();
		if (optional.isPresent()) {
			return remove(optional.get());
		}
		return false;
	}

	public V findProperty(String key) {
		if (isEmpty()) {
			return null;
		}
		Optional<V> optional = stream().filter(property -> property.getKey().equals(key)).findFirst();
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public ArrayList<V> findProperties(String... keys) {
		ArrayList<V> properties = new ArrayList<>();
		for (String key : keys) {
			V property;
			if ((property = findProperty(key)) != null) {
				properties.add(property);
			}
		}
		return properties;
	}

	public boolean containsProperty(String key) {
		return stream().filter(property -> property.getKey().equals(key)).findFirst().isPresent();
	}

	public boolean hasProperties() {
		return !isEmpty();
	}

	/*
	 * 
	 */

	public abstract int getPropertyCount();

	public abstract void clearProperties();

	public abstract V[] getProperties();

	protected abstract Stream<V> stream();

	protected abstract boolean add(Property<?> property);

	protected abstract boolean add(V property);

	protected abstract boolean remove(Property<?> property);

	protected abstract boolean remove(V property);

	protected abstract boolean isEmpty();

}
