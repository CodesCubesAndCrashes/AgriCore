/*
 */
package com.agricraft.agricore.registry;

import com.agricraft.agricore.plant.AgriSoil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriSoils implements AgriLoadableRegistry<AgriSoil> {

	private final Map<String, AgriSoil> soils;

	public AgriSoils() {
		this.soils = new HashMap<>();
	}
	
	public boolean hasSoil(String id) {
		return this.soils.containsKey(id);
	}
	
	public boolean addSoil(AgriSoil plant) {
		return this.soils.putIfAbsent(plant.getId(), plant) == null;
	}
	
	public AgriSoil getSoil(String id) {
		return this.soils.get(id);
	}
	
	public List<AgriSoil> getAll() {
		return new ArrayList<>(this.soils.values());
	}
	
	public void validate() {
		soils.entrySet().removeIf((e) -> (!e.getValue().validate()));
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nSoils:");
		for (AgriSoil plant : soils.values()) {
			sb.append("\n\t- Soil: ");
			sb.append(plant.toString().replaceAll("\n", "\n\t").trim());
		}
		return sb.append("\n").toString();
	}

	@Override
	public boolean acceptsElement(String filename) {
		return filename.toLowerCase().endsWith("_soil.json");
	}

	@Override
	public Class<AgriSoil> getElementClass() {
		return AgriSoil.class;
	}

	@Override
	public void registerElement(AgriSoil element) {
		this.addSoil(element);
	}

	@Override
	public void clearElements() {
		this.soils.clear();
	}

}