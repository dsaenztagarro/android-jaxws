package com.hidrocon.webservice.route.service;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.hidrocon.webservice.SoapObject2PojoConverter;
import com.hidrocon.webservice.route.entities.Route;

public class RouteService {
 
	 private static final String NAMESPACE = "http://webservice.hidrocon/";
	 private static String URL = "http://192.168.1.35:8080/hidroconweb/hidroconws"; 
	 private static final String METHOD_NAME = "getAllRoute";
	 private static final String SOAP_ACTION =  "http://webservice.hidrocon/getAllRoute";
	 
	 //private TextView lblResult;
	 
	 public RouteService() {
		 //this.lblResult = lblResult; 
	 }
	 
	@SuppressWarnings("unchecked")
	public ArrayList<Route> doCall() {
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 

    	PropertyInfo propInfo=new PropertyInfo();
    	propInfo.name="name";
    	propInfo.type=PropertyInfo.STRING_CLASS;

    	String name = "David";
    	//request.addProperty(propInfo, name);  

    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
    	
    	envelope.setOutputSoapObject(request);
    	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
    	androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

    	ArrayList<Route> routeList = new ArrayList<Route>();
    	try {
    		androidHttpTransport.call(SOAP_ACTION, envelope);
    	    
    		SoapObject resultsRequestSOAP = (SoapObject)envelope.bodyIn;
    	  
    		int size = resultsRequestSOAP.getPropertyCount();
    		for (int i=0; i<size; i++) {
    			SoapObject elementSoapObject = (SoapObject)resultsRequestSOAP.getProperty(i);
				Route route = SoapObject2PojoConverter.soapObject2Route(elementSoapObject);
				routeList.add(route);
				//log("\nid: " + id);
				//log("\nname: " + routeName);
				//log("\nnextReadingDate: " + nextReadingDate);
    		}
    	 
    	} catch (Exception e) {
    		Log.getStackTraceString(e);
    	}
    	return routeList;
	 }
	 
	 /*public void log(String text) {
		 lblResult.setText(lblResult.getText() + text);
	 }*/
}
