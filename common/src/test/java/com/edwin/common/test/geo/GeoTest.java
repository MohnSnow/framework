package com.edwin.common.test.geo;

import org.junit.Test;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.io.GeohashUtils;
import com.spatial4j.core.shape.Rectangle;

import ch.hsr.geohash.GeoHash;

/**
 * geohash常用操作
 * 
 * @author jinming
 */
public class GeoTest {

    /**
     * 坐标区间
     */
    @Test
    public void testCoordinatesScope() {
        double lon = 116.312528, lat = 39.983733;
        int radius = 1; // 一公里
        SpatialContext geo = SpatialContext.GEO;
        Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(geo.makePoint(lon, lat),
                                                                    radius * DistanceUtils.KM_TO_DEG, geo, null);
        System.out.println(rectangle.getMinX() + "-" + rectangle.getMaxX());
        System.out.println(rectangle.getMinY() + "-" + rectangle.getMaxY());
    }

    /**
     * 10位的geohash base32 编码
     */
    @Test
    public void testGeoHashCode() {
        double lon = 116.312528, lat = 39.983733;
        System.out.println(GeohashUtils.encodeLatLon(lat, lon, 10));
    }

    /**
     * 周围8个区域的geohash，精度是10位
     */
    @Test
    public void testAdjacent() {
        double lon = 116.312528, lat = 39.983733;
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, 10);
        System.out.println(geoHash.toBase32());
        GeoHash[] adjacent = geoHash.getAdjacent();
        for (GeoHash hash : adjacent) {
            System.out.println(hash.toBase32());
        }
    }
}
