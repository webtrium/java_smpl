package ru.smpl.sandbox;

public class Point {

    public double dotX;
    public double dotY;

    public Point(double dotX, double dotY){
        this.dotX = dotX;
        this.dotY = dotY;
    }

    public double distance(Point p){
        return Math.sqrt(Math.pow((this.dotX - p.dotX),2) + Math.pow((this.dotY - p.dotY),2));
    };

}
