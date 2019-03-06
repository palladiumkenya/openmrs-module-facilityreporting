package org.openmrs.module.facilityreporting.api.models;

import org.openmrs.BaseOpenmrsData;

import java.util.UUID;

public class FacilityReportIndicator extends BaseOpenmrsData {
	
	private Integer id;
	
	private String name;
	
	private FacilityReportDataset dataset;
	
	private String description;
	
	private String uuid;
	
	private String mapping;
	
	public FacilityReportIndicator() {
		prePersist();
	}
	
	public FacilityReportIndicator(String name, String description, String uuid, String mapping) {
		this.name = name;
		this.description = description;
		this.uuid = uuid;
		this.mapping = mapping;
	}
	
	public void prePersist() {
		
		if (null == getUuid())
			setUuid(UUID.randomUUID().toString());
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public FacilityReportDataset getDataset() {
		return dataset;
	}
	
	public void setDataset(FacilityReportDataset dataset) {
		this.dataset = dataset;
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
