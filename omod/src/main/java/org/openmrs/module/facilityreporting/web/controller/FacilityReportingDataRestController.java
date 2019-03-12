/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.facilityreporting.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.facilityreporting.api.jsonvalidator.utils.RestRequestUtils;
import org.openmrs.module.facilityreporting.api.restUtil.FacilityReporting;
import org.openmrs.module.facilityreporting.api.restUtil.RestReportRequestMapper;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

/**
 * The main controller for getting data from facility reporting tables
 */
@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/facilityreporting")
public class FacilityReportingDataRestController extends BaseRestController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * gets SHR based on patient/client internal ID
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getreportdata")
	@ResponseBody
	public Object getReportForPeriod(HttpServletRequest request) {
		Integer reportID = null;
		String startDate = null;
		String endDate = null;
		String adxOrgUnit = null;
		String adxReportPeriod = null;
		String requestBody = null;
		RestReportRequestMapper thisRequest = null;
		try {
			requestBody = RestRequestUtils.fetchRequestBody(request.getReader());//request.getParameter("encryptedSHR") != null? request.getParameter("encryptedSHR"): null;
		}
		catch (IOException e) {
			return new SimpleObject().add("ServerResponse", "Error extracting request body");
		}
		try {
			thisRequest = new ObjectMapper().readValue(requestBody, RestReportRequestMapper.class);
		}
		catch (IOException e) {
			e.printStackTrace();
			return new SimpleObject().add("ServerResponse", "Error reading patient id: " + requestBody);
		}
		reportID = Integer.parseInt(thisRequest.getReportID());
		startDate = thisRequest.getStartDate();
		endDate = thisRequest.getEndDate();
		
		if (reportID != 0) {
			FacilityReporting fData = new FacilityReporting();
			try {
				return fData.getReportDataForPeriod(reportID, startDate, endDate).toString();
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		return new SimpleObject().add("Server Response", "There was a problem handling report" + reportID + ", " + startDate
		        + ", " + endDate);
	}
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController#getNamespace()
	 */
	
	@Override
	public String getNamespace() {
		return "v1/kenyaemrfacilityreporting";
	}
	
}
