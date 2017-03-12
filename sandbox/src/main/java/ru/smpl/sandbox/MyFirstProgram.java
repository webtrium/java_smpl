package ru.smpl.sandbox;

public class MyFirstProgram {

  public static void main (String[] args) {

    Point p = new Point(1,1,2,2);

    System.out.printf("Расстояние между двумя точками на плоскости с координатами: " +
            "(%.1f;%.1f) и (%.1f;%.1f) равно: %.2f!", p.dotX1, p.dotY1, p.dotX2, p.dotY2, p.distance());
  }

/*  old
    Point p1 = new Point(1,1);
    Point p2 = new Point(2,2);
    System.out.printf("Расстояние между двумя точками на плоскости с координатами: " +
            "(%s,%s) и (%s,%s) равно: %.2f!", p1.dotX, p1.dotY, p2.dotX, p2.dotY, distance(p1,p2));
}
  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p1.dotX-p2.dotX),2) + Math.pow((p1.dotY-p2.dotY),2));
  };*/
}