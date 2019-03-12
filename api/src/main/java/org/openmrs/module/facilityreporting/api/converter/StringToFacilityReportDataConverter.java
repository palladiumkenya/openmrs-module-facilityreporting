package org.openmrs.module.facilityreporting.api.converter;

import org.openmrs.api.context.Context;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.ui.framework.converter.util.ConversionUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFacilityReportDataConverter implements Converter<String, FacilityReportData> {
	
	/**
	 * @see Converter#convert(Object)
	 */
	@Override
	public FacilityReportData convert(String id) {
		FacilityreportingService service = Context.getService(FacilityreportingService.class);
		if (org.apache.commons.lang.StringUtils.isBlank(id)) {
			return null;
		} else if (ConversionUtil.onlyDigits(id)) {
			return service.getReportDataById(Integer.valueOf(id));
		}
		return null;
	}
}
