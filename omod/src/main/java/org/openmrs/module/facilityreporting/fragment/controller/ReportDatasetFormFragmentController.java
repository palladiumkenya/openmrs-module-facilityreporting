package org.openmrs.module.facilityreporting.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.kenyaui.form.AbstractWebForm;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.annotation.FragmentParam;
import org.openmrs.ui.framework.annotation.MethodParam;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

public class ReportDatasetFormFragmentController {
	
	public void controller(@FragmentParam(value = "id", required = false) FacilityReportDataset facilityReportDataset,
	        @RequestParam(value = "returnUrl") String returnUrl, @RequestParam(value = "reportId") FacilityReport report,
	        PageModel model) {
		
		FacilityReportDataset exists = facilityReportDataset != null ? facilityReportDataset : null;
		model.addAttribute("command", newFacilityReportDatasetForm(exists, report));
		model.addAttribute("returnUrl", returnUrl);
		model.addAttribute("report", report);
		
	}
	
	public SimpleObject saveReportDatasetForm(
	        @MethodParam("newFacilityReportDatasetForm") @BindParams FacilityReportDatasetForm form, UiUtils ui) {
		ui.validate(form, form, null);
		FacilityReportDataset report = form.save();
		return SimpleObject.create("reportId", report.getId());
	}
	
	public FacilityReportDatasetForm newFacilityReportDatasetForm(
	        @RequestParam(value = "id", required = false) FacilityReportDataset facilityReportDataset,
	        @RequestParam(value = "reportId", required = false) FacilityReport report) {
		if (facilityReportDataset != null) {
			
			return new FacilityReportDatasetForm(facilityReportDataset, report);
		} else {
			return new FacilityReportDatasetForm(report);
		}
	}
	
	public class FacilityReportDatasetForm extends AbstractWebForm {
		
		private FacilityReportDataset original;
		
		private String name;
		
		private String description;
		
		private String mapping;
		
		private FacilityReport report;
		
		public FacilityReportDatasetForm() {
		}
		
		public FacilityReportDatasetForm(FacilityReport report) {
			this.report = report;
		}
		
		public FacilityReportDatasetForm(FacilityReportDataset facilityReportDataset, FacilityReport report) {
			
			this.original = facilityReportDataset;
			this.name = facilityReportDataset.getName();
			this.description = facilityReportDataset.getDescription();
			this.mapping = facilityReportDataset.getMapping();
			this.report = report;
			
		}
		
		public FacilityReportDataset save() {
			FacilityReportDataset toSave;
			if (original != null) {
				
				toSave = original;
			} else {
				toSave = new FacilityReportDataset();
			}
			toSave.setName(name);
			toSave.setDescription(description);
			toSave.setMapping(mapping);
			toSave.setReport(report);
			FacilityReportDataset fReportDataset = Context.getService(FacilityreportingService.class).saveOrUpdateDataset(
			    toSave);
			return fReportDataset;
			
		}
		
		@Override
		public void validate(Object o, Errors errors) {
			require(errors, "name");
			require(errors, "description");
			require(errors, "mapping");
			
		}
		
		public FacilityReportDataset getOriginal() {
			return original;
		}
		
		public void setOriginal(FacilityReportDataset original) {
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
		
		public FacilityReport getReport() {
			return report;
		}
		
		public void setReport(FacilityReport report) {
			this.report = report;
		}
	}
	
}
