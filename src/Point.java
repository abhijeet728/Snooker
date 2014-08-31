/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAMARTH
 */
public class Point {
    public float x;
    public float y;
    Point(){
        this.x = 0.0f;
        this.y = 0.0f;
    }
    Point(float x, float y){
        this.x = x;
        this.y = y;
    }
    public double getSlope(Point P1, Point P2){
        return (P2.y - P1.y)/(P2.x - P1.y);
    }
    public double getAngle(Point P1, Point P2,double slope){
        double angle = (float)Math.toDegrees(Math.atan((P2.y - P1.y)/(P2.x - P1.x)));
        return (slope < 0)? angle + 90.0f : angle;
    }
}
