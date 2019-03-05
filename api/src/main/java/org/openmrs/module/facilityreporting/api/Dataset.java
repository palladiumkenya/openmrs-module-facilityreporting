package org.openmrs.module.facilityreporting.api;

import org.openmrs.BaseOpenmrsData;

public class Dataset extends BaseOpenmrsData {
	
	private Integer id;
	
	private Report report;
	
	private String name;
	
	private String description;
	
	private String uuid;
	
	private String mapping;
	
	public Dataset(String name, String description, String uuid, String mapping) {
		this.name = name;
		this.description = description;
		this.uuid = uuid;
		this.mapping = mapping;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Report getReport() {
		return report;
	}
	
	public void setReport(Report report) {
		this.report = report;
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
	
	@Override
	public String getUuid() {
		return uuid;
	}
	
	@Override
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getMapping() {
		return mapping;
	}
	
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
}
