package com.syntaxphoenix.spigot.realisticapi.data.entity;

import org.bukkit.Location;
import org.bukkit.World;

import com.syntaxphoenix.spigot.realisticapi.data.RealData;

public abstract class RealEntity extends RealData {
	
	public void spawn(World world, int x, int z) {
		spawn(new Location(world, x, getY(), z));
	}
	
	public void spawn(World world, int x, int y, int z) {
		spawn(new Location(world, x, y, z));
	}
	
	public abstract void spawn(Location location);
	
	public abstract int getY();

}
