package org.openmrs.module.facilityreporting.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;

import java.util.ArrayList;
import java.util.List;

public class FacilityReportListPageController {
    protected static final Log log = LogFactory.getLog(FacilityReportListPageController.class);

    public void controller(@SpringBean KenyaUiUtils kenyaUi, UiUtils ui, PageModel model) {

        FacilityreportingService reportingService = Context.getService(FacilityreportingService.class);
        List<FacilityReport> reportList = reportingService.getFacilityReportObj();

        model.put("reports", reportFormatter(reportList));
    }

    private List<SimpleObject> reportFormatter( List<FacilityReport> reports) {
        List<SimpleObject> objects = new ArrayList<SimpleObject>();

        for(FacilityReport rpt : reports) {

            SimpleObject reportObject = SimpleObject.create(
                    "id", rpt.getId(),
                    "name", rpt.getName(),
                    "description", rpt.getDescription(),
                    "mapping", rpt.getMapping()
            );
            objects.add(reportObject);

        }

        return objects;
    }
}
