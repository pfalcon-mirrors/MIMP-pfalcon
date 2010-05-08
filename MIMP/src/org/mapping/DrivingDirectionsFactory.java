package org.mapping;

import org.mapping.impl.DrivingDirectionsGoogleKML;

public class DrivingDirectionsFactory
{
	public static DrivingDirections createDrivingDirections ()
	{
		return new DrivingDirectionsGoogleKML ();
	}
}
