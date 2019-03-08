package org.openmrs.module.facilityreporting.api.restUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FacilityReporting {
	
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public ObjectNode getReportDataForPeriod(Integer reportId, String startDate, String endDate, String adxOrgUnit,
	        String adxReportPeriod) throws ParseException {
		FacilityreportingService service = Context.getService(FacilityreportingService.class);
		
		System.out.println("Variables as received: reportId:" + reportId + ", startDate: " + startDate + ", endDate: "
		        + endDate);
		FacilityReport report = service.getReportById(reportId);
		Date from = DATE_FORMAT.parse(startDate);
		Date to = DATE_FORMAT.parse(endDate);
		
		List<FacilityReportData> rData = service.getReportData(report, from, to);
		ObjectNode node = getJsonNodeFactory().objectNode();
		node.put("dataset", "test");
		node.put("indicator", "this is the first indicator");
		
		ObjectMapper mapper = new ObjectMapper();
		ReportDatasetValueEntryMapper dsVal = null;
		try {
			dsVal = mapper.readValue(createDummyDatasetEntry(), ReportDatasetValueEntryMapper.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Parsing string with json array: ==================" + dsVal.getIndicators());
		return node;
		
	}
	
	public JsonNodeFactory getJsonNodeFactory() {
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		return factory;
	}
	
	private String createDummyDatasetEntry() {
		String val = "{\n" + "                        \"datasetName\": \"dataset_1\",\n"
		        + "                        \"reportId\":1,\n" + "                        \"datasetId\":1,\n"
		        + "                        \"indicators\": [\n" + "                            {\n"
		        + "                                \"name\": \"females_pregnant\",\n"
		        + "                                \"id\": 200,\n" + "                                \"value\":36\n"
		        + "                            },\n" + "                            {\n"
		        + "                                \"name\": \"females_delivered\",\n"
		        + "                                \"id\": 300,\n" + "                                \"value\":30\n"
		        + "                            },\n" + "                            {\n"
		        + "                                \"name\": \"females_on_art\",\n"
		        + "                                \"id\": 400,\n" + "                                \"value\":\"\"\n"
		        + "                            }\n" + "                        ]\n" + "                    }";
		return val;
	}
}
