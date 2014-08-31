
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Snooker {
    private Board board;
   
    //width, length, ball Diameter, baulk-line, baulkD
    private static final float boardDimentions[] = {3569.0f, 1778.0f, 52.5f, 737.0f, 292.0f};
   
    //1st row ball codes
    //2nd row number of that ball
    private static final int Balls[][] = {
                                            {1, 2,  3, 4, 5, 6, 7, 8},
                                            {1, 15, 1, 1, 1, 1, 1, 1}
                                         };
   
    public Snooker(){
        //set the board
        this.board = new Board(Snooker.boardDimentions[0], Snooker.boardDimentions[1], true, Snooker.boardDimentions[3], Snooker.boardDimentions[4]);
        
        //set the ball positions on board
        this.initBallPos();              
        this.initGame();   
    }
    private void initGame(){
        int option; 
        Scanner in = new Scanner(System.in);       
        do{
            System.out.println("Please choose one of the following option");
            System.out.println("1: Show Ball Details");
            System.out.println("2: Move Ball");
            System.out.println("3: Hit Ball");
            System.out.println("4: Exit");
            option = in.nextInt();
            
            float xPos, yPos;
            switch(option){
                case 1:                   
                    this.board.showInfo();
                    break;
                case 2:
                    int ballNum;                   
                    System.out.println("Enter Ball-Number X postion and Y postion:");
                    ballNum = in.nextInt();
                    xPos = in.nextFloat();
                    yPos = in.nextFloat();
                    System.out.println("===================================================================================");
                    this.board.moveBallWithNumber(ballNum, xPos, yPos);
                    System.out.println("===================================================================================");
                    break;
                case 3:                    
                    System.out.println("Enter X postion and Y postion to Hit:");
                    xPos = in.nextFloat();
                    yPos = in.nextFloat();
                    System.out.println("===================================================================================");
                    this.board.hitBall(xPos, yPos);
                    System.out.println("===================================================================================");
                    break;
                case 4:
                    //System.out.println("option3!!!!!");
                    break;
                default:
                    System.out.println("ERROR: Invalid input");
                    break;
            }
       }while(option != 4);
    }
    private void initBallPos(){
        float ballData[][] = new float[4][22];
        
        //give all balls position and set them on board
        int count = 0, ballId = 0;
        for(int i = 0; i < Snooker.Balls[0].length; i++){           
            
            if(Snooker.Balls[0][i] == 2){
                //for red balls
                ballData[0][count] = 2;
                ballData[3][count] = ++ballId;
                ballData[1][count] = (Snooker.boardDimentions[1] / 2);
                ballData[2][count] = (Snooker.boardDimentions[0] * (0.75f)) + Snooker.boardDimentions[2]; //1st red ball above pink
                count++;
                
                //45.466 is vertical distance between red balls
                for(int j = 2; j <= 5; j++){
                    float startPoint = (Snooker.boardDimentions[1] / 2) - (Snooker.boardDimentions[2] * j - Snooker.boardDimentions[2])/2;                    
                    for(int k = 1; k <= j; k++){
                        ballData[0][count] = 2;
                        ballData[3][count] = ++ballId;
                        ballData[1][count] = startPoint + (k-1) * Snooker.boardDimentions[2];
                        ballData[2][count] = ((Snooker.boardDimentions[0] * (0.75f)) + Snooker.boardDimentions[2]) + 45.466333f * (j - 2);
                        count++;
                    }                    
                }
                count--;
            }else if(Snooker.Balls[0][i] == 1){ //cue ball
                ballData[0][count] = 1;
                ballData[3][count] = ++ballId;
                //ballData[1][count] = (Snooker.boardDimentions[1] / 2);
                //ballData[2][count] = Snooker.boardDimentions[3] - Snooker.boardDimentions[2];
                
                ballData[1][count] = 780.0f;
                ballData[2][count] = 684.0f;
            }else if(Snooker.Balls[0][i] == 3){
                ballData[0][count] = 3;
                ballData[3][count] = ++ballId;
                ballData[1][count] = (Snooker.boardDimentions[1] / 2) + Snooker.boardDimentions[4];
                ballData[2][count] = Snooker.boardDimentions[3];
            }else if(Snooker.Balls[0][i] == 4){
                ballData[0][count] = 4;
                ballData[3][count] = ++ballId;
                ballData[1][count] = (Snooker.boardDimentions[1] / 2) - Snooker.boardDimentions[4];
                ballData[2][count] = Snooker.boardDimentions[3];     
            }else if(Snooker.Balls[0][i] == 5){
                ballData[0][count] = 5;
                ballData[3][count] = ++ballId;
                ballData[1][count] = (Snooker.boardDimentions[1] / 2);
                ballData[2][count] = Snooker.boardDimentions[3];
            }else if(Snooker.Balls[0][i] == 6){
                ballData[0][count] = 6;
                ballData[3][count] = ++ballId;
                ballData[1][count] = (Snooker.boardDimentions[1] / 2);
                ballData[2][count] = (Snooker.boardDimentions[0] / 2);
            }else if(Snooker.Balls[0][i] == 7){
                ballData[0][count] = 7;
                ballData[3][count] = ++ballId;
                ballData[1][count] = (Snooker.boardDimentions[1] / 2);
                ballData[2][count] = (Snooker.boardDimentions[0] * (0.75f));
            }else if(Snooker.Balls[0][i] == 8){
                ballData[0][count] = 8;
                ballData[3][count] = ++ballId;
                ballData[1][count] = (Snooker.boardDimentions[1] / 2);
                ballData[2][count] = (Snooker.boardDimentions[0] - 324.0f);
            }
            count++;    
        } 
        
        this.board.setBalls(ballData, Snooker.boardDimentions[2]);
        
    }

}
