package org.openmrs.module.facilityreporting.api.models;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.module.reporting.indicator.Indicator;

import java.util.UUID;

public class FacilityReportMappingPrefix extends BaseOpenmrsData {
	
	private Integer id;
	
	private String uuid;
	
	private Indicator indicator;
	
	private String prefix;
	
	private String description;
	
	public FacilityReportMappingPrefix() {
		prePersist();
	}
	
	public FacilityReportMappingPrefix(String uuid, Indicator indicator, String prefix, String description) {
		this.uuid = uuid;
		this.indicator = indicator;
		this.prefix = prefix;
		this.description = description;
	}
	
	public void prePersist() {
		
		if (null == getUuid())
			setUuid(UUID.randomUUID().toString());
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
	
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Indicator getIndicator() {
		return indicator;
	}
	
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	
}
