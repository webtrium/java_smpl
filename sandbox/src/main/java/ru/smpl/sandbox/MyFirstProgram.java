package ru.smpl.sandbox;

public class MyFirstProgram {

  public static void main (String[] args) {

    Point p = new Point(1,1,2,2);

    System.out.printf("Расстояние между двумя точками на плоскости, с координатами: " +
            "(%.1f;%.1f) и (%.1f;%.1f) равно: %.2f!", p.dotX1, p.dotY1, p.dotX2, p.dotY2, p.distance());
  }
}