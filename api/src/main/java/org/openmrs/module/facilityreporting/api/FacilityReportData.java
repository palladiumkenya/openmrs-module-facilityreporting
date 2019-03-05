package org.openmrs.module.facilityreporting.api;

import org.openmrs.BaseOpenmrsData;

import java.util.Date;

public class Data extends BaseOpenmrsData {
	
	private Integer id;
	
	private Indicator indicator;
	
	private Report report;
	
	private String value;
	
	private Date startDate;
	
	private Date endDate;
	
	public Data(Integer id, String value, Date startDate, Date endDate) {
		this.id = id;
		this.value = value;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Indicator getIndicator() {
		return indicator;
	}
	
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	
	public Report getReport() {
		return report;
	}
	
	public void setReport(Report report) {
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
