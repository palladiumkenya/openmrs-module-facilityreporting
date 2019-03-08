package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class FacilityDatasetEntryFormPageController {
	
	public void controller(@RequestParam(value = "returnUrl") String returnUrl, PageModel model) {
		model.addAttribute("returnUrl", returnUrl);
		
	}
	
}
