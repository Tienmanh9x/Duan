package com.parsexml_vnnet;
import java.util.ArrayList;
import java.util.List;

import com.parsexml_vnnet.WebviewActivity;
import xml.custom.CustomView;
import xml.parse.ListTinTuc;
import xml.parse.TinTuc;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.parsexml_vnnet.RefreshableListView.OnRefreshListener;

public class ParseXMLExamActivity extends BaseActivity {
	CustomView adapter;
    //ListView list;
    ListTinTuc listRssItem;
    List<TinTuc> listTinTuc;
    private ProgressDialog progressBar;
    private RefreshableListView list;
    Mystask asyscTask ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parse_xmlexam);
		list = (RefreshableListView) findViewById(R.id.listview);
		asyscTask = new Mystask();
		asyscTask.execute();	
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), WebviewActivity.class);
				startActivity(intent);
//				WebviewActivity.URL = listTinTuc.get(position).getLink();
				WebviewActivity.strURL = listTinTuc.get(position).getLink();
			}
		});
	
		list.setOnRefreshListener(new OnRefreshListener() {
            @SuppressWarnings("unused")
			public void onRefresh() {
            	Log.i("//=======================", "//============onRefresh===========");
               
            }

			@Override
			public void onRefresh(RefreshableListView listView) {
				// TODO Auto-generated method stub
				Log.i("//=======================", "//============RefreshableListView===========");
				asyscTask = new Mystask();
				asyscTask.execute();
			}
        });
	}
	
	private class Mystask extends AsyncTask<Void, Void, ListTinTuc> {

		@Override
		protected ListTinTuc doInBackground(Void... params) {
			DatabaseHandler db = new DatabaseHandler(ParseXMLExamActivity.this);
			
			if(checkConnection()){
				Log.d("--------", "//=============//connect online");
				listTinTuc = new ArrayList<TinTuc>();
				listTinTuc = setArrayTinTuc();
				
				for (int i = 0; i < listTinTuc.size(); i++) {
					Log.d("--------", "//=============//"+i);
					TinTuc entry = listTinTuc.get(i);
					Log.d("--------", "//=============//"+entry.getTitle());
					db.addData(entry);
				}
			}			
			else{
				Log.d("--------", "//=============//connect offline");
				listTinTuc = new ArrayList<TinTuc>();
				listTinTuc = db.getAllContacts();
			}
			Log.d("--------", "//====progressBar=========//");
			Log.d("--------", "//====progressBar=========//");
			Log.d("--------", "//====progressBar=========//");
			Log.d("--------", "//====progressBar=========//"+listTinTuc.size());
			
			
			
			if (progressBar.isShowing()) {
				progressBar.dismiss();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(ListTinTuc result) {
			// TODO Auto-generated method stub
			Log.i("----------", "//=============//onPostExecute");
			adapter = new CustomView(ParseXMLExamActivity.this,listTinTuc);
			list.setAdapter(adapter);
			list.completeRefreshing();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			Log.i("---------", "//=============//onPreExecute");
			progressBar = ProgressDialog.show(ParseXMLExamActivity.this, "",
					"please wait for checking data...");
			super.onPreExecute();
		}
		
	}
}
