package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
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
@AppPage("facilityReporting.home")
public class FacilityReportingHomePageController {
	
	public void controller(UiUtils ui, PageModel model) {
		
		FacilityreportingService service = org.openmrs.api.context.Context.getService(FacilityreportingService.class);
		
		List<FacilityReport> reportConfigurations = service.getAllReportDefinitions();
		model.put("reports", reportFormatter(reportConfigurations));

	}
	
	private List<SimpleObject> reportFormatter(List<FacilityReport> definitions) {
		List<SimpleObject> objects = new ArrayList<SimpleObject>();
		
		for (FacilityReport ds : definitions) {
			
			SimpleObject reportObject = SimpleObject.create("id", ds.getId(), "name", ds.getName(), "description",
			    ds.getDescription(), "mapping", ds.getMapping());
			objects.add(reportObject);
			
		}
		
		return objects;
	}
}
