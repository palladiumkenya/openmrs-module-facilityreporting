package org.openmrs.module.facilityreporting.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
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
	
	public void controller(@FragmentParam(value = "id", required = false) FacilityReport facilityReport,
	        @RequestParam(value = "returnUrl") String returnUrl, PageModel model) {
		
		FacilityReport exists = facilityReport != null ? facilityReport : null;
		model.addAttribute("command", newFacilityReportForm(exists));
		
	}
	
	public SimpleObject saveReportForm(@MethodParam("newFacilityReportForm") @BindParams FacilityReportForm form, UiUtils ui) {
		ui.validate(form, form, null);
		FacilityReport report = form.save();
		return SimpleObject.create("reportId", report.getId());
	}
	
	public FacilityReportForm newFacilityReportForm(
	        @RequestParam(value = "id", required = false) FacilityReport facilityReport) {
		if (facilityReport != null) {
			
			return new FacilityReportForm(facilityReport);
		} else {
			return new FacilityReportForm();
		}
	}
	
	public class FacilityReportForm extends AbstractWebForm {
		
		private FacilityReport original;
		
		private String name;
		
		private String description;
		
		private String mapping;
		
		public FacilityReportForm() {
		}
		
		public FacilityReportForm(FacilityReport facilityReport) {
			
			this.original = facilityReport;
			this.name = facilityReport.getName();
			this.description = facilityReport.getDescription();
			this.mapping = facilityReport.getMapping();
			
		}
		
		public FacilityReport save() {
			FacilityReport toSave;
			if (original != null) {
				
				toSave = original;
			} else {
				toSave = new FacilityReport();
			}
			toSave.setName(name);
			toSave.setDescription(description);
			toSave.setMapping(mapping);
			FacilityReport fReport = Context.getService(FacilityreportingService.class).saveOrUpdateReport(toSave);
			return fReport;
			
		}
		
		@Override
		public void validate(Object o, Errors errors) {
			require(errors, "name");
			require(errors, "description");
			require(errors, "mapping");
			
		}
		
		public FacilityReport getOriginal() {
			return original;
		}
		
		public void setOriginal(FacilityReport original) {
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
	}
	
}
