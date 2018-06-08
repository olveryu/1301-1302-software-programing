import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	int score;
	int shape = 0;//shape index
	int centerRow;//center index of a shape
	int centerCol;//center index of a shape
	double duration = 0.7;//draw down shapes wait time 
	int level = 1;
	int second = 0;
	boolean pause = false;
	boolean keepGoing = true;
	boolean hasSpace = true;
	boolean lose = false;
	int turned;
	public  Timeline timeline = new Timeline();
	public  Timeline timeline_2 = new Timeline();
	public  Timeline timeline_3 = new Timeline();
	int row = 18;
	int col = 10;
	public Timeline timeline_4 = new Timeline();
	public static Button otherGames = new Button("Other Games");
	public static Button restart = new Button("Restart");
	public static Button highScore = new Button("High Score");
	public int time=0;
	public Text timeText= new Text(90,60,Integer.toString(time));
	public Text levelText= new Text(90,60,Integer.toString(level));
	public Text scoreText = new Text("your score:" + score);
	public String name = new String("");
	TetrisBox[][] board;//array to store each square boxes
	 KeyFrame keyFrame;
	
	
	//for later tool bar
	VBox scoreBoard = new VBox(40);	
	//main game board
	GridPane gameBoard = new GridPane();
	
	/**
     * Construct an object instance of the TetrisBoard class.
     */
	public Tetris() {
		//ask user for initial
		askForName();
		
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
		
		//update with the current level text
		Label levelLabel = new Label("level: ");
		levelLabel.setTextFill(Color.RED);
		levelLabel.setStyle(" -fx-font-size:22;");
		HBox levelBox = new HBox();
		levelBox.setMinHeight(50);
		levelBox.setMinWidth(90);
		levelBox.setStyle("-fx-alignment:center; -fx-background-color: black");
		levelText.setFill(Color.RED);
		levelText.setStyle(" -fx-font-size:22;");
		levelBox.getChildren().addAll(levelLabel,levelText);//current level game on
		
		//instruction on how to play the game
		HBox instrBox = new HBox();
		Text instructions = new Text();
		instructions.setText("Instructions: \n Press ENTER to pause the game \n Press UP to rotate \n Press LEFT/RIGHT to shift horizontally \n Hold DOWN to speed up");
		instrBox.setMinHeight(50);
		instrBox.setMinWidth(90);
		instrBox.setStyle("-fx-alignment:center; -fx-background-color: lightblue");
		instructions.setFill(Color.BLACK);
		instructions.setStyle(" -fx-font-size:12;");
		instrBox.getChildren().addAll(instructions);
		
		//prints user score
		HBox scoreGame = new HBox();
		scoreGame.setMinHeight(50);
		scoreGame.setMinWidth(90);
		scoreGame.setStyle("-fx-alignment:center; -fx-background-color: black");
		scoreText.setFill(Color.RED);
		scoreText.setStyle(" -fx-font-size:22;");
		scoreGame.getChildren().addAll(scoreText);
		
		//shows high score window
		highScore.setOnAction(e->{
			highScoreWindow();
		});
		
		//set up content in toolBar
		scoreBoard.setPadding(new Insets(50,20,20,20));
		scoreBoard.getChildren().addAll(instrBox,scoreGame,showScore,levelBox, otherGames,restart, highScore);
	
		gameBoard.setGridLinesVisible(true);
		board = new TetrisBox[row][col];//initialize array dimention 
		//add box to the gameBoard
		for(int i = 0; i <row; i++) {
			for(int j= 0 ; j <col; j++) {
				TetrisBox box = new TetrisBox();
				board[i][j] = box;
				box.paint();
				gameBoard.add(box,j,i);//add square to gameBoard one by one
			}//for
		}//for
		
		//set the gameboard to the middle of the pane	
		setCenter(gameBoard);
		//call to make a newshape object before star
		newShape();
		//start dropping down the shapes
		downTimer();
 		}//TetrisBoard Constructor
		
		/**
		 *This method start a timeline for dropping down the shape every 0.7
		 *second and the CycleCount is INDEFINITE
		 */
		public void downTimer() {
			keyFrame = new KeyFrame(Duration.seconds(duration), downHandler);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		}//downTimer
		
		//handler for dropping down shapes
		EventHandler<ActionEvent> downHandler = event -> {
			second ++;
				//check if there is space to go down
				for(int i = row-1; i >= 0 ; i--) {
					for(int j = 0; j < col; j++) {
						if(board[i][j].liveBox == true) {
							if(i+1>17 || board[i+1][j].hasBox==true ) {
								hasSpace=false;
							}//if
						}//if
					}//for
				}//for
			
					
				//if no space to move down, set square equal to -1 
				if(hasSpace==false) {
					for(int i = 17; i >= 0 ; i--) {
						for(int j = 0; j < 10; j++) {
							if(board[i][j].liveBox==true) {
								board[i][j].liveBox=false;
								board[i][j].hasBox=true;
							};
						}//for
					}//for
					cleanRow();//if shape got stuck, call cleanRow
					newShape();//if shape got stuck, call new shape
				}//if
					
				//if there is space to drop down
				if(hasSpace) {
					centerRow++;
					for(int i = 17; i >= 0 ; i--) {
						for(int j = 0; j < 10; j++) {
							if(board[i][j].liveBox==true) {
								board[i][j].liveBox=false;
								board[i+1][j].liveBox=true;
								board[i][j].shapeNum=0;
								board[i+1][j].shapeNum=shape;
								board[i][j].paint();
								board[i+1][j].paint();
							}//if
						}//for
					}//for
				}//if
				hasSpace=true;
				
				//check for level up
				if(second == 35) {
					second = 0;
					level ++;
					duration = duration-0.1;
					levelUp();
				}//if
			};//downHandler
			
			//speed up method
			public void speedUp() {
				//check if there is space to go down
				for(int i = row-1; i >= 0 ; i--) {
					for(int j = 0; j < col; j++) {
						if(board[i][j].liveBox == true) {
							if(i+1>17 || board[i+1][j].hasBox==true ) {
								hasSpace=false;
							}//if
						}//if
					}//for
				}//for
			
					
				//if no space to move down,execute this block of code
				if(hasSpace==false) {
					for(int i = 17; i >= 0 ; i--) {
						for(int j = 0; j < 10; j++) {
							if(board[i][j].liveBox==true) {
								board[i][j].liveBox=false;
								board[i][j].hasBox=true;
							};
						}//for
					}//for
					cleanRow();//if shape got stuck ,call cleanRow
					newShape();//if shape got stuck, call new shape
				}//if
					
				//if there is space to drop down
				if(hasSpace) {
					centerRow++;
					for(int i = 17; i >= 0 ; i--) {
						for(int j = 0; j < 10; j++) {
							if(board[i][j].liveBox==true) {
								board[i][j].liveBox=false;
								board[i+1][j].liveBox=true;
								board[i][j].shapeNum=0;
								board[i+1][j].shapeNum=shape;
								board[i][j].paint();
								board[i+1][j].paint();
							}//if
						}//for
					}//for
				}//if
				hasSpace=true;
			}//speed up
			
			/**
			 *This recursive method clears one row at a time if it is full and
			 *calls itself if there is more rows that can be cleared 
			 */
			public void cleanRow() {
				boolean clear = false;
				int row = 0 ;
				//check if there is a full row
				for(int i = 0; i <=this.row-1; i++) {
					boolean hasIt = true;
					for(int j = 0; j<this.col; j++) {
						if(board[i][j].hasBox==false) {
							hasIt = false;
							break;
						}//if
					}//for
					//if the board contains a full row
					if(hasIt==true) {
						row=i;
						clear=true;
					break;
					}//if
				}//for
				//clear the full row	
				if (clear==true) {
					for(int i = row; i>1; i --) {
						for(int j = 0; j<col;j++) {
							board[i][j].hasBox=board[i-1][j].hasBox;
							board[i][j].liveBox=board[i-1][j].liveBox;								board[i][j].shapeNum= board[i-1][j].shapeNum;
							board[i][j].paint();
						}//for
					}//for			
					score=score+10;//add 10 points
					cleanRow();//call itself again to check if there is more full row 
					scoreText.setText("your score:" + score);
					}//if
					//simply return if no full row to clear
					if(clear==false) {
						return;
					}//if
				}//cleanRow
	

				
		/**
		 *This method produces a random new Tetromino
		 */
		public void newShape() {	
			Random r = new Random();
			int shape = r.nextInt(7) + 1;
			this.shape = shape;
			
			//create the shape that correspond to the random number
			switch(shape) {
			case 1:
				//set center coordinate
				turned=1;
				centerRow = 0;
				centerCol = 4;
				//check for lose
				if(keepGoing) {
					for(int i = 3; i < 7; i++) {
						if(board[0][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//draw hape
				if(keepGoing) {
					for(int i = 3; i < 7; i++) {
						board[0][i].liveBox=true;//set true
						board[0][i].shapeNum=1;//set color code
						board[0][i].paint();//show
					}//for
				}//if
				break;
			case 2:
				//set center coordinate
				turned=1;
				centerRow = 0;
				centerCol = 4;
				//check for game over
				if(keepGoing) {
					if(board[1][5].hasBox==true) {
						keepGoing = false;
						lose();
					}//if
				}//if
				//paint first spot
				if(keepGoing) {
					board[1][5].liveBox=true;//set true
					board[1][5].shapeNum=2;//set color code
					board[1][5].paint();//show
				}//if
					//check for game over
				if(keepGoing) {
					for(int i = 3; i < 6; i++) {
						if(board[0][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//draw shape
				if(keepGoing) {
					for(int i = 3; i < 6; i++) {
						board[0][i].liveBox=true;//set true
						board[0][i].shapeNum=2;//set color code
						board[0][i].paint();//show
					}//for
				}//if
					break;
			case 3:
				//set center coordinate
				turned=1;
				centerRow = 0;
				centerCol = 4;
				//check for game over
				if(keepGoing) {
					if(board[1][3].hasBox==true) {
						keepGoing = false;
						lose();
					}//if
				}//if
				if(keepGoing) {
					board[1][3].liveBox=true;//set true
					board[1][3].shapeNum=3;//set color code
					board[1][3].paint();//show
				}//if
				//check for game over
				if(keepGoing) {
					for(int i = 3; i < 6; i++) {
						if(board[0][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//drawn shape
				if(keepGoing) {
					for(int i = 3; i < 6; i++) {
						board[0][i].liveBox=true;//set true
						board[0][i].shapeNum=3;//set color code
						board[0][i].paint();//show
					}//for
				}//if
				break;
			case 4:
				//check for game over
				if(keepGoing) {
						for(int j=4; j<6; j++) {
							if(board[0][j].hasBox==true) {
								keepGoing = false;
								lose();
								break;
							}//if
					}//for
				}//if
				//paint fist row
				if(keepGoing) {
						for(int j=4; j<6; j++) {
							board[0][j].liveBox=true;
							board[0][j].shapeNum=4;
							board[0][j].paint();//show
						}//for
				}//if
				//check for game over
				if(keepGoing) {
						for(int j=4; j<6; j++) {
							if(board[1][j].hasBox==true) {
								keepGoing = false;
								lose();
								break;
							}//if
						}//for
				}//if
				//paint first row
				if(keepGoing) {
						for(int j=4; j<6; j++) {
							board[1][j].liveBox=true;
							board[1][j].shapeNum=4;
							board[1][j].paint();//show
						}//for
				}//if
				break;
			case 5:
				//set center coordinate
				turned=1;
				centerRow = 1;
				centerCol = 4;
				//check for game over
				if(keepGoing) {
					//check for game over
					for(int i = 4; i < 6; i++) {
						if(board[0][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//paint first row
				if(keepGoing) {
					for(int i=4; i<6; i++) {
						board[0][i].liveBox=true;
						board[0][i].shapeNum=5;
						board[0][i].paint();//show
					}//for
				}
				//check for gameOver
				if(keepGoing) {
					for(int i = 3; i < 5; i++) {
						if(board[1][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//paint second row
				if(keepGoing) {
					for(int i=3; i<5; i++) {
						board[1][i].liveBox=true;
						board[1][i].shapeNum=5;
						board[1][i].paint();//show
					}//for
				}//if
					break;
			case 6:
				//set center coordinate
				turned=1;
				centerRow = 0;
				centerCol = 4;
				//check for game over
				if(keepGoing) {
					if(board[1][4].hasBox==true) {
						keepGoing = false;
						lose();
					}//if
				}//if
				//paint first spot
				if(keepGoing) {
					board[1][4].liveBox=true;//set true
					board[1][4].shapeNum=6;//set color code
					board[1][4].paint();//show
				}//if
					//check for game over
				if(keepGoing) {
					for(int i = 3; i < 6; i++) {
						if(board[0][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//draw shape
				if(keepGoing) {
					for(int i = 3; i < 6; i++) {
						board[0][i].liveBox=true;//set true
						board[0][i].shapeNum=6;//set color code
						board[0][i].paint();//show
					}//for
				}//if
					break;
			case 7:
				//set center coordinate
				turned=1;
				centerRow = 1;
				centerCol = 4;
				//check for game over
				if(keepGoing) {
					//check for game over
					for(int i = 3; i < 5; i++) {
						if(board[0][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//paint first row
				if(keepGoing) {
					for(int i=3; i<5; i++) {
						board[0][i].liveBox=true;
						board[0][i].shapeNum=7;
						board[0][i].paint();//show
					}//for
				}
				//check for gameOver
				if(keepGoing) {
					for(int i = 4; i < 6; i++) {
						if(board[1][i].hasBox==true) {
							keepGoing = false;
							lose();
							break;
						}//if
					}//for
				}//if
				//paint second row
				if(keepGoing) {
					for(int i=4; i<6; i++) {
						board[1][i].liveBox=true;
						board[1][i].shapeNum=7;
						board[1][i].paint();//show
					}//for
				}//if
					break;
			}//switch
		}//newShape
	
	/**
	 *This method increases game levels by shorten the wait between each drop
	 *of tetrominos
	 */
	public void levelUp() {
		//only 3 levels allowed
		//level 2
		if(level ==2) {
			timeline.pause();//pause original timeline
			//level 2 speed
			keyFrame = new KeyFrame(Duration.seconds(duration), downHandler);
			timeline_2.setCycleCount(Timeline.INDEFINITE);
			timeline_2.getKeyFrames().add(keyFrame);
			timeline_2.play();
			levelText.setText(Integer.toString(level));
		}
		if(level ==3) {
			timeline_2.pause();//pause original timeline
			//level 3 speed
			keyFrame = new KeyFrame(Duration.seconds(duration), downHandler);
			timeline_3.setCycleCount(Timeline.INDEFINITE);
			timeline_3.getKeyFrames().add(keyFrame);
			timeline_3.play();
			levelText.setText(Integer.toString(level));
		}
	}//levelUp
	
	/**
	 * This method stops the game and and enters a game lose mode
	 */
	private void lose() {
		timeline_3.pause();//pause the last level timeline
		timeline_4.pause();//pause timer timeline
		lose = true;
		highScore();//arrange the score into high score window
		scoreText.setText("GAME OVER!!! \n"
				+ "Your final score: "+ score );		
		}//lose

	/**This class creates a rectangle Tetris box to be added to the tetris board
	*each box has three attributes, and integer shapeNum represents a number corresponding
	*to a color, boolean liveBox indicating whether the box is in motion and boolean
	*hasBox indication whether a box is placed fixed.
	*
	*@author Wenhao Lin
	*@author RuiJie Xu
	*@since 2018-04-25 
	*/
	public class TetrisBox extends Rectangle{
		public int shapeNum = 0;//shape corresponding number
		public boolean liveBox = false;//whether the box is movable
		public boolean hasBox = false;
		
		TetrisBox(){
			super(45,45);//set dimension
			
			setStroke(Color.BLACK);
		}//Constructor
		//shows proper color of a box according to its number correspondence 
		public void paint() {
			switch(shapeNum) {
			case 0:
				setFill(Color.LAVENDER);
				break;
			case 1:
				setFill(Color.CYAN);
				break;
			case 2:
				setFill(Color.BLUE);
				break;
			case 3:
				setFill(Color.ORANGE);
				break;
			case 4:
				setFill(Color.YELLOW);
				break;
			case 5:
				setFill(Color.GREEN);
				break;
			case 6:
				setFill(Color.PURPLE);
				break;
			case 7:
				setFill(Color.RED);
				break;
			}//switch
		}//paint
	}//TetrisBox

	/**
	 * this method keeps insert the new score into the high score list if there is any 
	 * score in the high score list is less than the newest score. if not, then no score will be
	 * inserted.
	 */
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
					}//if	
				}//for
				break;	//find pos to insert, escape out of the loop
			}//if
		}//for
	}//highScore
	
	/**
	 * This method shows the highscore board window. all ten high score stored
	 * in the high score array and their corresponding initials will show on 
	 * this window.
	 */
	public void highScoreWindow() {
		 
		 VBox vbox = new VBox();
		 
		 Text rank =new Text("High Score Board");
		 rank.setStyle("-fx-font: 20 impact;");
		 vbox.getChildren().add(rank);
		//iterate thru the high score array and print out the saved name and score
		 for(int i =0; i< ArcadeApp.tetrisHighScore.length; i++) {
			 HBox ranking = new HBox();//put all text in a row
			 Text text1 = new Text(i+1 +".\t");//set numbers
			 Text text2 = new Text("");
			 Text text3 = new Text("");
			 if(ArcadeApp.winnerNamesTetris[i]!=null) {//if not empty, then set text
				 text2 = new Text(ArcadeApp.winnerNamesTetris[i]+"\t");
				 text3 = new Text(ArcadeApp.tetrisHighScore[i]+ "");
				}//if
			 ranking.getChildren().addAll(text1,text2,text3);
			 vbox.getChildren().add(ranking);
		 }//for
		 Stage highScoreW = new Stage();
		 highScoreW.initModality(Modality.APPLICATION_MODAL);//block other window before this window is handled
		 highScoreW.setTitle("High Score Board");
		 highScoreW.setMinWidth(80);
		 highScoreW.setMinHeight(100);
		 Scene newScene = new Scene(vbox);
		 highScoreW.setScene(newScene);
		 highScoreW.sizeToScene();
		 highScoreW.showAndWait();
	 }//highScore window
	
	/**
	 * This window will pop up every time user start a new game or restart on the game
	 * ask for user's name or initial. This name will later be inserted into the the 
	 * high score if the score is higher than any score in the high score array
	 */
	public void askForName() {
		Stage nameStage = new Stage();
		//prompt to ask for initials
		Text askName = new Text("Please enter your initial: ");
		//takes user's initials
		TextField initial = new TextField("enter initial");
		
		//button to valid initials in the textField
		Button submit = new Button("submit");
		//validate text, save later put in name array
		submit.setOnAction(e->{
			name=initial.getText().trim();//take the initial from all winning users
			nameStage.close();
		});
		
		nameStage.setMinWidth(20);
		nameStage.setMinHeight(100);
		VBox vbox = new VBox();
		Scene scene = new Scene (vbox);
		nameStage.setScene(scene);
		
		nameStage.initModality(Modality.APPLICATION_MODAL);//block other window before this window is handled
		nameStage.setTitle("Name Box");
		nameStage.setWidth(250);
		
		vbox.getChildren().addAll(askName, initial,submit);
		vbox.setAlignment(Pos.CENTER);
		nameStage.sizeToScene();
		nameStage.showAndWait();
	}//askForName
	
	/**	
	 * This method keeps track of the time since the user start with the game. The time
	 * of the game also reflects on the score of the user and will show on the textbox in the
	 * right toolbar.
	 */
	public void time() {
		EventHandler<ActionEvent> handler = event -> {
			timeText.setText(Integer.toString(time));
			time ++;//keep track of the time user is playing the game	
		};//handler

		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), handler);
		timeline_4.setCycleCount(Timeline.INDEFINITE);
		timeline_4.getKeyFrames().add(keyFrame);
		timeline_4.play();
	}//time
}//TetrisBoard