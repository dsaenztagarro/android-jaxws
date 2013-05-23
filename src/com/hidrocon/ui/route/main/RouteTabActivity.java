package com.hidrocon.ui.route.main;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hidrocon.ui.HidroconActivity;
import com.hidrocon.ui.R;
import com.hidrocon.webservice.route.entities.Route;
import com.hidrocon.webservice.route.service.RouteService;

public class RouteTabActivity extends ListActivity {
	
	public static Route routeSelected;
	
    private ProgressDialog progressDialog = null;
    private ArrayList<Route> routeList = null;     
    private RouteAdapter adapter;
    private Runnable requestBackgroundTask; 
    
    private Runnable responseTask = new Runnable() {
        public void run() {
            if(routeList != null && routeList.size() > 0){
                adapter.notifyDataSetChanged();
                for(int i=0;i<routeList.size();i++) {
                	adapter.add(routeList.get(i));
                }
            }
            progressDialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    };
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.route_tab);
      routeList = new ArrayList<Route>();
      this.adapter = new RouteAdapter(this, R.layout.route_tab_list_item, routeList);
      setListAdapter(this.adapter);
      
      requestBackgroundTask = new Runnable(){
          public void run() {
              RouteService routeService = new RouteService();
              routeList = routeService.doCall();
              runOnUiThread(responseTask);
          }
      };
      Thread thread =  new Thread(null, requestBackgroundTask, "BackgroundTask");
      thread.start();
      progressDialog = ProgressDialog.show(RouteTabActivity.this,    
    		  getString(R.string.loading_title), getString(R.string.loading_message), true);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		routeSelected = (Route) getListAdapter().getItem(position);
		Activity activity = getParent();
		if (activity instanceof HidroconActivity) {
			((HidroconActivity)activity).getTabHost().setCurrentTab(1);
		}
		Toast.makeText(this, routeSelected.getRouteName() + " seleccionado", Toast.LENGTH_LONG).show();
	}
	
	private class RouteAdapter extends ArrayAdapter<Route> {

	    private ArrayList<Route> items;

	    public RouteAdapter(Context context, int textViewResourceId, ArrayList<Route> items) {
	            super(context, textViewResourceId, items);
	            this.items = items;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.route_tab_list_item, null);
            }
            Route route = items.get(position);
            if (route != null) {
                TextView tt = (TextView) v.findViewById(R.id.route_name);
                TextView bt = (TextView) v.findViewById(R.id.route_details);
                if (tt != null) {
                	tt.setText(route.getRouteName());                            
                }
                if(bt != null){
                    bt.setText("Status: "+ route.getId());
                }
            }
            return v;
	    }
	}
	
}
