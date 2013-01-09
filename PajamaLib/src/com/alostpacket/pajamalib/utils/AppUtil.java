package com.alostpacket.pajamalib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

public class AppUtil
{
	public static String  getVersionName( Context c )
	{
		String returnString = "";
		
		try
		{
			PackageInfo 	pi 		= c.getPackageManager().getPackageInfo ( c.getPackageName ( ) , 0 );
			returnString 			= pi.versionName;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
		return returnString;
	}
	
	
	public static String  getVersionCode( Context c)
	{
		String returnString = "";
		
		try
		{
			PackageInfo 	pi 	= c.getPackageManager().getPackageInfo ( c.getPackageName ( ), 0 );
			returnString 		= pi.versionCode +"";
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
		return returnString;
	}
	
	public static String getAppName(Context c)
	{
		return c.getApplicationInfo ( ).name;
	}
	
}
