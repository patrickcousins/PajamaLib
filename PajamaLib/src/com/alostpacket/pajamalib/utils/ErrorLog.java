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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import android.content.Context;

import com.alostpacket.pajamalib.config.LibraryBuild;

//TODO possible replace with Log4j or SLF4J
public class ErrorLog
{
	@SuppressWarnings("unused")
	private static final String			TAG							= LibraryBuild.TAG_PREFIX + "ErrorLog";
    private static final boolean 		GLOBAL_DEBUG				= LibraryBuild.DEBUGGING;
    private static final boolean 		LOCAL_DEBUG					= true;
    private static final boolean 		D 							= ( LOCAL_DEBUG && GLOBAL_DEBUG );
	private static  	File			logFile						= null;
	
	
	
	synchronized public static void write(String text, Context appContext)
	{  
		if (logFile == null)
		{
			logFile = new File( FolderUtil.getAppFolder ( appContext ), "error_log.txt");
		}
	   
	   if ( !logFile.exists() )
	   {
	      try
	      {
	         logFile.createNewFile();
	      } 
	      catch (Exception e)
	      {
	    	 if (D) e.printStackTrace();
	      }
	   }
	   
	   
	   try
	   {
	      //BufferedWriter for performance, FileWriter 2nd param true to append to file
	      BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true)); 
	      buf.append(System.currentTimeMillis() +":"+ text);
	      buf.newLine();
	      buf.close();
	   }
	   catch (Exception e)
	   {
	      if (D) e.printStackTrace();
	   }
	}

}
