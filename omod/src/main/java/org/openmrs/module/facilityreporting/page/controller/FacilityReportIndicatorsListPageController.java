package org.openmrs.module.facilityreporting.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class FacilityReportIndicatorsListPageController {
    protected static final Log log = LogFactory.getLog(FacilityReportIndicatorsListPageController.class);

    public void controller(@SpringBean KenyaUiUtils kenyaUi,
                           @RequestParam(value = "dataset") FacilityReportDataset dataset,
                           UiUtils ui, PageModel model) {

        FacilityreportingService reportingService = Context.getService(FacilityreportingService.class);
        List<FacilityReportIndicator> indicatorList = reportingService.getFacilityReportIndicatorObj(dataset);

        model.put("indicators", reportFormatter(indicatorList));
        model.put("dataset",dataset);
    }

    private List<SimpleObject> reportFormatter( List<FacilityReportIndicator> indicators) {
        List<SimpleObject> objects = new ArrayList<SimpleObject>();

        for(FacilityReportIndicator ind : indicators) {

            SimpleObject reportObject = SimpleObject.create(
                    "id", ind.getId(),
                    "name", ind.getName(),
                    "description", ind.getDescription(),
                    "dataset", ind.getDataset(),
                    "mapping", ind.getMapping()
            );
            objects.add(reportObject);

        }

        return objects;
    }
}
