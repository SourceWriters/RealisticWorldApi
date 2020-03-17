package com.syntaxphoenix.spigot.realisticapi.data.property;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import com.syntaxphoenix.syntaxapi.nbt.NbtCompound;
import com.syntaxphoenix.syntaxapi.nbt.NbtList;
import com.syntaxphoenix.syntaxapi.nbt.NbtType;
import com.syntaxphoenix.syntaxapi.utils.data.Property;

public class RealisticProperties extends AProperties<RealisticProperty<?>> {

	private final ArrayList<RealisticProperty<?>> properties = new ArrayList<>();

	@Override
	public int getPropertyCount() {
		return properties.size();
	}

	@Override
	public void clearProperties() {
		properties.clear();
	}

	@Override
	public RealisticProperty<?>[] getProperties() {
		return properties.toArray(new RealisticProperty<?>[0]);
	}

	@Override
	protected Stream<RealisticProperty<?>> stream() {
		return properties.stream();
	}

	@Override
	protected boolean add(Property<?> property) {
		return add(new RealisticProperty<>(property));
	}

	@Override
	protected boolean add(RealisticProperty<?> property) {
		return properties.add(property);
	}

	@Override
	protected boolean remove(Property<?> property) {
		Optional<RealisticProperty<?>> option = properties.stream()
				.filter(realistic -> realistic.getProperty().equals(property)).findAny();
		if (!option.isPresent())
			return false;
		return remove(option.get());
	}

	@Override
	protected boolean remove(RealisticProperty<?> property) {
		return properties.remove(property);
	}

	@Override
	protected boolean isEmpty() {
		return properties.isEmpty();
	}

	@Override
	public NbtCompound asNbt() {
		NbtCompound compound = new NbtCompound();
		compound.set("size", properties.size());
		if(isEmpty()) {
			return compound;
		}
		NbtList<NbtCompound> nbtProperties = new NbtList<>(NbtType.COMPOUND);
		for(RealisticProperty<?> property : properties) {
			nbtProperties.add(property.asNbt());
		}
		compound.set("properties", nbtProperties);
		return compound;
	}

}
