package com.alostpacket.pajamalib.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PermissionInfo;


public class FlagUtil
{
	/**
	 * possibly deprecated/redundant
	 * @param flags
	 * @return
	 */
	public static boolean isSystemApp (final int flags )
	{
		if (( flags & ApplicationInfo.FLAG_SYSTEM ) > 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static boolean matchFlag( final int flagsToCheck, final int flagToMatchAgainst )
	{
		if (( flagsToCheck & flagToMatchAgainst ) > 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String getProtectionLevelString( final int flagsToCheck )
	{
		if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_DANGEROUS) )
		{
			return "DANGEROUS";
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, 0x00000020) )
		{
			return "DEVELOPMENT";
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_NORMAL) )
		{
			return "NORMAL";
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, 0x00000010) )
		{
			return "SYSTEM";
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_SIGNATURE) )
		{
			return "SIGNATURE";
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_SIGNATURE_OR_SYSTEM) )
		{
			return "SIGNATURE OR SYSTEM";
		}
		else
		{
			return "UNKNOWN";
		}
	}
	
	
	public static int getProtectionLevelInt( final int flagsToCheck )
	{
		if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_DANGEROUS) )
		{
			return 2;
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, 0x00000020) )//PROTECTION_FLAG_DEVELOPMENT
		{
			return 6;
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_NORMAL) )
		{
			return 1;
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, 0x00000010) )//PROTECTION_FLAG_SYSTEM
		{
			return 5;
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_SIGNATURE) )
		{
			return 4;
		}
		else if ( FlagUtil.matchFlag(flagsToCheck, PermissionInfo.PROTECTION_SIGNATURE_OR_SYSTEM) )
		{
			return 3;
		}
		else
		{
			return 0;
		}
	}
}
