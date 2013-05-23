package com.hidrocon.webservice.building.service;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.hidrocon.webservice.SoapObject2PojoConverter;
import com.hidrocon.webservice.building.entities.Building;
import com.hidrocon.webservice.route.entities.Route;

public class BuildingService {
 
	 private static final String NAMESPACE = "http://webservice.hidrocon/";
	 private static String URL = "http://192.168.1.35:8080/hidroconweb/hidroconws"; 
	 private static final String METHOD_NAME = "getRouteBuildingViewList";
	 private static final String SOAP_ACTION =  "http://webservice.hidrocon/getRouteBuildingViewList";
	 
	 //private TextView lblResult;
	 
	 public BuildingService() {
		 //this.lblResult = lblResult;
	 }
	 
	@SuppressWarnings("unchecked")
	public ArrayList<Building> doCall(Route route) {
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 

    	Building building = new Building(1,-1,null,null);
    	
    	/*PropertyInfo propInfo=new PropertyInfo();
    	propInfo.setName("building");
    	propInfo.setValue(building);
    	propInfo.setType(building.getClass());*/
    	PropertyInfo propInfo=new PropertyInfo();
    	propInfo.setName("routeId");
    	propInfo.setValue(new Integer(route.getId()));
    	propInfo.setType(PropertyInfo.INTEGER_CLASS);
    	
    	request.addProperty(propInfo);
    	
    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
    	
    	envelope.setOutputSoapObject(request);
    	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
    	androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

    	ArrayList<Building> buildingList = new ArrayList<Building>();
    	try {
    		androidHttpTransport.call(SOAP_ACTION, envelope);
    	    
    		SoapObject resultsRequestSOAP = (SoapObject)envelope.bodyIn;
    	  
    		int size = resultsRequestSOAP.getPropertyCount();
    		for (int i=0; i<size; i++) {
    			SoapObject elementSoapObject = (SoapObject)resultsRequestSOAP.getProperty(i);
				Building buildingAux = SoapObject2PojoConverter.soapObject2Building(elementSoapObject);
				buildingList.add(buildingAux);
				//log("\nid: " + id);
				//log("\nname: " + buildingName);
				//log("\nnextReadingDate: " + nextReadingDate);
    		}
    	 
    	} catch (Exception e) {
    		Log.getStackTraceString(e);
    		//log("Exception: " + e.getMessage());
    	}
    	return buildingList;
	 }
	 
	 /*public void log(String text) {
		 lblResult.setText(lblResult.getText() + text);
	 }*/
}
