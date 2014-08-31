
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Board {
    private final float width;
    private final float length;
    private final boolean baulkLine; 
    private final float baulkDimention;
    private final float baulkDRadius;
    private Ball ballsArr[];
    
    public Board(float w, float l, boolean ifBaulkLine, float baulkDimention, float baulkDRa){
        this.width = w;
        this.length = l;
        this.baulkLine = ifBaulkLine;
        this.baulkDimention = baulkDimention;
        this.baulkDRadius = baulkDRa;      
    }
    
    public void setBalls(float ballData[][], float ballDia){
        
        this.ballsArr = new Ball[ballData[0].length];
        
        for (int i = 0; i < ballData[0].length ; i++){
            //System.out.print(ballData[0][i] + "   " + ballData[1][i] + "   " + ballData[2][i] + "   " + ballData[3][i]);
            //System.out.println();
            Ball tempObj;
            tempObj = new Ball((int)ballData[0][i], (int)ballData[3][i], ballDia, ballData[1][i], ballData[2][i]);
            
            this.ballsArr[i] = tempObj;
            
        }
        
    }
    
    public void showInfo(){
        System.out.println("Board Width = " + this.width + " Board Height = " + this.length);
        
        for (Ball ballsArr1 : this.ballsArr) {
            Ball tempObj;
            tempObj = ballsArr1;
            tempObj.showInfo();
        } 
    }
    private float getDistance(float x1, float x2, float y1, float y2){    
        double dis =  Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        return (float)dis;
    }
    private boolean ifBallOverlap(Ball b1, Ball b2){
        float dis = this.getDistance(b1.getX(), b2.getX(), b1.getY(), b2.getY());
        return (dis*dis) < ((b1.getRadius() + b2.getRadius()) * (b1.getRadius() + b2.getRadius()));
    }
    private boolean ifBallOnBoard(Ball b1){
       
        if(b1.getX() + b1.getRadius() < 0 || b1.getX() + b1.getRadius() > this.length){
            return false;
        }else if(b1.getX() - b1.getRadius() < 0 || b1.getX() - b1.getRadius() > this.length){
            return false;
        }else if(b1.getY() + b1.getRadius() < 0 || b1.getY() + b1.getRadius() > this.width){
            return false;
        }else if(b1.getY() - b1.getRadius() < 0 || b1.getY() - b1.getRadius() > this.width){
            return false;
        } 
        return true;
    }
    private boolean ifPositionOnBoard(float x, float y){
        return x >= 0 && x <= this.length && y >= 0 && y <= this.width;
    }
    
    private boolean moveBall(Ball ballObj, float x, float y){
        float tempX = ballObj.getX(), tempY = ballObj.getY();
        boolean retVal = true;
        ballObj.changeBallPosition(x, y);
        
        if(!ifBallOnBoard(ballObj)){
            System.out.println("Ball will be out of Board Boundry");
            return false;
        }
        
        for (Ball object : this.ballsArr) {           
            if(object != ballObj){               
                if(ifBallOverlap(object, ballObj)){
                    System.out.println("This Position Overlaps with Ball Number" + object.getBallNumber());
                    retVal = false;
                }          
            }
        }
        
        if(!retVal){
            ballObj.changeBallPosition(tempX, tempY);
        }else{
            System.out.println("Ball Position changed.");
        }
        
        return retVal;
    }
    
    public void moveBallWithNumber(int ballNumber, float x, float y){
        if(!ifPositionOnBoard(x, y)){
            System.out.println("Position out of Bounds.");
            return;
        }
        
        for(Ball object : this.ballsArr){
            if(object.getBallNumber() == ballNumber){
                moveBall(object, x, y);
                return;
            }
        }
        System.out.println("Ball Number is invalid.");       
    }
    private int getWhiteBallNumber(){    
        for(Ball object : this.ballsArr){
            if(object.getBallCode() == 1){               
                return object.getBallNumber();
            }
        }       
        return 0;
    }
    private Point getPositionOfBallWithNumber(int ballNumber){
        Point p = new Point();
        for(Ball object : this.ballsArr){
            if(object.getBallNumber() == ballNumber){
                p.x = object.getX();
                p.y = object.getY();
            }
        }
        return p;
    }
    
    public void hitBall(Point P, double velocity){
        
        int whiteBallNumber = this.getWhiteBallNumber();
        Point whiteBallPos = this.getPositionOfBallWithNumber(whiteBallNumber);
        
        //points where line hits the edges of the board
        //float pointArr[] = this.getBoardExtremePoints(posX, posY, whiteBallPos[0], whiteBallPos[1]);
        //System.out.println("X postion=>"+ pointArr[0] +" Y postion=>" + pointArr[1]);
        //System.out.println("X postion=>"+ pointArr[2] +" Y postion=>" + pointArr[3]);
        
        //closest point from line to brown ball
        //double closest[] = this.closestpointonline(pointArr[0], pointArr[1], pointArr[2], pointArr[3], 889.0f, 737.0f);
        //System.out.println("X postion=>"+ closest[0] +" Y postion=>" + closest[1]);
        
        //using descrete time steps
        double slope = P.getSlope(P, whiteBallPos);       
        double xAtZero = slope * 0 - slope * P.x + P.y; 
        double angle = P.getAngle(P, whiteBallPos, slope);
        this.setVelocityOfBall(this.getVx(velocity, angle), this.getVy(velocity, angle), whiteBallNumber);
        
        //update this white balls position and check for any collision
        
    }
    private double getVx(double velocity, double angle){
        angle = Math.toRadians(angle);
        return velocity * Math.cos(angle);
    }
    private double getVy(double velocity, double angle){
        angle = Math.toRadians(angle);
        return velocity * Math.sin(angle);
    }
    private void setVelocityOfBall(double vx, double vy, int ballNumber){
        for(Ball object : this.ballsArr){
            if(object.getBallNumber() == ballNumber){
                object.setVelocity(vx, vy);
            }
        }
    }
    
    private double[] closestpointonline(float lx1, float ly1, float lx2, float ly2, float x0, float y0){ 
         float A1 = ly2 - ly1; 
         float B1 = lx1 - lx2; 
         double C1 = (ly2 - ly1)*lx1 + (lx1 - lx2)*ly1; 
         double C2 = -B1*x0 + A1*y0; 
         double det = A1*A1 - -B1*B1; 
         double cx; 
         double cy; 
         if(det != 0){ 
            cx = (float)((A1*C1 - B1*C2)/det); 
            cy = (float)((A1*C2 - -B1*C1)/det); 
         }else{ 
            cx = x0; 
            cy = y0; 
         } 
         double pointArr[] = new double[2]; 
         pointArr[0] = cx;
         pointArr[1] = cy;
         
         return pointArr; 
    }


    private float[] getBoardExtremePoints(float x1, float y1, float x2, float y2){
        
        double slope = (y2 - y1)/(x2 - x1);
        
        //left line intersection (0, y)
        double xAtZero = slope * 0 - slope * x1 + y1;       
        //right line intersection  (length, y)
        double xAtLength = slope * this.length - slope * x1 + y1; 
        //bottom line intersection (x , 0)
        double yAtZero = (0 - y1 + slope * x1) / slope;      
        //top line (x, width)
        double yAtWidth = (this.width - y1 + slope * x1) / slope;
         
        //now get the two points where they intersect
        float pointArr[] = new float[4];
        int pointArrIndex = 0;
        int pointCount = 0;
        if(xAtZero >=0 && xAtZero <= this.width && pointCount < 2){
            pointArr[pointArrIndex++] = 0.0f;
            pointArr[pointArrIndex++] = (float)xAtZero;
            pointCount++;
        }
        if(xAtLength >=0 && xAtLength <= this.width && pointCount < 2){
            pointArr[pointArrIndex++] = this.length;
            pointArr[pointArrIndex++] = (float)xAtLength;
            pointCount++;
        }
        if(yAtWidth >=0 && yAtWidth <= this.length && pointCount < 2){
            pointArr[pointArrIndex++] = (float)yAtWidth;
            pointArr[pointArrIndex++] = this.width;
            pointCount++;
        }
        if(yAtZero >=0 && yAtZero <= this.length && pointCount < 2){
            pointArr[pointArrIndex++] = (float)yAtZero;
            pointArr[pointArrIndex++] = 0.0f;
            pointCount++;
        }
               
        return pointArr;
    }
    
}
