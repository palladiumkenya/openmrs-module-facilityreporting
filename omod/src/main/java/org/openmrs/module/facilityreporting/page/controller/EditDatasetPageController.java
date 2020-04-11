package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.FacilityReportingConstant;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

@AppPage(FacilityReportingConstant.APP_FALICITY_REPORTING)
public class EditDatasetPageController {
	
	public void controller(@RequestParam(value = "returnUrl") String returnUrl,
	        @RequestParam("datasetId") FacilityReportDataset dataset, @RequestParam("reportId") FacilityReport report,
	        @RequestParam("dataId") FacilityReportData data, PageModel model) {
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("report", report);
		model.addAttribute("dataset", dataset);
		model.addAttribute("reportdata", data);
	}
	
}
