package com.example.csvapi.util;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

public class CoordinateConverter {

    public static double[] convertOSGB36ToWGS84(double easting, double northing) {
        // Define the coordinate reference systems
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem osgb36 = crsFactory.createFromName("EPSG:27700");
        CoordinateReferenceSystem wgs84 = crsFactory.createFromName("EPSG:4326");

        // Define the transformation
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = ctFactory.createTransform(osgb36, wgs84);

        // Perform the transformation
        ProjCoordinate srcCoord = new ProjCoordinate(easting, northing);
        ProjCoordinate dstCoord = new ProjCoordinate();
        transform.transform(srcCoord, dstCoord);

        return new double[]{dstCoord.y, dstCoord.x}; // Latitude, Longitude
    }
}
