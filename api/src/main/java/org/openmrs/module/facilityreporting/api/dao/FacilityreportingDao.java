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
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.APIException;
import org.openmrs.module.facilityreporting.api.models.FacilityReport;
import org.openmrs.module.facilityreporting.api.models.FacilityReportData;
import org.openmrs.module.facilityreporting.api.models.FacilityReportDataset;
import org.openmrs.module.facilityreporting.api.models.FacilityReportIndicator;

import java.util.Date;
import java.util.List;

public class FacilityreportingDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * saves or updates a facility report object
	 * 
	 * @param report
	 * @return
	 * @throws APIException
	 */
	public FacilityReport saveOrUpdateReport(FacilityReport report) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(report);
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
		return (FacilityReport) this.sessionFactory.getCurrentSession().createCriteria(FacilityReport.class)
		        .add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	/**
	 * gets facility report object by uuid
	 * 
	 * @param reportUuid
	 * @return
	 * @throws APIException
	 */
	public FacilityReport getReportByUuid(String reportUuid) {
		return (FacilityReport) this.sessionFactory.getCurrentSession().createCriteria(FacilityReport.class)
		        .add(Restrictions.eq("uuid", reportUuid)).uniqueResult();
		
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
		this.sessionFactory.getCurrentSession().saveOrUpdate(dataset);
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
		return (FacilityReportDataset) this.sessionFactory.getCurrentSession().createCriteria(FacilityReportDataset.class)
		        .add(Restrictions.eq("uuid", datasetUuid)).uniqueResult();
		
	}
	
	public FacilityReportDataset getDatasetById(Integer id) {
		return (FacilityReportDataset) this.sessionFactory.getCurrentSession().createCriteria(FacilityReportDataset.class)
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
		this.sessionFactory.getCurrentSession().saveOrUpdate(indicator);
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
		return (FacilityReportIndicator) this.sessionFactory.getCurrentSession()
		        .createCriteria(FacilityReportIndicator.class).add(Restrictions.eq("uuid", indicatorUuid)).uniqueResult();
		
	}
	
	/**
	 * saves or updates report data object
	 * 
	 * @param reportData
	 * @return
	 * @throws APIException
	 */
	public FacilityReportData saveOrUpdateReportData(FacilityReportData reportData) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(reportData);
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
		return (FacilityReportData) this.sessionFactory.getCurrentSession().createCriteria(FacilityReportData.class)
		        .add(Restrictions.eq("uuid", dataUuid)).uniqueResult();
		
	}
	
	public FacilityReportIndicator getReportIndicatorById(Integer id) {
		return (FacilityReportIndicator) this.sessionFactory.getCurrentSession()
		        .createCriteria(FacilityReportIndicator.class).add(Restrictions.eq("id", id)).uniqueResult();
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
		return (FacilityReportData) this.sessionFactory.getCurrentSession().createCriteria(FacilityReportData.class)
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
