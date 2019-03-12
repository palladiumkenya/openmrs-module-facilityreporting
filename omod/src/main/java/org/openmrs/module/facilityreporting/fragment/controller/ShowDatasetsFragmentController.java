package org.openmrs.module.facilityreporting.fragment.controller;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowDatasetsFragmentController {
	
	public void controller(FragmentConfiguration config, FragmentModel model,
	        @RequestParam(value = "returnUrl") String returnUrl, @RequestParam("reportId") FacilityReport report,
	        @RequestParam("datasetId") FacilityReportDataset dataset) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("dataset", dataset);
		model.addAttribute("report", report);
		
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		ObjectMapper mapper = new ObjectMapper();
		List<FacilityReportData> reportData = service.getReportData(report, dataset);
		List<SimpleObject> datasetHstoryPayload = new ArrayList<SimpleObject>();
		for (FacilityReportData dt : reportData) {
			//JsonNode jsonNode = mapper.readValue(dt.getValue(), JsonNode.class);
			SimpleObject o = SimpleObject.create("startDate", df.format(dt.getStartDate()), "endDate",
			    df.format(dt.getEndDate()), "dataId", dt.getId());
			
			//((ObjectNode) childNode).put("dataNodeValue", jsonNode);
			
			datasetHstoryPayload.add(o);
		}
		model.put("datasetHstoryPayload", datasetHstoryPayload);
		
	}
	
}
