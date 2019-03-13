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

public class EditDataSetsViewFragmentController {
	
	public void controller(FragmentConfiguration config, FragmentModel model,
	        @RequestParam(value = "returnUrl") String returnUrl, @RequestParam("dataId") FacilityReportData data)
	        throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		model.addAttribute("returnUrl", returnUrl);
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<JsonNode> editDatasetPayload = new ArrayList<JsonNode>();
		FacilityReportData dt = data;
		JsonNode jsonNode = mapper.readValue(dt.getValue(), JsonNode.class);
		JsonNode childNode = mapper.createObjectNode();
		((ObjectNode) childNode).put("dataNodeValue", jsonNode);
		
		editDatasetPayload.add(childNode);
		
		model.put("editDatasetPayload", editDatasetPayload);
		model.put("startDate", df.format(data.getStartDate()));
		model.put("endDate", df.format(data.getEndDate()));
		model.addAttribute("reportdata", data);
		
	}
	
	public void updateDataSet(@RequestParam("payload") String payload, @RequestParam("dataId") FacilityReportData data)
	        throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			JsonNode jsonNode = mapper.readTree(payload);
			JsonNode facilityData = jsonNode.get("dataSetResults");
			for (int i = 0; i < facilityData.size(); i++) {
				JsonNode datasetJson = facilityData.get(i);
				data.setValue(datasetJson.toString());
				
				service.saveOrUpdateReportData(data);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
