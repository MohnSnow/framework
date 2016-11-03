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

    public synchronized void waitMethod() throws InterruptedException {
        this.wait();
        System.out.println("waitMethod");
    }

    public void notifyMethod() throws InterruptedException {
        this.notify();
        System.out.println("notifyMethod");
    }

    public static void main(String args[]) {
        int x = -1 << 31 >> 1;
        System.out.println(Integer.toBinaryString(x));
        x = x >> 32;
        //System.out.println(x);
        //System.out.println(Integer.toBinaryString(x));
        //System.out.println(Integer.toBinaryString(-1));
    }
}
