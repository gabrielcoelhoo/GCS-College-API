package com.gabriel.gcscollegeAPI.model;

public enum Status {

	SENT,
	ANALYSIS,
	COMPLETED,
	COLLECTED,
	REFUSED;
	
	public static Status findByName(String name) {
		for (Status status : values()) {
			if(status.name().equals(name)) {
				return status;
			}
		}
		
		return null;
	}
}