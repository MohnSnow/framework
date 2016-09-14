package com.edwin.common;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import com.spatial4j.core.shape.Rectangle;

import ch.hsr.geohash.GeoHash;

/**
 * @author jinming
 */
public class MainTest {

    public static void main(String args[]) {
        double lon = 116.312528, lat = 39.983733;
        int radius = 1;
        SpatialContext geo = SpatialContext.GEO;
        Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(geo.makePoint(lon, lat),
                                                                    radius * DistanceUtils.KM_TO_DEG, geo, null);
        System.out.println(rectangle.getMinX() + "-" + rectangle.getMaxX());
        System.out.println(rectangle.getMinY() + "-" + rectangle.getMaxY());

        System.out.println(GeohashUtils.encodeLatLon(lat, lon, 10));

        // 计算周围8个区域的geohash
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, 10);
        System.out.println(geoHash.toBase32());
        GeoHash[] adjacent = geoHash.getAdjacent();
        for (GeoHash hash : adjacent) {
            System.out.println(hash.toBase32());
        }
    }
}
