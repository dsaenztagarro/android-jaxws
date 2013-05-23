package com.hidrocon.webservice;

import org.ksoap2.serialization.SoapObject;

import com.hidrocon.webservice.building.entities.Building;
import com.hidrocon.webservice.route.entities.Route;

public class SoapObject2PojoConverter {

	public static Route soapObject2Route(SoapObject elementSoapObject) {
		String id = elementSoapObject.getProperty("id").toString();
		String routeName = elementSoapObject.getProperty("routeName").toString();
		//String nextReadingDate = elementSoapObject.getProperty("nextReadingDate").toString();
		Route route = new Route(Integer.parseInt(id),routeName);
		return route;
	} 
	
	public static Building soapObject2Building(SoapObject elementSoapObject) {
		//String id = elementSoapObject.getProperty("id").toString();
		String buildingNumber = elementSoapObject.getProperty("buildingNumber").toString();
		String buildingCode = elementSoapObject.getProperty("buildingCode").toString();
		String address = elementSoapObject.getProperty("buildingAddress").toString();
		//String nextReadingDate = elementSoapObject.getProperty("nextReadingDate").toString();
		Building building = new Building(Integer.parseInt("0"), Integer.parseInt(buildingNumber), buildingCode, address);
		return building;
	}
	
}
