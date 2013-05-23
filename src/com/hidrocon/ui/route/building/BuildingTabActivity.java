package com.hidrocon.ui.route.building;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hidrocon.ui.R;
import com.hidrocon.ui.route.main.RouteTabActivity;
import com.hidrocon.webservice.building.entities.Building;
import com.hidrocon.webservice.building.service.BuildingService;
import com.hidrocon.webservice.route.entities.Route;

public class BuildingTabActivity extends ListActivity {

	private static Building buildingSelected;
	
    private ProgressDialog progressDialog = null;
    private ArrayList<Building> buildingList = null;     
    private BuildingAdapter adapter;
    private Runnable requestBackgroundTask; 
    
    private Runnable responseTask = new Runnable() {
        public void run() {
            if(buildingList != null && buildingList.size() > 0){
                adapter.notifyDataSetChanged();
                for(int i=0;i<buildingList.size();i++) {
                	adapter.add(buildingList.get(i));
                }
            }
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    };
	
    public void onCreate(Bundle savedInstanceState) {
  	  	super.onCreate(savedInstanceState);
  	  	setContentView(R.layout.route_tab);
    }
    
    public void onResume() {
    	super.onResume();
    	loadBuildingList();
    }
    
    public void onRestart() {
    	super.onRestart();
    	loadBuildingList();
    }

    private void loadBuildingList() {
    	final Route routeSelected = RouteTabActivity.routeSelected;
    	if (routeSelected != null) {
	        buildingList = new ArrayList<Building>();
	        this.adapter = new BuildingAdapter(this, R.layout.building_tab_list_item, buildingList);
	        setListAdapter(this.adapter);
	        
	        requestBackgroundTask = new Runnable(){
	            public void run() {
	                BuildingService buildingService = new BuildingService();
	                buildingList = buildingService.doCall(routeSelected);
	                runOnUiThread(responseTask);
	            }
	        };
	        Thread thread =  new Thread(null, requestBackgroundTask, "BuildingBackgroundTask");
	        thread.start();
	        progressDialog = ProgressDialog.show(BuildingTabActivity.this,    
	      		  getString(R.string.loading_title), getString(R.string.loading_message), true);    	
    	}
    }
    
	private class BuildingAdapter extends ArrayAdapter<Building> {

	    private ArrayList<Building> items;

	    public BuildingAdapter(Context context, int textViewResourceId, ArrayList<Building> items) {
	            super(context, textViewResourceId, items);
	            this.items = items;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.building_tab_list_item, null);
            }
            Building building = items.get(position);
            if (building != null) {
                TextView tt = (TextView) v.findViewById(R.id.building_address);
                TextView bt = (TextView) v.findViewById(R.id.building_number);
                if (tt != null) {
                	tt.setText(building.getAddress());                            
                }
                if(bt != null){
                    bt.setText("Status: "+ building.getBuildingNumber());
                }
            }
            return v;
	    }
	}
    
}
