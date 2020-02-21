package com.syntaxphoenix.realisticapi.data.property;

import com.syntaxphoenix.realisticapi.data.RealData;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public abstract class RealProperty<E> extends RealData {

	protected final Property<E> property;
	protected final Class<E> type;
	
	public RealProperty(Class<E> type, Property<E> property) {
		this.property = property;
		this.type = type;
	}
	
	@SuppressWarnings("unchecked")
	public RealProperty(Property<E> property) {
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
	
	public <V> Property<V> tryParse(Class<V> clazz) {
		return property.tryParse(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <V> Property<V> tryParse(V object) {
		return (Property<V>) tryParse(object.getClass());
	}
	
	public boolean isInstance(Object object) {
		return type.isInstance(object);
	}
	
	public boolean instanceOf(Class<?> clazz) {
		return type.isAssignableFrom(clazz);
	}
	
}
