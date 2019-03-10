package org.openmrs.module.facilityreporting.fragment.controller;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewReportDataListFragmentController {
	
	public void controller(FragmentConfiguration config, FragmentModel model,
	        @RequestParam(value = "returnUrl") String returnUrl, @RequestParam("reportId") FacilityReport report,
	        @RequestParam("datasetId") Integer dataset) throws Exception {
		
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("dataset", dataset);
		model.addAttribute("report", report);
		
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		ObjectMapper mapper = new ObjectMapper();
		List<JsonNode> objDatasets = new ArrayList<JsonNode>();
		service.getDatasetById(dataset);
		JsonNode childNode = mapper.createObjectNode();
		((ObjectNode) childNode).put("dataset_id", dataset);
		((ObjectNode) childNode).put("datasetName", service.getDatasetById(dataset).getName());
		((ObjectNode) childNode).put("description", service.getDatasetById(dataset).getDescription());
		((ObjectNode) childNode).put("mapping", service.getDatasetById(dataset).getMapping());
		List<FacilityReportIndicator> reportConfigurations = service.getIndicatorsByDataset(service.getDatasetById(dataset));
		List<JsonNode> indicators = reportFormatterIndicators(reportConfigurations);
		((ObjectNode) childNode).putArray("indicators").addAll(indicators);
		
		objDatasets.add(childNode);
		model.put("singleDataset", objDatasets);
		
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
