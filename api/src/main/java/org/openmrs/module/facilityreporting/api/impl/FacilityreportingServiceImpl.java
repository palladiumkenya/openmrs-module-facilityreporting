/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.facilityreporting.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.facilityreporting.Item;
import org.openmrs.module.facilityreporting.api.FacilityreportingService;
import org.openmrs.module.facilityreporting.api.dao.FacilityreportingDao;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;

import java.util.Date;
import java.util.List;

public class FacilityreportingServiceImpl extends BaseOpenmrsService implements FacilityreportingService {
	
	FacilityreportingDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(FacilityreportingDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}
	
	@Override
	public Item saveItem(Item item) throws APIException {
		if (item.getOwner() == null) {
			item.setOwner(userService.getUser(1));
		}
		
		return dao.saveItem(item);
	}
	
	@Override
	public FacilityReport saveOrUpdateReport(FacilityReport report) throws APIException {
		return dao.saveOrUpdateReport(report);
	}
	
	@Override
	public FacilityReport getReportByUuid(String reportUuid) throws APIException {
		return dao.getReportByUuid(reportUuid);
	}
	
	@Override
	public FacilityReport getReportById(Integer id) throws APIException {
		return dao.getReportById(id);
	}
	
	@Override
	public List<FacilityReport> getAllReportDefinitions() throws APIException {
		return dao.getAllReportDefinitions();
	}
	
	@Override
	public FacilityReportDataset saveOrUpdateDataset(FacilityReportDataset dataset) throws APIException {
		return dao.saveOrUpdateDataset(dataset);
	}
	
	@Override
	public FacilityReportDataset getDatasetByUuid(String datasetUuid) throws APIException {
		return dao.getDatasetByUuid(datasetUuid);
	}
	
	@Override
	public FacilityReportDataset getDatasetById(Integer id) throws APIException {
		return dao.getDatasetById(id);
	}
	
	@Override
	public List<FacilityReportDataset> getDatasetsByReport(FacilityReport report) throws APIException {
		return dao.getDatasetsByReport(report);
	}
	
	@Override
	public FacilityReportIndicator saveOrUpdateIndicator(FacilityReportIndicator indicator) throws APIException {
		return dao.saveOrUpdateIndicator(indicator);
	}
	
	@Override
	public FacilityReportIndicator getReportIndicatorByUuid(String indicatorUuid) throws APIException {
		return dao.getReportIndicatorByUuid(indicatorUuid);
	}
	
	@Override
	public FacilityReportIndicator getReportIndicatorById(Integer id) throws APIException {
		return dao.getReportIndicatorById(id);
	}
	
	@Override
	public List<FacilityReportIndicator> getIndicatorsByDataset(FacilityReportDataset dataset) throws APIException {
		return dao.getIndicatorsByDataset(dataset);
	}
	
	@Override
	public boolean saveOrUpdateReportData(List<FacilityReportData> reportData) throws APIException {
		return dao.saveOrUpdateReportData(reportData);
	}
	
	@Override
	public FacilityReportData saveOrUpdateReportData(FacilityReportData reportData) throws APIException {
		return dao.saveOrUpdateReportData(reportData);
	}
	
	@Override
	public FacilityReportData getReportDataByUuid(String dataUuid) throws APIException {
		return dao.getReportDataByUuid(dataUuid);
	}
	
	@Override
	public List<FacilityReportData> getReportData(FacilityReport report, Date startDate, Date endDate) throws APIException {
		return dao.getReportData(report, startDate, endDate);
	}
	
	@Override
	public List<FacilityReportData> getReportData(FacilityReport report, FacilityReportDataset dataset, Date startDate,
	        Date endDate) throws APIException {
		return dao.getReportData(report, dataset, startDate, endDate);
	}
	
	@Override
	public List<FacilityReportData> getReportData(FacilityReport report, FacilityReportDataset dataset) throws APIException {
		return dao.getReportData(report, dataset);
	}
	
	@Override
	public FacilityReportData getReportDataById(Integer integer) throws APIException {
		return dao.getReportDataById(integer);
	}
}
