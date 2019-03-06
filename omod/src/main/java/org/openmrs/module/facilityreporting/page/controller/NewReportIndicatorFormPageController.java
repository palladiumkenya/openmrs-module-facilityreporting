package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class NewReportIndicatorFormPageController {
	
	public void controller(@RequestParam("returnUrl") String url,
						   @RequestParam(value = "indicator", required = false) FacilityReportIndicator indicator,
						   @RequestParam(value = "dataset") FacilityReportDataset dataset,
						   PageModel model) {
		model.addAttribute("returnUrl", url);
		model.addAttribute("indicator", indicator);
		model.addAttribute("dataset", dataset);

	}
}
