/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Ball {
    private final float diameter;
    private final float mass;
    private double vx;
    private double vy;
    private float xPos;
    private float yPos;
    private String color;
    private int ballCode;
    private final int ballId;
    
    //1-white 2-red 3-yellow 4-green 5-brown 6-blue 7-pink 8-black
    private static final int preDefCodes[] = {1, 2, 3, 4, 5, 6, 7, 8};
    private static final String preDefColors[] = {"white", "red", "yellow", "green", "brown", "blue", "pink", "black"};
    
    public Ball(int code, int ballId, float dia, float xPos, float yPos){
        this.diameter = dia;
        this.xPos = xPos;
        this.yPos = yPos;
        this.ballId = ballId;
        this.mass = 1;
        this.vx = 0.0f;
        this.vy = 0.0f;
        int initiated = 0;
        for(int i = 0; i < Ball.preDefCodes.length; i++){
            if(Ball.preDefCodes[i] == code){
                this.ballCode = Ball.preDefCodes[i];
                this.color = Ball.preDefColors[i];
                initiated = 1;
            }
        }
        
        if(initiated == 0){
            System.out.println("WARNING!!! One of the Balls is not initiated properly");
        }
    }
    public void changeBallPosition(float x, float y){
        this.xPos = x;
        this.yPos = y;
    }
    public void setVelocity(double vx, double vy){
        this.vx = vx;
        this.vy = vy;
    }
    public float getX(){
        return this.xPos;
    }
    public float getY(){
        return this.yPos;
    }
    public float getDia(){
        return this.diameter;
    }
    public float getRadius(){
        return this.diameter/2.0f;
    }
    public int getBallNumber(){
        return this.ballId;
    }
    public int getBallCode(){
        return this.ballCode;
    }
    public void showInfo(){        
        System.out.println("Ball number=" + this.ballId + " Code=" + this.ballCode + " Color=" + this.color + " xPos=" + this.xPos + " yPos=" + this.yPos + " Vx=" + this.vx + " Vy1=" + this.vy);      
    }
 
}
