package org.openmrs.module.facilityreporting.api.restUtil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.ArrayUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacilityReporting {
	
	static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public static List<ReportDatasetValueEntryMapper> getReportDataForPeriod(Integer reportId, String startDate,
	        String endDate) throws ParseException {
		FacilityreportingService service = Context.getService(FacilityreportingService.class);
		
		FacilityReport report = service.getReportById(reportId);
		Date from = DATE_FORMAT.parse(startDate);
		Date to = DATE_FORMAT.parse(endDate);
		
		List<FacilityReportData> rData = service.getReportData(report, from, to);
		if (rData.isEmpty())
			return new ArrayList<ReportDatasetValueEntryMapper>();
		
		List<ReportDatasetValueEntryMapper> mappedResult = getListOfEntriesForReport(rData);
		
		return mappedResult;
		
	}
	
	public static JsonNodeFactory getJsonNodeFactory() {
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		return factory;
	}
	
	public static ArrayNode constructResponseJson(List<FacilityReportData> result) {
		ArrayNode items = getJsonNodeFactory().arrayNode();
		
		for (FacilityReportData data : result) {
			items.add(data.getValue());
		}
		return items;
	}
	
	public static List<ReportDatasetValueEntryMapper> getListOfEntriesForReport(List<FacilityReportData> result) {
		List<ReportDatasetValueEntryMapper> res = new ArrayList<ReportDatasetValueEntryMapper>();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		for (FacilityReportData data : result) {
			ReportDatasetValueEntryMapper dsVal = null;
			
			try {
				dsVal = mapper.readValue(data.getValue(), ReportDatasetValueEntryMapper.class);
				res.add(dsVal);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
