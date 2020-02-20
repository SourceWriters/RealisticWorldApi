package com.syntaxphoenix.realisticapi.data;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.tools.NbtJsonParser;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class RealProperty<E> extends RealData {

	private final Property<E> property;
	private final Class<E> type;
	
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
	
	/*
	 * 
	 */
	
	@Override
	public NbtCompound asNbt() {
		NbtCompound property = new NbtCompound();
		property.set("key", getKey());
		property.set("value", NbtJsonParser.toNbt(getValue()));

		NbtCompound compound = new NbtCompound();
		compound.set("type", type.getName());
		compound.set("property", property);
		return compound;
	}
	
}
