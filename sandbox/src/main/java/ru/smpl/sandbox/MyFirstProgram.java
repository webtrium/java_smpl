package ru.smpl.sandbox;

public class MyFirstProgram {

  public static void main (String[] args) {

    Point p1 = new Point(1,1);
    Point p2 = new Point(2,2);

    System.out.printf("Расстояние между двумя точками на плоскости, с координатами: " +
            "(%.1f;%.1f) и (%.1f;%.1f) равно: %.2f!", p1.dotX, p1.dotY, p2.dotX, p2.dotY, p1.distance(p2));
  }
}