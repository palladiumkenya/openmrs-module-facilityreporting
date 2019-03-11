package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ViewReportDataPageController {
	
	public void controller(@RequestParam(value = "returnUrl") String returnUrl,
	        @RequestParam("reportId") FacilityReport report, @RequestParam("datasetId") FacilityReportDataset dataset,
	        @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, PageModel model) {
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("dataset", dataset);
		model.addAttribute("report", report);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
	}
	
}
