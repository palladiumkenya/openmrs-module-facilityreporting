/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.facilityreporting.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.facilityreporting.FacilityreportingConfig;
import org.openmrs.module.facilityreporting.Item;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface FacilityreportingService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Item getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param item
	 * @return
	 * @throws APIException
	 */
	@Authorized(FacilityreportingConfig.MODULE_PRIVILEGE)
	@Transactional
	Item saveItem(Item item) throws APIException;
	
	/**
	 * saves or updates a facility report object
	 * 
	 * @param report
	 * @return
	 * @throws APIException
	 */
	FacilityReport saveOrUpdateReport(FacilityReport report) throws APIException;
	
	/**
	 * gets facility report object by uuid
	 * 
	 * @param reportUuid
	 * @return
	 * @throws APIException
	 */
	FacilityReport getReportByUuid(String reportUuid) throws APIException;
	
	/**
	 * gets facility report object by uuid
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	FacilityReport getReportById(Integer id) throws APIException;
	
	/**
	 * saves or updates dataset object
	 * 
	 * @param dataset
	 * @return
	 * @throws APIException
	 */
	FacilityReportDataset saveOrUpdateDataset(FacilityReportDataset dataset) throws APIException;
	
	/**
	 * returns FacilityReportDataset whose uuid is provided
	 * 
	 * @param datasetUuid
	 * @return
	 * @throws APIException
	 */
	FacilityReportDataset getDatasetByUuid(String datasetUuid) throws APIException;
	
	/**
	 * saves or updates indicator object
	 * 
	 * @param indicator
	 * @return
	 * @throws APIException
	 */
	FacilityReportIndicator saveOrUpdateIndicator(FacilityReportIndicator indicator) throws APIException;
	
	/**
	 * returns indicator whose uuid is provided
	 * 
	 * @param indicatorUuid
	 * @return
	 * @throws APIException
	 */
	FacilityReportIndicator getReportIndicatorByUuid(String indicatorUuid) throws APIException;
	
	/**
	 * saves or updates report data object
	 * 
	 * @param reportData
	 * @return
	 * @throws APIException
	 */
	FacilityReportData saveOrUpdateReportData(FacilityReportData reportData) throws APIException;
	
	/**
	 * returns report data whose uuid is provided
	 * 
	 * @param dataUuid
	 * @return
	 * @throws APIException
	 */
	FacilityReportData getReportDataByUuid(String dataUuid) throws APIException;

	List<FacilityReport> getFacilityReportObj();
	List<FacilityReportDataset> getFacilityReportDatasetObj(FacilityReport facilityReport);
	List<FacilityReportIndicator> getFacilityReportIndicatorObj(FacilityReportDataset facilityReportDataset);

}
