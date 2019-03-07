package org.openmrs.module.facilityreporting.api.restUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RestReportRequestMapper {
	
	String reportID;
	
	String startDate;
	
	String endDate;
	
	@JsonCreator
	public RestReportRequestMapper(@JsonProperty("REPORTID") String reportID, @JsonProperty("STARTDATE") String startDate,
	    @JsonProperty("ENDDATE") String endDate) {
		this.reportID = reportID;
		this.startDate = startDate;
		this.endDate = endDate;
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
}
