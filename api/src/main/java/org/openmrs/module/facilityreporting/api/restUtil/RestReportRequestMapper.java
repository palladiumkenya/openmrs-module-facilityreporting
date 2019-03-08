package org.openmrs.module.facilityreporting.api.restUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RestReportRequestMapper {
	
	String reportID;
	
	String startDate;
	
	String endDate;
	
	String adxOrgUnit;
	
	String adxReportingPeriod;
	
	@JsonCreator
	public RestReportRequestMapper(@JsonProperty("REPORTID") String reportID, @JsonProperty("STARTDATE") String startDate,
	    @JsonProperty("ENDDATE") String endDate, @JsonProperty("ADXORGUNIT") String adxOrgUnit,
	    @JsonProperty("ADXREPORTINGPERIOD") String adxReportingPeriod) {
		this.reportID = reportID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adxOrgUnit = adxOrgUnit;
		this.adxReportingPeriod = adxReportingPeriod;
	}
	
	@JsonProperty("REPORTID")
	public String getReportID() {
		return reportID;
	}
	
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	
	@JsonProperty("STARTDATE")
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	@JsonProperty("ENDDATE")
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * we want to reuse org unit already generated from kenyaemr module
	 * 
	 * @return
	 */
	@JsonProperty("ADXORGUNIT")
	public String getAdxOrgUnit() {
		return adxOrgUnit;
	}
	
	public void setAdxOrgUnit(String adxOrgUnit) {
		this.adxOrgUnit = adxOrgUnit;
	}
	
	/**
	 * the reporting period is also shared across
	 * 
	 * @return
	 */
	@JsonProperty("ADXREPORTINGPERIOD")
	public String getAdxReportingPeriod() {
		return adxReportingPeriod;
	}
	
	public void setAdxReportingPeriod(String adxReportingPeriod) {
		this.adxReportingPeriod = adxReportingPeriod;
	}
}
