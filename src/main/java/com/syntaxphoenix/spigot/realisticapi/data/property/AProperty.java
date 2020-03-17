package com.syntaxphoenix.spigot.realisticapi.data.property;

import com.syntaxphoenix.spigot.realisticapi.data.AData;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public abstract class AProperty<E> extends AData {

	protected final Property<E> property;
	protected final Class<E> type;

	public AProperty(Class<E> type, Property<E> property) {
		this.property = property;
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public AProperty(Property<E> property) {
		this((Class<E>) property.getValue().getClass(), property);
	}

	public Class<E> getPropertyType() {
		return type;
	}

	public Property<E> getProperty() {
		return property;
	}

	/*
	 * 
	 */

	public E getValue() {
		return property.getValue();
	}

	public String getKey() {
		return property.getKey();
	}

	/*
	 * 
	 */

	@SuppressWarnings("unchecked")
	public <V> AProperty<V> tryParse(Class<V> clazz) {
		Object obj = property.tryParse(clazz);
		return obj == null ? null : (AProperty<V>) this;
	}

	@SuppressWarnings("unchecked")
	public <V> AProperty<V> tryParse(V object) {
		Object obj = property.tryParse(object.getClass());
		return obj == null ? null : (AProperty<V>) this;
	}

	@SuppressWarnings("unchecked")
	public AProperty<String> parseString() {
		Property<String> stringified = property.parseString();
		return stringified.equals(property) ? (AProperty<String>) this : init(stringified);
	}

	public boolean isInstance(Object object) {
		return type.isInstance(object);
	}

	public boolean instanceOf(Class<?> clazz) {
		return type.isAssignableFrom(clazz);
	}

	/*
	 * 
	 */

	protected abstract <A> AProperty<A> init(Property<A> value);

}
