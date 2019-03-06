package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class NewReportDatasetFormPageController {
	
	public void controller(@RequestParam("returnUrl") String url,
						   @RequestParam(value = "report") FacilityReport report,
						   @RequestParam(value = "dataset", required = false) FacilityReportDataset dataset,
						   PageModel model) {
		model.addAttribute("returnUrl", url);
		model.addAttribute("report", report);
		model.addAttribute("dataset", dataset);

	}
}