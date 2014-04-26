package com.luthfihariz.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	private SharedPreferences pref;
	private Editor editor;
	
	private static final int PRIVATE_MODE = 0;
	private static final String PREF_NAME = "HalalSpotPref";
	
	private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_USERID = "id"; 
	
	public SessionManager(Context context) {
		this.pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}		
	
	public void createSession(int id, String username){
		editor.putBoolean(KEY_IS_LOGGED_IN, true);
		editor.putInt(KEY_USERID, id);
		editor.putString(KEY_USERNAME, username);
		editor.commit();
	}	
	
	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_IS_LOGGED_IN, false);
	}
	
	public String getUsername(){
		return pref.getString(KEY_USERNAME, "");
	}
	
	public void clearSession(){
		Helper.log("clear gudpoin session");
		editor.clear();
		editor.commit();
	}
	
}
