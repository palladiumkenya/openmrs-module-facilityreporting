package org.openmrs.module.facilityreporting.fragment.controller;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class FacilityDataSetsFragmentController {
	
	public void controller(FragmentConfiguration config, FragmentModel model) throws Exception {
		
		ArrayNode datim = getJsonNodeFactory().arrayNode();
		ObjectNode report1 = getJsonNodeFactory().objectNode();
		report1.put("reportName", "Datim_v1");
		report1.put("description", "Something here");
		//report1.put("description", "Something here");
		ObjectNode rpt1Dataset1 = getJsonNodeFactory().objectNode();
		ArrayNode rpt1DatasetIndicators = getJsonNodeFactory().arrayNode();
		ArrayNode dataset = getJsonNodeFactory().arrayNode();
		
		rpt1Dataset1.put("datasetName", "dataset_1");
		//rpt1Dataset1.put("datasetName", "dataset_1");
		
		// indicators
		ObjectNode ind1 = getJsonNodeFactory().objectNode();
		ind1.put("name", "females_pregnant");
		ind1.put("id", 200);
		
		ObjectNode ind2 = getJsonNodeFactory().objectNode();
		ind2.put("name", "females_delivered");
		ind2.put("id", 300);
		
		ObjectNode ind3 = getJsonNodeFactory().objectNode();
		ind3.put("name", "females_on_art");
		ind3.put("id", 400);
		rpt1DatasetIndicators.add(ind1);
		rpt1DatasetIndicators.add(ind2);
		rpt1DatasetIndicators.add(ind3);
		dataset.add(rpt1Dataset1);
		
		rpt1Dataset1.put("indicators", rpt1DatasetIndicators);
		report1.put("dataset", dataset);
		
		List<JsonNode> reportList = new ArrayList<JsonNode>();
		reportList.add(report1);
		
		//datim.add(report1);
		
		ObjectNode rpt1Dataset2 = getJsonNodeFactory().objectNode();
		
		// System.out.println("============" + reportList);
		model.put("datim", reportList);
		
	}
	
	private JsonNodeFactory getJsonNodeFactory() {
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		return factory;
	}
	
	private void saveDataSetReport(@RequestParam("payload") String payload) {
		System.out.println("================== this is the payload" + payload);
		
	}
	
}
