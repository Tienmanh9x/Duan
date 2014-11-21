package com.parsexml_vnnet;

import java.util.List;

import xml.parse.ListTinTuc;
import xml.parse.ParseXml;
import xml.parse.TinTuc;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Log;

public class BaseActivity extends Activity{
	
	public static ListTinTuc listRssItem;
	private String url = "http://www.tinmoi.vn/rss/home.rss";
	
	public List<TinTuc> setArrayTinTuc(){
		try {
			listRssItem = new ListTinTuc();
			ParseXml parse = new ParseXml(url);
			listRssItem = parse.parseXMLTinTuc();		
		} catch (Exception e) {
			listRssItem= null;
			Log.i("Error", "Here");
		}
		return listRssItem.getListTinTuc();
	}
	
	public Boolean checkConnection()
	{
		ConnectivityManager conn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
	    if (conn != null && (conn.getNetworkInfo(1).getState() == State.CONNECTED) ||(conn.getNetworkInfo(0).getState() == State.CONNECTED)){ 
	       return true;
	    }else 
	    {             
	       return false;
	    }
	}

}
