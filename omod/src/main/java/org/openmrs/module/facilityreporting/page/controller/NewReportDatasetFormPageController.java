package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class NewReportDatasetFormPageController {
	
	public void controller(@RequestParam("returnUrl") String url, @RequestParam("reportId") FacilityReport report,
	        PageModel model) {
		model.addAttribute("returnUrl", url);
		model.addAttribute("report", report);
		
	}
}
