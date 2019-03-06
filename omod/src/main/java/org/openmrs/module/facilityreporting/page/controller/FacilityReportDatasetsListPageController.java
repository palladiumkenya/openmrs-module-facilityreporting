package org.openmrs.module.facilityreporting.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class FacilityReportDatasetsListPageController {
    protected static final Log log = LogFactory.getLog(FacilityReportDatasetsListPageController.class);

    public void controller(@SpringBean KenyaUiUtils kenyaUi,
                           @RequestParam(value = "report") FacilityReport report,
                           UiUtils ui, PageModel model) {

        FacilityreportingService reportingService = Context.getService(FacilityreportingService.class);
        List<FacilityReportDataset> datasetList = reportingService.getFacilityReportDatasetObj(report);

        model.put("datasets", reportFormatter(kenyaUi, datasetList));
        model.put("report", report);

    }

    private List<SimpleObject> reportFormatter(KenyaUiUtils kenyaUi, List<FacilityReportDataset> datasets) {
        List<SimpleObject> objects = new ArrayList<SimpleObject>();

        for(FacilityReportDataset ds : datasets) {

            SimpleObject reportObject = SimpleObject.create(
                    "id", ds.getId(),
                    "name", ds.getName(),
                    "report",ds.getReport(),
                    "description", ds.getDescription(),
                    "mapping", ds.getMapping()
            );
            objects.add(reportObject);

        }

        return objects;
    }
}
