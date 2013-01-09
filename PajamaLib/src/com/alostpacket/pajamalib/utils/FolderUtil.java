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

import java.io.File;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.alostpacket.pajamalib.config.LibraryBuild;


public class FolderUtil
{
	private static final String			TAG							= LibraryBuild.TAG_PREFIX + "FileUtil";
    private static final boolean 		GLOBAL_DEBUG				= LibraryBuild.DEBUGGING;
    private static final boolean 		LOCAL_DEBUG					= true;
    private static final boolean 		D 							= ( LOCAL_DEBUG && GLOBAL_DEBUG );

    

	/**
	 * Returns a folder File named after the application. 
	 * If the folder does not exist, this method will attempt to create it. 
	 * If an error occurs, it will return null and post to the errorHandler.
	 * 
	 * @param 	appContext		Application Context - best not to pass a reference to an Activity here.
	 * @return 					The ApplicationBuild.getAppName ( appContext ); folder or null if the storage is not available
	 */
	public static File getAppFolder(Context appContext)
	{
		
		String 		name 			= AppUtil.getAppName ( appContext );
		File 		parentDir		= Environment.getExternalStorageDirectory();
		
		File 		appFolder 		= getOrCreateFolder ( parentDir, name );
		
		
		return appFolder;
	}
	
	
	
	
	
	
	
	//-----------------------------------------------------------
	//	Generic/dynamic methods
	//-----------------------------------------------------------
	
	/**
	 * Returns a child folder to the main app folder with the name ResID
	 * If the folder does not exist, this method will attempt to create it. 
	 * If an error occurs, it will return null and post to the errorHandler.
	 * 
	 * @param 	appContext		Application Context - best not to pass a reference to an Activity here.
	 * @return 					The folder or null if the storage is not available
	 */
	public static File getFolder(Context appContext, int resID)
	{
		
		Resources 	res 			= appContext.getResources();
		String 		name 			= res.getString ( resID );
		File 		parentDir		= getAppFolder( appContext );
		
		File 		folder 			= getOrCreateFolder ( parentDir, name );
		
		
		return folder;
		
	}



	/**
	 * Gets a folder as a File object or creates one if it doesn't yet exist
	 * @param parentDir  		The folder where to create this new folder. Must be a File object directory
	 * @param name				The name of the new folder to create
	 * @return
	 */
	private static File getOrCreateFolder(File parentDir, String name )
	{
		
		if ( !FileUtil.isStorageWritable() )								//make sure the storage is present
		{
			return null;
		}
		
		
		if ( parentDir == null || name == null )				//sanity check
		{	
			if (D) Log.e ( TAG, "getOrCreateFolder() parentDir == null" );
			return null;
		}
		
		
		File childFolder = null;    									//create a null object we can return if all else fails
		
		try
		{
			
			childFolder = new File( parentDir, name );
			
			if ( !childFolder.exists() )  						//see if folder already exist, create if not
			{
				boolean madeDir = childFolder.mkdir();
				
				if ( !madeDir ) 								//returns false if we could not make it a directory
				{
					if (D)Log.e ( TAG, "Error: getOrCreateFolder(): mkdir() failed " );
				}
			}
			
		}
		catch ( Exception e )
		{
			if (D) e.printStackTrace();
			return null;
		}
		
		//debugging 
		if (D)	Log.v ( TAG, "getOrCreateFolder() ::: childFolder.getAbsolutePath() :"+childFolder.getAbsolutePath() ); 
		
		return childFolder;
		
	}


}
