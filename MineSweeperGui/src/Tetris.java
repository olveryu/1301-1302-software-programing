import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**Represent the Tetris GameBoard that creates a fully functional MineSweeper game.
*
*@author Wenhao Lin
*@author RuiJie Xu
*@since 2018-04-25 
*/
public class Tetris extends BorderPane{
	int score=0;
	int shape = 0;
	boolean hasSpace = true;
	public Timeline timeline = new Timeline();
	public static Button otherGames = new Button("Other Games");
	public static Button restart = new Button("Restart");
	public static Button highScore = new Button("High Score");
	public int time=0;
	public Text timeText= new Text(90,60,Integer.toString(time));
	public String name = new String("");
	//to store color info

	
	int[][] internalColorBoard = {{0,0,0,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0},	
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0},
			                      {0,0,0,0,0,0,0,0,0,0}};
	//to store shapes info
	int[][] internalBoard = {{0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},	
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0},
							 {0,0,0,0,0,0,0,0,0,0}};
	//for later tool bar
	VBox scoreBoard = new VBox(50);	
	
	//main game board
	GridPane gameBoard = new GridPane();
	
	/**
     * Construct an object instance of the TetrisBoard class.
     */
		public Tetris() {
			//start counting time for score
			time();
			
			
			
			//set tool bar on borderPane
			setRight(scoreBoard);
			
			//score  and timeText style
			HBox showScore = new HBox();
			showScore.setMinHeight(50);
			showScore.setMinWidth(90);
			showScore.setStyle("-fx-alignment:center; -fx-background-color: black");
			timeText.setFill(Color.RED);
			timeText.setStyle(" -fx-font-size:22;");
			showScore.getChildren().add(timeText);
			
			highScore.setOnAction(e->{
				highScoreWindow();
			});
			
			//set up content in toolBar
			scoreBoard.setPadding(new Insets(50,20,20,20));
			scoreBoard.getChildren().addAll(showScore,otherGames,restart, highScore);
			
			
			gameBoard.setGridLinesVisible(true);
			//construct the visual gameBoard with stackPane
			for(int i = 0; i <internalBoard.length; i++) {
				for(int j= 0 ; j <internalBoard[0].length; j++) {
					Rectangle s = new Rectangle(45,45);
					s.setStroke(Color.BLACK);
					s.setFill(Color.LAVENDER);
					gameBoard.add(s,j,i);//add square to gameBoard one by one
				}//for
			}//for
		
		//set the gameboard to the middle of the pane	
		setCenter(gameBoard);
		//call to make a newshape object before star
		newShape();
		//start refreshing the gameboard
		timer();
		//start dropping down the shapes
		downTimer();
		
 		}//TetrisBoard
		
		//handler for update whole board 
		EventHandler<ActionEvent> handler = event -> {
			for(int i = 0; i <internalColorBoard.length; i++) {
				for(int j= 0 ; j <internalColorBoard[0].length; j++) {
					Rectangle s = new Rectangle(45,45);
					if(internalColorBoard[i][j] == 0) {
						s.setStroke(Color.BLACK);
						s.setFill(Color.LAVENDER);
					}
					if(internalColorBoard[i][j]==1){
						s.setFill(Color.CYAN);
					}
					if(internalColorBoard[i][j]==2){
						s.setFill(Color.BLUE);
					}
					if(internalColorBoard[i][j]==3){
						s.setFill(Color.ORANGE);
					}
					if(internalColorBoard[i][j]==4){
						s.setFill(Color.YELLOW);
					}
					if(internalColorBoard[i][j]==5){
						s.setFill(Color.GREEN);
					}
					if(internalColorBoard[i][j]==6){
						s.setFill(Color.PURPLE);
					}
					if(internalColorBoard[i][j]==7){
						s.setFill(Color.RED);
					}
					gameBoard.add(s,j,i);//add square to gameBoard one by one
				}//for
			}//for
		};//handler
		
		//handler for dropping down shapes
		EventHandler<ActionEvent> downHandler = event -> {
			//new thread for concurrency
			 Thread t2 = new Thread(() -> {
			//check if there is space to go down
			for(int i = 17; i >= 0 ; i--) {
				for(int j = 0; j < 10; j++) {
					if(internalBoard[i][j] == 1) {
						if(i+1>17 || internalBoard[i+1][j]==-1 ) {
							hasSpace=false;
						}//if
					}//if
				}//for
			}//for
	
			
			//if no space to move down, set square equal to -1 
			if(hasSpace==false) {
				for(int i = 17; i >= 0 ; i--) {
					for(int j = 0; j < 10; j++) {
						if(internalBoard[i][j]==1) {
							internalBoard[i][j]=-1;
						};
					}//for
				}//for
				newShape();//is shape got stuck, call new shape
				//if
			}//if
			
			//go down
			if(hasSpace) {
				for(int i = 17; i >= 0 ; i--) {
					for(int j = 0; j < 10; j++) {
						if(i+1 <=17 && internalBoard[i][j]==1 && internalBoard[i+1][j]==0) {
							internalBoard[i][j]=0;
							internalBoard[i+1][j]=1;
							internalColorBoard[i][j]=0;
							internalColorBoard[i+1][j]=shape;
						}//if
					}//for
				}//for
			}//if
			hasSpace=true;
			 });//thread
			 t2.setDaemon(true);
			 t2.start();
		};//downHandler
		

		
		/**
		 *This method creates a random new shape also checks for if there is space to 
		 *draw the new shape, if not, game Over 
		 */
		public void newShape() {	
			Random r = new Random();
			int shape = r.nextInt(7) + 1;
			this.shape = shape;
			
			//create the shape that correspond to the random number
			switch(shape) {
			case 1:
					for(int i = 3; i < 7; i++) {
						if(internalBoard[0][i]==-1) {
							score=time;
							lose(); //check for game over
							
						}
						internalBoard[0][i]=1;
						internalColorBoard[0][i]=1;
					}//for
					setCenter(gameBoard);
					break;
			case 2:
					for(int i = 3; i < 6; i++) {
						//check for game over
						if(internalBoard[0][i]==-1) {
							score=time;
							lose();
							
						}
						
						internalBoard[0][i]=1;
						internalColorBoard[0][i]=2;
					}//for
					//check for game over
					if(internalBoard[1][5]==-1) {
						score=time;
						lose();
						
					}
					internalBoard[1][5]=1;
					internalColorBoard[1][5]=2;
					setCenter(gameBoard);
					break;
			case 3:
					for(int i = 3; i < 6; i++) {
						//check for game over
						if(internalBoard[0][i]==-1) {
							score=time;
							lose();
							
						}
						internalBoard[0][i]=1;
						internalColorBoard[0][i]=3;
					}//for
					//check for game over
					if(internalBoard[1][3]==-1) {
						score=time;
						lose();
						
					}
					internalBoard[1][3]=1;
					internalColorBoard[1][3]=3;
					setCenter(gameBoard);
					break;
			case 4:
					for(int i =0; i <2; i++) {
						for(int j=4; j<6; j++) {
							//check for game over
							if(internalBoard[i][j]==-1) {
								score=time;
								lose();
								
							}
							internalBoard[i][j]=1;
							internalColorBoard[i][j]=4;
						}//for
					}//for
					setCenter(gameBoard);
					break;
			case 5:
					for(int i=4; i<6; i++) {
						//check for game over
						if(internalBoard[0][i]==-1) {
							score = time;
							lose();
							
						}
						internalBoard[0][i]=1;
						internalColorBoard[0][i]=5;
					}//for
					for(int i=3; i<5; i++) {
						//check for game over
						if(internalBoard[1][i]==-1) {
							score = time;
							lose();
							
						}
						internalBoard[1][i]=1;
						internalColorBoard[1][i]=5;
					}//for
					setCenter(gameBoard);
					break;
			case 6:
				for(int i = 3; i < 6; i++) {
					//check for game over
					if(internalBoard[0][i]==-1) {
						score = time;
						lose();
						
					}
					internalBoard[0][i]=1;
					internalColorBoard[0][i]=6;
				}//for
				//check for game over
				if(internalBoard[1][4]==-1) {
					score = time;
					lose();
					
				}
				internalBoard[1][4]=1;
				internalColorBoard[1][4]=6;
				setCenter(gameBoard);
				break;
			case 7:
				for(int i=3; i<5; i++) {
					//check for game over
					if(internalBoard[0][i]==-1) {
						score = time;
						lose();
						
					}
					internalBoard[0][i]=1;
					internalColorBoard[0][i]=7;
				}//for
				for(int i=4; i<6; i++) {
					//check for game over
					if(internalBoard[1][i]==-1) {
						score = time;
						lose();
						
					}
					internalBoard[1][i]=1;
					internalColorBoard[1][i]=7;
				}//for
				setCenter(gameBoard);
				break;
			}//switch
		}//newShape
		
		/**
	      *This method create a lose mode for the game that stops the timer and create an pop up window
	      *that displays the some text to inform player that they have lost.
	      */
		public void lose() {
			
			timeline.pause();
			
			
			//prompt to ask for initials
			Text askName = new Text("Please enter your initial: ");
			//takes user's initials
			TextField initial = new TextField("enter initial");
			
			Stage loseStage = new Stage();
			
			Button submit = new Button("submit");
			submit.setOnAction(e->{
				name=initial.getText().trim();//take the initial from all winning users
				loseStage.close();
			});
			
			Label loseLabel = new Label("GameOver");
			
			
			
			
			
			
			
			//button to valid initials in the textField
			
	
			loseStage.setMinWidth(100);
			loseStage.setMinHeight(100);
			VBox vbox = new VBox();
			Scene scene = new Scene (vbox);
			loseStage.setScene(scene);
			
			loseStage.initModality(Modality.APPLICATION_MODAL);//block other window before this window is handled
			loseStage.setTitle("YOU LOST");
			loseStage.setWidth(250);
			loseStage.setHeight(300);
			vbox.getChildren().addAll(loseLabel, askName, initial, submit);
			vbox.setAlignment(Pos.CENTER);
			loseStage.sizeToScene();
			loseStage.showAndWait();
			
			
		}//lose
		
	
		/**
		 *This method start a timeline for updating the whole board every half 
		 *second and the CycleCount is INDEFINITE
		 */
		public void timer() {
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), handler);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		    }//timer
		/**
		 *This method start a timeline for dropping down the shape every half
		 *second and the CycleCount is INDEFINITE
		 */
		public void downTimer() {
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.45), downHandler);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		}
		
		/**
		 *This method Update the board, if some key events are detected,
		 *this method will be call for immediate game board update to avoid delay
		 *in the visual aspect of the game
		 */
		public void update() {
			for(int i = 0; i <internalColorBoard.length; i++) {
				for(int j= 0 ; j <internalColorBoard[0].length; j++) {
					Rectangle s = new Rectangle(45,45);
					if(internalColorBoard[i][j] == 0) {
						s.setStroke(Color.BLACK);
						s.setFill(Color.LAVENDER);
					}
					if(internalColorBoard[i][j]==1){
						s.setFill(Color.CYAN);
					}
					if(internalColorBoard[i][j]==2){
						s.setFill(Color.BLUE);
					}
					if(internalColorBoard[i][j]==3){
						s.setFill(Color.ORANGE);
					}
					if(internalColorBoard[i][j]==4){
						s.setFill(Color.YELLOW);
					}
					if(internalColorBoard[i][j]==5){
						s.setFill(Color.GREEN);
					}
					if(internalColorBoard[i][j]==6){
						s.setFill(Color.PURPLE);
					}
					if(internalColorBoard[i][j]==7){
						s.setFill(Color.RED);
					}
					gameBoard.add(s,j,i);//add square to gameBoard one by one
				}//for
			}//for
		}//update
		
		public void time() {
			EventHandler<ActionEvent> handler = event -> {
				timeText.setText(Integer.toString(time));
				time ++;
			
			};

			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), handler);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		}
		
		public void highScore() {
			
			for (int i =0; i< 10; i++) {
					
					if (score > ArcadeApp.tetrisHighScore[i]) {//score higher than the number in the array
						int temp = ArcadeApp.tetrisHighScore[i];//save the current value in the array
						String tempName = ArcadeApp.winnerNamesTetris[i];//save the current string in the array
						ArcadeApp.tetrisHighScore[i]=score;//current position of the array equals to the score
						ArcadeApp.winnerNamesTetris[i]=name;
						for (int j = 10-1; j> i; j--) {//this array starts from the back, pushes every array after the insertion back one spot
							ArcadeApp.tetrisHighScore[j]=ArcadeApp.tetrisHighScore[j-1];
							ArcadeApp.winnerNamesTetris[j]=ArcadeApp.winnerNamesTetris[j-1];
								if (j==i+1) {//at the pos one after the beginnig array, pos = temp saved in the beginning of the array, the old value of the current pos
									ArcadeApp.tetrisHighScore[j]=temp;
									ArcadeApp.winnerNamesTetris[j]=tempName;
								}	
						}
						
						break;	//find pos to insert, escape out of the loop
					}
					}
			}
		public void highScoreWindow() {
			 
			 VBox vbox = new VBox();
			 
			 Text rank =new Text("High Score Board");
			 rank.setStyle("-fx-font: 20 impact;");
			 vbox.getChildren().add(rank);
			 for(int i =0; i< ArcadeApp.tetrisHighScore.length; i++) {
					HBox ranking = new HBox();//put all text in a row
				 	Text text1 = new Text(i+1 +".\t");//set numbers
					Text text2 = new Text("");
					Text text3 = new Text("");
					if(ArcadeApp.winnerNamesTetris[i]!=null) {//if not empty, then set text
					 text2 = new Text(ArcadeApp.winnerNamesTetris[i]+"\t");
					 text3 = new Text(ArcadeApp.tetrisHighScore[i]+ "");
					}
					
					
					ranking.getChildren().addAll(text1,text2,text3);
					vbox.getChildren().add(ranking);
					
				}
			 
			 Stage highScoreW = new Stage();
			 highScoreW.initModality(Modality.APPLICATION_MODAL);//block other window before this window is handled
			 highScoreW.setTitle("High Score Board");
			 highScoreW.setMinWidth(80);
			 highScoreW.setMinHeight(100);
			 Scene newScene = new Scene(vbox);
			 highScoreW.setScene(newScene);
			 highScoreW.sizeToScene();
			 highScoreW.showAndWait();
			 
			 
		 }


	}//BorderPane

