package org.openmrs.module.facilityreporting.api;

import org.openmrs.BaseOpenmrsData;

public class Report extends BaseOpenmrsData {
	
	private Integer id;
	
	private String name;
	
	private String uuid;
	
	private String description;
	
	private String mapping;
	
	public Report(String name, String uuid, String description, String mapping) {
		this.name = name;
		this.uuid = uuid;
		this.description = description;
		this.mapping = mapping;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMapping() {
		return mapping;
	}
	
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
}
