package org.mimp.directions;

import org.mimp.directions.impl.DrivingDirectionsGoogleKML;

public class DrivingDirectionsFactory {
    public static DrivingDirections createDrivingDirections() {
        return new DrivingDirectionsGoogleKML();
    }
}
