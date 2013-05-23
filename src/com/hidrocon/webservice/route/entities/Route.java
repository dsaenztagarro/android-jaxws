/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hidrocon.webservice.route.entities;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 *
 * @author David Sáenz Tagarro
 */
public class Route implements KvmSerializable{
	private int id;
	private String routeName;
	 
	public Route(int id, String routeName) {
		this.id = id;
		this.routeName = routeName;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRouteName() {
		return routeName;
	}
	
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Object getProperty(int arg0) {
        switch(arg0)
        {
        case 0:
            return id;
        case 1:
            return routeName;
        }
        return null;
	}

	public int getPropertyCount() {
		return 2;
	}

	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "id";
            break;
        case 1:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "routeName";
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
            routeName = value.toString();
            break;
        default:
            break;
        }
	}	
	
}
