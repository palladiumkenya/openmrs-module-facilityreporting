package org.openmrs.module.facilityreporting.page.controller;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReportSingleDatasetEntryFormPageController {
	
	public void controller(@RequestParam(value = "returnUrl") String returnUrl,
	        @RequestParam("reportId") FacilityReport report, @RequestParam("datasetId") FacilityReportDataset dataset,
	        PageModel model) {
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("dataset", dataset);
		model.addAttribute("report", report);
		
	}
	
}
