/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hidrocon.webservice.building.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 *
 * @author David Sáenz Tagarro
 */
public class Building implements KvmSerializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int buildingNumber; 
    private String buildingCode;
    private String address;

    public Building(int id, int buildingNumber, String buildingCode, String address) {
        this.id = id;
        this.buildingNumber = buildingNumber;
        this.buildingCode =  buildingCode;
        this.address = address;
    }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getBuildingNumber() {
		return buildingNumber;
	}


	public void setBuildingNumber(int buildingNumber) {
		this.buildingNumber = buildingNumber;
	}


	public String getBuildingCode() {
		return buildingCode;
	}


	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Object getProperty(int arg0) {
        switch(arg0)
        {
        case 0:
            return id;
        case 1:
            return buildingNumber;
        case 2:
            return buildingCode;
        case 3:
        	return address;
        }
        return null;
	}

	public int getPropertyCount() {
		return 4;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "id";
            break;
        case 1:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "buildingNumber";
            break;
        case 2:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "buildingCode";
            break;            
        case 3:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "address";
            break;
        default:break;
        }
	}

	public void setProperty(int index, Object value) {
        switch(index)
        {
        case 0:
            id = Integer.parseInt(value.toString());
            break;
        case 1:
            buildingNumber = Integer.parseInt(value.toString());
            break;
        case 2:
            buildingCode = value.toString();
            break;            
        case 3:
            address = value.toString();
            break;
        default:
            break;
        }
	}

    
}
