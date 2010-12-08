package org.mapping.google;

import org.mapping.google.impl.DrivingDirectionsGoogleKML;

public class DrivingDirectionsFactory {
    public static DrivingDirections createDrivingDirections() {
        return new DrivingDirectionsGoogleKML();
    }
}
