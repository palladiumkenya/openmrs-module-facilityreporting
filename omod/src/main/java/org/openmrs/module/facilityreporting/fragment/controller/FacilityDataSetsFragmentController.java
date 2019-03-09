package org.openmrs.module.facilityreporting.fragment.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FacilityDataSetsFragmentController {
	
	public void controller(FragmentConfiguration config, FragmentModel model, @RequestParam("reportId") FacilityReport report)
	        throws Exception {
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		
		List<FacilityReportDataset> datasetConfigurations = service.getDatasetsByReport(report);
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> objDatasets = new ArrayList<JsonNode>();
		for (FacilityReportDataset dt : datasetConfigurations) {
			JsonNode childNode = mapper.createObjectNode();
			((ObjectNode) childNode).put("dataset_id", dt.getId());
			((ObjectNode) childNode).put("datasetName", dt.getName());
			((ObjectNode) childNode).put("description", dt.getDescription());
			((ObjectNode) childNode).put("mapping", dt.getMapping());
			List<FacilityReportIndicator> reportConfigurations = service.getIndicatorsByDataset(dt);
			List<JsonNode> indicators = reportFormatterIndicators(reportConfigurations);
			((ObjectNode) childNode).putArray("indicators").addAll(indicators);
			
			objDatasets.add(childNode);
		}
		model.put("datasetLists", objDatasets);
		
	}
	
	public void saveDataSetReport(@RequestParam("payload") String payload, @RequestParam("reportId") FacilityReport report,
	        @RequestParam("datasetId") FacilityReportDataset dataset) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("================== this is the payload" + payload);
		FacilityReportData data = new FacilityReportData();
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			JsonNode jsonNode = mapper.readTree(payload);
			JsonNode facilityData = jsonNode.get("dataSetResults");
			for (int i = 0; i < facilityData.size(); i++) {
				JsonNode datasetJson = facilityData.get(i);
				//JsonNode dasetId = datasetJson.get("datasetId");
				JsonNode childNode1 = mapper.createObjectNode();
				((ObjectNode) childNode1).put("dataset", datasetJson);
				data.setReport(report);
				data.setDataset(dataset);
				data.setValue(childNode1.toString());
				data.setStartDate(df.parse("2019-01-01"));
				data.setEndDate(df.parse("2019-01-31"));
				service.saveOrUpdateReportData(data);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<SimpleObject> reportFormatter(List<FacilityReportDataset> definitions) {
		List<SimpleObject> objects = new ArrayList<SimpleObject>();
		
		for (FacilityReportDataset ds : definitions) {
			
			SimpleObject reportObject = SimpleObject.create("id", ds.getId(), "name", ds.getName(), "description",
			    ds.getDescription(), "mapping", ds.getMapping());
			objects.add(reportObject);
			
		}
		
		return objects;
	}
	
	private List<JsonNode> reportFormatterIndicators(List<FacilityReportIndicator> definitions) {
		List<JsonNode> objects = new ArrayList<JsonNode>();
		ObjectMapper mapper = new ObjectMapper();
		
		for (FacilityReportIndicator ds : definitions) {
			JsonNode childNode1 = mapper.createObjectNode();
			((ObjectNode) childNode1).put("id", ds.getId());
			((ObjectNode) childNode1).put("name", ds.getName());
			((ObjectNode) childNode1).put("description", ds.getDescription());
			((ObjectNode) childNode1).put("mapping", ds.getMapping());
			objects.add(childNode1);
			
		}
		
		return objects;
	}
	
}
