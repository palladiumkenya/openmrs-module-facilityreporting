package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class ViewReportDataPageController {
	
	public void controller(@RequestParam(value = "returnUrl") String returnUrl,
	        @RequestParam("reportId") FacilityReport report, @RequestParam("datasetId") FacilityReportDataset dataset,
	        @RequestParam("dataId") FacilityReportData data, PageModel model) {
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("report", report);
		model.addAttribute("reportdata", data);
		model.addAttribute("dataset", dataset);
		
	}
	
}
