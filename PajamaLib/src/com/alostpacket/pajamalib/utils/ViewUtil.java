package com.alostpacket.pajamalib.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ViewUtil
{
	
	private static LayoutInflater	inflater;

	private static LayoutInflater getInflater(Context c)
	{
		inflater = inflater == null? (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE): inflater;
		return inflater;
	}
	
	/**
	 * Convenience method for inflating without attaching to root.  
	 * 
	 * @param c
	 * @param resourceID
	 * @param parent   		important to pass the view's parent.
	 * @return
	 */
	
	public static View inflate (Context c, int resourceID, ViewGroup parent )
	{
		return getInflater ( c ).inflate ( resourceID, parent, false );
	}

}
