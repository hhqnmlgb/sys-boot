package com.sunys.core.run.impl;

import com.sunys.facade.run.EventType;

/**
 * StateEventType
 * @author sunys
 * @date Jun 11, 2020
 */
public class StateEventType implements EventType {

	private String name;
	
	public StateEventType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateEventType other = (StateEventType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StateEventType [name=" + name + "]";
	}
}
