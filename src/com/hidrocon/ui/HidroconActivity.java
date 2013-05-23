package com.hidrocon.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.hidrocon.ui.route.building.BuildingTabActivity;
import com.hidrocon.ui.route.main.RouteTabActivity;
import com.hidrocon.ui.route.neighbour.NeighbourTabActivity;
import com.hidrocon.webservice.route.service.RouteService;

public class HidroconActivity extends TabActivity {
	
	 private static final String NAMESPACE = "http://webservice.hidrocon/";
	 private static String URL = "http://192.168.1.35:8080/hidroconweb/hidroconws"; 
	 private static final String METHOD_NAME = "getAllRoute";
	 private static final String SOAP_ACTION =  "http://webservice.hidrocon/getAllRoute";
	 
	 //private static final String METHOD_NAME = "getHello";
	 //private static final String SOAP_ACTION =  "http://webservice.hidrocon/getHello";

	 private TextView lblResult;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, RouteTabActivity.class); 

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("tab_route").setIndicator(getString(R.string.route_tab),
                          res.getDrawable(R.drawable.ic_tab_route))
                          //.setContent(R.id.textview1);
                          .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, BuildingTabActivity.class);
        spec = tabHost.newTabSpec("tab_building").setIndicator(getString(R.string.building_tab),	
                          res.getDrawable(R.drawable.ic_tab_building))
                          .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, NeighbourTabActivity.class);
        spec = tabHost.newTabSpec("tab_neighbour").setIndicator(getString(R.string.neighbour_tab),
                          res.getDrawable(R.drawable.ic_tab_neighbour))
                          .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    	
    	/*
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);

    	lblResult = (TextView) findViewById(R.id.result);
    	
    	routeService = new RouteService(lblResult);
    	routeService.doCall();
    	*/
    	
    	/*SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME); 

    	PropertyInfo propInfo=new PropertyInfo();
    	propInfo.name="name";
    	propInfo.type=PropertyInfo.STRING_CLASS;

    	String name = "David";
    	//request.addProperty(propInfo, name);  

    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
    	
    	envelope.setOutputSoapObject(request);
    	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
    	androidHttpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    	

    	try {
    		lblResult.setText(lblResult.getText()+"Calling..");
    		
    		androidHttpTransport.call(SOAP_ACTION, envelope);
    		androidHttpTransport.debug = true;
    		
    		lblResult.setText(lblResult.getText()+"..OK");
    	    
    		SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
    	  
    	    lblResult.setText(lblResult.getText()+"resultsRequestSOAP: " + resultsRequestSOAP.toString());
    	    
    	    lblResult.setText(lblResult.getText()+"Success!!: "+resultsRequestSOAP.toString());
    	   
    	 
    	} catch (Exception e) {
    		lblResult.setText(lblResult.getText()+"Exception: " + e.getMessage());
    	}
    	
<!--
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	android:orientation="vertical" android:layout_width="fill_parent"
	android:layout_height="fill_parent">
	<TextView android:layout_width="fill_parent"
		android:layout_height="wrap_content" android:text="" />
	<TextView android:id="@+id/result" android:layout_width="fill_parent"
		android:layout_height="wrap_content" />
</LinearLayout>
-->
    	*/
    }
}