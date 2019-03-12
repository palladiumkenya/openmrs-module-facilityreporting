package org.openmrs.module.facilityreporting.api.restUtil;

public class DatasetIndicatorDetails {
	
	private String name;
	
	private int id;
	
	private String value;
	
	public DatasetIndicatorDetails() {
	}
	
	public DatasetIndicatorDetails(String name, int id, String value) {
		this.name = name;
		this.id = id;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
