package org.openmrs.module.facilityreporting.api.restUtil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ReportDatasetValueEntryMapper {
	
	private String reportID;
	
	private String datasetName;
	
	private String datasetID;
	
	private List<DatasetIndicatorDetails> indicators;
	
	@JsonCreator
	public ReportDatasetValueEntryMapper(@JsonProperty("reportId") String reportID,
	    @JsonProperty("datasetName") String datasetName, @JsonProperty("datasetId") String datasetID,
	    @JsonProperty("indicators") List<DatasetIndicatorDetails> indicators) {
		this.reportID = reportID;
		this.datasetName = datasetName;
		this.datasetID = datasetID;
		this.indicators = indicators;
	}
	
	@JsonProperty("datasetId")
	public String getDatasetID() {
		return datasetID;
	}
	
	public void setDatasetID(String datasetID) {
		this.datasetID = datasetID;
	}
	
	@JsonProperty("datasetName")
	public String getDatasetName() {
		return datasetName;
	}
	
	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}
	
	@JsonProperty("indicators")
	public List<DatasetIndicatorDetails> getIndicators() {
		return indicators;
	}
	
	public void setIndicators(List<DatasetIndicatorDetails> indicators) {
		this.indicators = indicators;
	}
	
	@JsonProperty("reportId")
	public String getReportID() {
		return reportID;
	}
	
	public void setReportID(String reportID) {
		this.reportID = reportID;
	}
	
}
