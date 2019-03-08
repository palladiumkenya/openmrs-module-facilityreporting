package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class FacilityDatasetEntryFormPageController {
	
	public void controller(@RequestParam(value = "returnUrl") String returnUrl,
	        @RequestParam("reportId") FacilityReport report, PageModel model) {
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("report", report);
		
	}
	
}
