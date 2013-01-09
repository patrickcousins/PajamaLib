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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.alostpacket.pajamalib.config.LibraryBuild;


public class FileUtil
{
	private static final String			TAG							= "FileUtil";
    private static final boolean 		GLOBAL_DEBUG				= LibraryBuild.DEBUGGING;
    private static final boolean 		LOCAL_DEBUG					= true;
    private static final boolean 		D 							= ( LOCAL_DEBUG && GLOBAL_DEBUG );
    
    /**
     * Very conservative collection of characters considered "illegal".
     * Many may be legal, but this is the "better safe than sorry list".
     * Modify or create new character sets as needed.
     * Includes:
     *  " / \ | $ @ ! ~ ^ ' * . > [ ] + < 
     *  
     */
    public static final String[] 		ILLEGAL_FILENAME_CHARS		= { "\"", "/","\\","|","$","@","!",
    																	"~","^","'","*",".","<",">","[",
    																	"]","+" };
    
    
  
    
    /**
	 * Quick check to make sure storage is in the writable state.  
	 * Any other state is mostly useless for our purposes
	 * 
	 * @return true if the external storage is writable
	 */
	public static boolean isStorageWritable()
	{
		boolean 	storageWriteable 	= false;
		String 		state 				= Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) 
		{
			storageWriteable = true;
		}
		else 
		{
		    storageWriteable = false;
		}
		
		return storageWriteable;
	}
	

	
	/**
	 * Replaces characters that may not work for filenames with underscores
	 * 
	 * @param s
	 * @return the 'cleaned' string, or the original unmodified string if an error occurs
	 * @see #ILLEGAL_FILENAME_CHARS
	 */
	public static String removeIllegalCharacters ( String s )
	{
		String [] illegalChars = ILLEGAL_FILENAME_CHARS;
		
		if ( s != null )
		{
			//copy the string so we leave the original unchanged in case we hit an error
			String returnString = new String(s);
			
			try
			{
				int numIllegalChars = illegalChars.length;
				
				for ( int i = 0; i < numIllegalChars; i++ )
				{
					if ( returnString != null )
					{
						if( returnString.contains ( illegalChars[i] ) )
						{
							returnString = returnString.replace ( illegalChars[i] , "_" );
						}
					}
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace ( );
				if (D) Log.e ( TAG, "error removing filename characters" );
			}
			
			return returnString;
		}
		else
		{
			if (D) Log.w ( TAG, "WARNING! FileUtil.removeIllegalCharacters() encountered an error and is returning the unmodified string" );
			return s;
		}
		
	}


		
	
	/**
	 * Write a .nomedia file in the directory provided
	 * (refactored version)
	 * @param 	handler  		A handler to an Activity that can receive {@link HandlerIDs.ERROR_WITH_TOAST_STRING} messages
	 * @param 	directory	  	The directory to store the .nomedia file
	 * @return 	true 			if file was successfully written or appears to already exist
	 */
	public static boolean writeNoMediaFile (File directory, Handler handler)
	{
		//sanity checks
				
		
		if( directory == null )
		{
			if (D) Log.e ( TAG, "writeNoMediaFile() you must supply a directory File" );
			return false;
		}
		
		
		
		if ( !isStorageWritable() ) 
		{
			return false;
		}
		
		
		//try writing the file
	
		try
		{
			//sanity checks
			if( !directory.exists() )
			{
				return false;
			}
			
			
			if ( !directory.isDirectory() )
			{
				try
				{
					directory.mkdir();
				}
				catch ( Exception e )
				{
					if (D) e.printStackTrace ( );
					return false;
				}
			}
			
			
			File noMedia = new File ( directory, ".nomedia" );
			
			if ( noMedia.exists() )
			{
				if (D) Log.i ( TAG, ".nomedia appears to exist already, returning without writing a new file" );
				return true;
			}
			else
			{
				boolean fileCreated = noMedia.createNewFile();
				
				if (D) Log.i (TAG, "writeNoMediaFile() fileCreated = " + fileCreated );
				
				if ( noMedia.canWrite() )
				{
					FileOutputStream 		noMediaOutStream 		= new FileOutputStream ( noMedia );
					
					noMediaOutStream.write( 0 );
					noMediaOutStream.close();
				}
				else
				{
					if (D) Log.e (TAG, "error writing .nomedia");
					return false;
				}
			}
			
			
		}
		catch ( Exception e )
		{
			if (D) Log.e (TAG, "error writing .nomedia-- Unknown exception?");
			
			
			if (D) e.printStackTrace();
			return false;
		}
		
		//if we get this far, everything is kosher
		//all other checks return false except if the file already exists which returns true
		return true;
		
	}
	
	
	
	/**
	 * Copies a file from one file to another
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	public static void copy(File src, File dst) throws IOException 
	{
	    InputStream in = new FileInputStream(src);
	    OutputStream out = new FileOutputStream(dst);

	    // Transfer bytes from in to out
	    byte[] buf = new byte[1024];
	    int len;
	    while ((len = in.read(buf)) > 0) {
	        out.write(buf, 0, len);
	    }
	    in.close();
	    out.close();
	}



}
