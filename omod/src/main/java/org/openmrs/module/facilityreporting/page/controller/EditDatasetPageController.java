package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class EditDatasetPageController {
	
	public void controller(@RequestParam(value = "returnUrl") String returnUrl,
	        @RequestParam("dataId") FacilityReportData data, PageModel model) {
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("reportdata", data);
	}
	
}
