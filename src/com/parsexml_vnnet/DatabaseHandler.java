package com.parsexml_vnnet;

import java.util.ArrayList;
import java.util.List;

import xml.parse.TinTuc;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DB_TinTuc";
	
	private static final String TinTuc_Title = "title";
	private static final String TinTuc_Image = "image";
	private static final String TABLE_TINTUC = "tbTinTuc";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    
		String sSQL = "CREATE TABLE " + TABLE_TINTUC + "("
				+ TinTuc_Title + " TEXT," 
				+ TinTuc_Image + " TEXT)";
		Log.i("//========table", "//=================="+sSQL);
				db.execSQL(sSQL);
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TINTUC);
		onCreate(db);
	}
	
	public void addDataTinTuc(String title, String image){
		SQLiteDatabase db = this.getWritableDatabase();
		db.beginTransaction();
		try {
			String sSQL = "insert into tbTinTuc(title,image)"
					+ " values ('"
					+ title
					+ "', '"
					+ image + "');";
			Log.i("//========insert", "//=================="+sSQL);
			db.execSQL(sSQL);
			db.setTransactionSuccessful();
		} catch (SQLiteException e2) {
			// report problem
		} finally {
			db.endTransaction();
		}
    }
	
	public void addData(TinTuc tintuc){
		
	   SQLiteDatabase db = this.getWritableDatabase();
	   db.delete(TABLE_TINTUC, null, null);
	   ContentValues values = new ContentValues();
	   values.put("title", tintuc.getTitle());
	   values.put("image", tintuc.getImage());
	   db.insert(TABLE_TINTUC, null, values);
       db.close();
	}
	
	public int getContactsCount() {
        String sSQL = "SELECT  * FROM " + TABLE_TINTUC;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sSQL, null);
        cursor.close();
        // return count
        return cursor.getCount();
	}
	
	 public void deleteHotel(TinTuc contact) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(TABLE_TINTUC, TinTuc_Title + " = ?",
	                new String[] { String.valueOf(contact.getTitle())});
	        db.close();
	    }
	 
	 public List<TinTuc> getAllContacts() {
	        List<TinTuc> listHotel = new ArrayList<TinTuc>();
	        // Select All Query
	        String sSQL = "SELECT  title,image FROM " + TABLE_TINTUC;
	        SQLiteDatabase db = this.getWritableDatabase();
	        Cursor cursor = db.rawQuery(sSQL, null);
	 
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	TinTuc tintuc = new TinTuc();
	                tintuc.setTitle(cursor.getString(0));
	                tintuc.setImage(cursor.getString(1));
	                // Adding contact to list
	                listHotel.add(tintuc);
	            } while (cursor.moveToNext());
	        }
	 
	        // return contact list
	        return listHotel;
	    }
}
