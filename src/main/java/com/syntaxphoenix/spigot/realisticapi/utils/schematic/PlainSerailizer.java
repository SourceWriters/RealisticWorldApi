package com.syntaxphoenix.spigot.realisticapi.utils.schematic;

import com.syntaxphoenix.syntaxapi.utils.java.Strings;

public final class PlainSerailizer {

	public static int[] parseLocation(String value) {
		int[] location = new int[] { 0, 0, 0 };
		if (value == null || value.isEmpty() || value.trim().isEmpty())
			return location;
		String[] parts = value.split("_", 3);
		if (parts.length >= 1)
			location[0] = Strings.isNumeric(parts[0]) ? Integer.parseInt(parts[0]) : 0;
		if (parts.length >= 2)
			location[1] = Strings.isNumeric(parts[1]) ? Integer.parseInt(parts[1]) : 0;
		if (parts.length >= 3)
			location[2] = Strings.isNumeric(parts[2]) ? Integer.parseInt(parts[2]) : 0;
		return location;
	}

}
