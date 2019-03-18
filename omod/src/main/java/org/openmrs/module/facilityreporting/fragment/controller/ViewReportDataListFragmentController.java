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
	
	FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public void controller(FragmentConfiguration config, FragmentModel model,
	        @RequestParam("datasetId") FacilityReportDataset dataset, @RequestParam("reportId") FacilityReport report,
	        @RequestParam(value = "returnUrl") String returnUrl, @RequestParam("dataId") FacilityReportData data)
	        throws Exception {
		
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("report", report);
		model.addAttribute("data", data);
		model.addAttribute("dataset", dataset);
		
		List<JsonNode> objDatasets = new ArrayList<JsonNode>();
		FacilityReportData dt = data;
		JsonNode jsonNode = mapper.readValue(dt.getValue(), JsonNode.class);
		JsonNode childNode = mapper.createObjectNode();
		((ObjectNode) childNode).put("dataNode", jsonNode);
		
		objDatasets.add(childNode);
		model.put("dataNodes", objDatasets);
		
	}
	
}
