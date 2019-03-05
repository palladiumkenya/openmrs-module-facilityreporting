package org.openmrs.module.facilityreporting.api.models;

import org.openmrs.BaseOpenmrsData;

public class FacilityReportDataset extends BaseOpenmrsData {
	
	private Integer id;
	
	private FacilityReport report;
	
	private String name;
	
	private String description;
	
	private String uuid;
	
	private String mapping;
	
	public FacilityReportDataset(String name, String description, String uuid, String mapping) {
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
	
	public FacilityReport getReport() {
		return report;
	}
	
	public void setReport(FacilityReport report) {
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
