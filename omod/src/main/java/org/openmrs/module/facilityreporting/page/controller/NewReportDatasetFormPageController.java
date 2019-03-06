package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class NewReportDatasetFormPageController {
	
	public void controller(@RequestParam("returnUrl") String url, PageModel model) {
		model.addAttribute("returnUrl", url);
		
	}
}
