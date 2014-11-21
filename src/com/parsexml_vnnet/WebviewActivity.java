package com.parsexml_vnnet;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;

public class WebviewActivity extends Activity {
	public static String URL="http//google.com";
	public static String strURL = "http//google.com";
	WebView webview ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webview);
		webview = (WebView) findViewById(R.id.webview);
//		webview.getSettings().setJavaScriptEnabled(true);
		Log.d("//===================================", "//==========="+strURL);
		Log.d("//===================================", "//==========="+URL);
		Log.d("//===================================", "//==========="+URL);
		Log.d("//===================================", "//==========="+URL);
		webview.loadUrl(strURL);
	}

	

}
