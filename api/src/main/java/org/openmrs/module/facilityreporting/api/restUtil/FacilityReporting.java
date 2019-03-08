package org.openmrs.module.facilityreporting.api.restUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.ArrayUtils;
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
	
	public ArrayNode getReportDataForPeriod(Integer reportId, String startDate, String endDate, String adxOrgUnit,
	        String adxReportPeriod) throws ParseException {
		FacilityreportingService service = Context.getService(FacilityreportingService.class);
		
		FacilityReport report = service.getReportById(reportId);
		Date from = DATE_FORMAT.parse(startDate);
		Date to = DATE_FORMAT.parse(endDate);
		
		List<FacilityReportData> rData = service.getReportData(report, from, to);
		ArrayNode result = constructResponseJson(rData);
		
		/*ObjectNode node = getJsonNodeFactory().objectNode();
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
		*/
		return result;
		
	}
	
	public JsonNodeFactory getJsonNodeFactory() {
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		return factory;
	}
	
	private ArrayNode constructResponseJson(List<FacilityReportData> result) {
		ArrayNode items = getJsonNodeFactory().arrayNode();
		
		for (FacilityReportData data : result) {
			items.add(data.getValue());
		}
		return items;
	}
	
	public static String createDummyDatasetEntry() {
		String val = "[{\n" + "  \"datasetName\": \"Methadone Assisted Therapy\",\n" + "  \"datasetId\": 3,\n"
		        + "  \"indicators\": [\n" + "    {\n" + "      \"name\": \"HV06-03\",\n" + "      \"id\": 200,\n"
		        + "      \"value\": 36\n" + "    },\n" + "    {\n" + "      \"name\": \"HV06-01\",\n"
		        + "      \"id\": 300,\n" + "      \"value\": 30\n" + "    },\n" + "    {\n"
		        + "      \"name\": \"HV06-04\",\n" + "      \"id\": 400,\n" + "      \"value\": \"\"\n" + "    },\n"
		        + "    {\n" + "      \"name\": \"HV06-02\",\n" + "      \"id\": 500,\n" + "      \"value\": \"49\"\n"
		        + "    }\n" + "  ]\n" + "}]";
		return val;
	}
}
