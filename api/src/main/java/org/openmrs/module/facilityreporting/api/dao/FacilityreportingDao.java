/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.facilityreporting.api.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.APIException;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.facilityreporting.Item;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("facilityreporting.FacilityreportingDao")
public class FacilityreportingDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Item getItemByUuid(String uuid) {
		return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	public Item saveItem(Item item) {
		getSession().saveOrUpdate(item);
		return item;
	}
	
	/**
	 * saves or updates a facility report object
	 * 
	 * @param report
	 * @return
	 * @throws APIException
	 */
	public FacilityReport saveOrUpdateReport(FacilityReport report) {
		getSession().saveOrUpdate(report);
		return report;
	}
	
	/**
	 * gets facility report object by uuid
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	public FacilityReport getReportById(Integer id) {
		return (FacilityReport) getSession().createCriteria(FacilityReport.class).add(Restrictions.eq("id", id))
		        .uniqueResult();
	}
	
	/**
	 * gets facility report object by uuid
	 * 
	 * @param reportUuid
	 * @return
	 * @throws APIException
	 */
	public FacilityReport getReportByUuid(String reportUuid) {
		return (FacilityReport) getSession().createCriteria(FacilityReport.class).add(Restrictions.eq("uuid", reportUuid))
		        .uniqueResult();
		
	}
	
	public List<FacilityReport> getAllReportDefinitions() {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(FacilityReport.class);
		return criteria.list();
	}
	
	/**
	 * saves or updates dataset object
	 * 
	 * @param dataset
	 * @return
	 * @throws APIException
	 */
	public FacilityReportDataset saveOrUpdateDataset(FacilityReportDataset dataset) {
		getSession().saveOrUpdate(dataset);
		return dataset;
	}
	
	/**
	 * returns FacilityReportDataset whose uuid is provided
	 * 
	 * @param datasetUuid
	 * @return
	 * @throws APIException
	 */
	public FacilityReportDataset getDatasetByUuid(String datasetUuid) {
		return (FacilityReportDataset) getSession().createCriteria(FacilityReportDataset.class)
		        .add(Restrictions.eq("uuid", datasetUuid)).uniqueResult();
		
	}
	
	public FacilityReportDataset getDatasetById(Integer id) {
		return (FacilityReportDataset) getSession().createCriteria(FacilityReportDataset.class)
		        .add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	public List<FacilityReportDataset> getDatasetsByReport(FacilityReport report) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(FacilityReportDataset.class);
		criteria.add(Restrictions.eq("report", report));
		return criteria.list();
	}
	
	/**
	 * saves or updates indicator object
	 * 
	 * @param indicator
	 * @return
	 * @throws APIException
	 */
	public FacilityReportIndicator saveOrUpdateIndicator(FacilityReportIndicator indicator) {
		getSession().saveOrUpdate(indicator);
		return indicator;
	}
	
	/**
	 * returns indicator whose uuid is provided
	 * 
	 * @param indicatorUuid
	 * @return
	 * @throws APIException
	 */
	public FacilityReportIndicator getReportIndicatorByUuid(String indicatorUuid) {
		return (FacilityReportIndicator) getSession().createCriteria(FacilityReportIndicator.class)
		        .add(Restrictions.eq("uuid", indicatorUuid)).uniqueResult();
		
	}
	
	/**
	 * saves or updates report data object
	 * 
	 * @param reportData
	 * @return
	 * @throws APIException
	 */
	public FacilityReportData saveOrUpdateReportData(FacilityReportData reportData) {
		getSession().saveOrUpdate(reportData);
		return reportData;
	}
	
	/**
	 * returns report data whose uuid is provided
	 * 
	 * @param dataUuid
	 * @return
	 * @throws APIException
	 */
	public FacilityReportData getReportDataByUuid(String dataUuid) {
		return (FacilityReportData) getSession().createCriteria(FacilityReportData.class)
		        .add(Restrictions.eq("uuid", dataUuid)).uniqueResult();
		
	}
	
	public FacilityReportIndicator getReportIndicatorById(Integer id) {
		return (FacilityReportIndicator) getSession().createCriteria(FacilityReportIndicator.class)
		        .add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	public List<FacilityReportIndicator> getIndicatorsByDataset(FacilityReportDataset dataset) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(FacilityReportIndicator.class);
		criteria.add(Restrictions.eq("dataset", dataset));
		return criteria.list();
	}
	
	public List<FacilityReportData> getReportData(FacilityReport report, Date startDate, Date endDate) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(FacilityReportData.class);
		criteria.add(Restrictions.eq("report", report));
		criteria.add(Restrictions.eq("startDate", startDate));
		criteria.add(Restrictions.eq("endDate", endDate));
		return criteria.list();
	}
	
	public FacilityReportData getReportDataById(Integer integer) {
		return (FacilityReportData) getSession().createCriteria(FacilityReportData.class)
		        .add(Restrictions.eq("id", integer)).uniqueResult();
	}
	
	public List<FacilityReportData> getReportData(FacilityReport report, FacilityReportDataset dataset, Date startDate,
	        Date endDate) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(FacilityReportData.class);
		criteria.add(Restrictions.eq("report", report));
		criteria.add(Restrictions.eq("dataset", dataset));
		criteria.add(Restrictions.eq("startDate", startDate));
		criteria.add(Restrictions.eq("endDate", endDate));
		return criteria.list();
	}
	
	public List<FacilityReportData> getReportData(FacilityReport report, FacilityReportDataset dataset) {
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(FacilityReportData.class);
		criteria.add(Restrictions.eq("report", report));
		criteria.add(Restrictions.eq("dataset", dataset));
		return criteria.list();
	}
	
	public boolean saveOrUpdateReportData(List<FacilityReportData> reportData) {
		if (reportData == null)
			return true;
		if (reportData.size() > 0) {
			for (FacilityReportData d : reportData) {
				saveOrUpdateReportData(d);
			}
		}
		return true;
	}
}
