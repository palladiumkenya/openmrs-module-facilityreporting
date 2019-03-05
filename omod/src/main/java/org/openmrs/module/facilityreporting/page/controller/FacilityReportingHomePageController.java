package org.openmrs.module.facilityreporting.page.controller;

import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for facility reporting home page
 */
//@AppPage("facilityReporting.home")
public class FacilityReportingHomePageController {
	
	public void controller(UiUtils ui, PageModel model) {
		
		List<SimpleObject> reports = new ArrayList<SimpleObject>();
		reports.add(SimpleObject.create("name", "Datim", "description", "Monthly reporting to DHIS2"));
		model.put("reports", reports);
	}
}
