package ru.smpl.sandbox;

public class Point {

    public double dotX1;
    public double dotY1;
    public double dotX2;
    public double dotY2;

    public Point(double dotX1, double dotY1, double dotX2, double dotY2){
        this.dotX1 = dotX1;
        this.dotY1 = dotY1;
        this.dotX2 = dotX2;
        this.dotY2 = dotY2;
    }

    public double distance(){
        return Math.sqrt(Math.pow((this.dotX1-this.dotX2),2) + Math.pow((this.dotY1-this.dotY2),2));
    };

}
