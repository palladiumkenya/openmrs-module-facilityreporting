package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.FacilityReportingConstant;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for facility reporting home page
 */
@AppPage(FacilityReportingConstant.APP_FALICITY_REPORTING)
public class ReportDatasetsListPageController {
	
	public void controller(UiUtils ui, @RequestParam("reportId") FacilityReport report,
	        @RequestParam(value = "returnUrl") String returnUrl, PageModel model) {
		
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		
		List<FacilityReportDataset> datasetConfigurations = service.getDatasetsByReport(report);
		
		model.put("datasets", reportFormatter(datasetConfigurations));
		model.put("report", report);
		model.put("returnUrl", returnUrl);
	}
	
	private List<SimpleObject> reportFormatter(List<FacilityReportDataset> definitions) {
		List<SimpleObject> objects = new ArrayList<SimpleObject>();
		
		for (FacilityReportDataset ds : definitions) {
			
			SimpleObject reportObject = SimpleObject.create("id", ds.getId(), "name", ds.getName(), "description",
			    ds.getDescription(), "mapping", ds.getMapping());
			objects.add(reportObject);
			
		}
		
		return objects;
	}
}
