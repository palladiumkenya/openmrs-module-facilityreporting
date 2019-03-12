package org.openmrs.module.facilityreporting.fragment.controller;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowDataSetsFragmentController {
	
	public void controller(FragmentConfiguration config, FragmentModel model,
	        @RequestParam(value = "returnUrl") String returnUrl, @RequestParam("reportId") FacilityReport report,
	        @RequestParam("datasetId") Integer dataset) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("dataset", dataset);
		model.addAttribute("report", report);
		
		//  model.addAttribute("startDate", startDate);
		//   model.addAttribute("endDate", endDate);
		
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		ObjectMapper mapper = new ObjectMapper();
		
		List<FacilityReportData> reportData = service.getReportData(report, df.parse("2019-03-01"), df.parse("2019-03-07"));
		List<JsonNode> editDatasetPayload = new ArrayList<JsonNode>();
		for (FacilityReportData dt : reportData) {
			JsonNode jsonNode = mapper.readValue(dt.getValue(), JsonNode.class);
			JsonNode childNode = mapper.createObjectNode();
			((ObjectNode) childNode).put("dataNodeValue", jsonNode);
			
			editDatasetPayload.add(childNode);
		}
		model.put("editDatasetPayload", editDatasetPayload);
		
	}
	
}
