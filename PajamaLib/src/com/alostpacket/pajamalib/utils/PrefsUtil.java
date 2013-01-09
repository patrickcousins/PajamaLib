/*   Copyright 2012 Patrick Cousins

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.alostpacket.pajamalib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PrefsUtil
{
	private static SharedPreferences prefs = null;
	
	
	
	private static SharedPreferences getPrefs(Context c)
	{
		prefs = prefs == null ? c.getSharedPreferences ( AppUtil.getAppName( c )+".prefs", Context.MODE_PRIVATE ) : prefs ; 
		return prefs;
	}
	
	public static void clearPrefs(Context c)
	{
		prefs = prefs == null ? c.getSharedPreferences ( AppUtil.getAppName( c )+".prefs", Context.MODE_PRIVATE ) : prefs ; 
		Editor editor = prefs.edit ();
		editor.clear();
		editor.commit();
	}
	
	
	/////////////puts
	
	public static void putBoolean(String name, boolean value, Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		Editor editor = sharedPrefs.edit ();
		editor.putBoolean ( name, value );
		editor.commit();
	}
	
	
	public static void putInt(String name, int value,  Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		Editor editor = sharedPrefs.edit ();
		editor.putInt ( name, value);
		editor.commit();
	}
	
	public static void putString(String name, String value, Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		Editor editor = sharedPrefs.edit ();
		
		editor.putString ( name, value );
		editor.commit();
	}
	
	public static void putLong(String name, long value, Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		Editor editor = sharedPrefs.edit ();
		editor.putLong ( name, value );
		editor.commit();
	}
	
	
	/////////////gets
	
	public static boolean getBoolean(String name, boolean defaultValue, Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		return sharedPrefs.getBoolean ( name, defaultValue );
	}
	
	
	public static int getInt(String name, int defaultValue,  Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		return sharedPrefs.getInt ( name, defaultValue);
	}
	
	public static String getString(String name, String defaultValue, Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		return sharedPrefs.getString ( name, defaultValue );
	}
	
	public static long getLong(String name,  long defaultValue, Context c)
	{
		SharedPreferences sharedPrefs = getPrefs ( c ); 
		return sharedPrefs.getLong ( name, defaultValue );
	}

}
