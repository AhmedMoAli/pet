package com.salesforce.tooling.utils;

/**
 * Utility for Geolocation.
 */
public class GeoLocationUtil {

    private static final double R = 6372.8; // In kilometers

    /**
     * Gets the distances between two geolocations.
     * 
     * @param lat1 first latitude point.
     * @param lon1 first longitude point.
     * @param lat2 second latitude point.
     * @param lon2 second longitude point.
     * @return
     */
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

}
