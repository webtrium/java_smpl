package ru.smpl.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(1,1);
        Point p2 = new Point(1,2);

        Assert.assertEquals(p1.distance(p2),1.0);
    }

    @Test
    public void testDistancePrintln() {
        Point p1 = new Point(-2,-2);
        Point p2 = new Point(-2,-4);

        Assert.assertTrue(p1.distance(p2) == 2.0, "Тест провален!");
    }
}
