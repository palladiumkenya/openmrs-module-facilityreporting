package org.openmrs.module.facilityreporting.api.models;

import org.openmrs.BaseOpenmrsData;

import java.util.Date;
import java.util.UUID;

public class FacilityReportData extends BaseOpenmrsData {
	
	private Integer id;
	
	private FacilityReportIndicator indicator;
	
	private FacilityReport report;
	
	private String value;
	
	private Date startDate;
	
	private Date endDate;
	
	public FacilityReportData() {
		prePersist();
	}
	
	public FacilityReportData(Integer id, String value, Date startDate, Date endDate) {
		this.id = id;
		this.value = value;
		this.startDate = startDate;
		this.endDate = endDate;
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
	
	public FacilityReportIndicator getIndicator() {
		return indicator;
	}
	
	public void setIndicator(FacilityReportIndicator indicator) {
		this.indicator = indicator;
	}
	
	public FacilityReport getReport() {
		return report;
	}
	
	public void setReport(FacilityReport report) {
		this.report = report;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
