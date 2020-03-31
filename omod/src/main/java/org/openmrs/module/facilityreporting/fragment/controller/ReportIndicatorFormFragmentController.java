package org.openmrs.module.facilityreporting.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.openmrs.module.kenyaui.form.AbstractWebForm;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.annotation.MethodParam;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

public class ReportIndicatorFormFragmentController {
	
	public void controller(@FragmentParam(value = "id", required = false) FacilityReportIndicator facilityReportIndicator,
	        @RequestParam(value = "returnUrl") String returnUrl,
	        @RequestParam(value = "datasetId") FacilityReportDataset dataset, PageModel model) {

		FacilityReportIndicator exists = facilityReportIndicator != null ? facilityReportIndicator : null;
		model.addAttribute("command", newFacilityReportIndicatorForm(exists, dataset));
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("dataset", dataset);
		
	}
	
	public SimpleObject saveReportIndicatorForm(
	        @MethodParam("newFacilityReportIndicatorForm") @BindParams FacilityReportIndicatorForm form, UiUtils ui) {
		ui.validate(form, form, null);
		FacilityReportIndicator indicator = form.save();
		return SimpleObject.create("indicatorId", indicator.getId());
	}
	
	public FacilityReportIndicatorForm newFacilityReportIndicatorForm(
	        @RequestParam(value = "id", required = false) FacilityReportIndicator reportIndicator,
	        @RequestParam(value = "datasetId", required = false) FacilityReportDataset dataset) {
		if (reportIndicator != null) {
			return new FacilityReportIndicatorForm(reportIndicator, dataset);
		} else {
			return new FacilityReportIndicatorForm(dataset);
		}
	}
	
	public class FacilityReportIndicatorForm extends AbstractWebForm {
		
		private FacilityReportIndicator original;
		
		private String name;
		
		private String description;
		
		private String mapping;
		
		private FacilityReportDataset dataset;
		
		public FacilityReportIndicatorForm() {
		}
		
		public FacilityReportIndicatorForm(FacilityReportDataset dataset) {
			this.dataset = dataset;
		}
		
		public FacilityReportIndicatorForm(FacilityReportIndicator reportIndicator, FacilityReportDataset dataset) {
			this.original = reportIndicator;
			this.name = reportIndicator.getName();
			this.description = reportIndicator.getDescription();
			this.mapping = reportIndicator.getMapping();
			this.dataset = dataset;
			
		}
		
		public FacilityReportIndicator save() {
			FacilityReportIndicator toSave;
			if (original != null) {
				
				toSave = original;
			} else {
				toSave = new FacilityReportIndicator();
			}
			toSave.setName(name);
			toSave.setDescription(description);
			toSave.setMapping(mapping);
			toSave.setDataset(dataset);
			FacilityReportIndicator fIndicator = Context.getService(FacilityreportingService.class).saveOrUpdateIndicator(
			    toSave);
			return fIndicator;
			
		}
		
		@Override
		public void validate(Object o, Errors errors) {
			require(errors, "name");
			require(errors, "description");
			require(errors, "mapping");
			
		}
		
		public FacilityReportIndicator getOriginal() {
			return original;
		}
		
		public void setOriginal(FacilityReportIndicator original) {
			this.original = original;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getMapping() {
			return mapping;
		}
		
		public void setMapping(String mapping) {
			this.mapping = mapping;
		}
		
		public FacilityReportDataset getDataset() {
			return dataset;
		}
		
		public void setDataset(FacilityReportDataset dataset) {
			this.dataset = dataset;
		}
	}
	
}
