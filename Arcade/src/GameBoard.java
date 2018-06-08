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
	import javafx.scene.image.Image;
	import javafx.scene.image.ImageView;
	import javafx.scene.input.MouseButton;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.GridPane;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.StackPane;
	import javafx.scene.layout.VBox;
	import javafx.scene.paint.Color;
	import javafx.scene.shape.Rectangle;
	import javafx.scene.text.Font;
	import javafx.scene.text.Text;
	import javafx.stage.Modality;
	import javafx.stage.Stage;
	import javafx.util.Duration;

	/**Represent the Main GameBoard that allows user to choose between a Minesweeper game or a Tetris game.
	 *
	 *@author Wenhao Lin
	 *@author RuiJie Xu
	 *@since 2018-04-20 
	 */
	public class GameBoard extends BorderPane{
		private int row=0;//define the number of rows for the game
		private int col=0;//define the number of col for the game
		private int width = col * 30;//width for the board
		private int height = row * 30 + 70;;//height for the board
		private int totalMine=0;
		private int [][] minePos; //keep track of mine location indexes 
		public static Button restart= new Button("restart");
		public static Button goBack = new Button("Other Games");
		public Box [] [] boxList; //to store rectangles(mines)
		public Timeline timeline = new Timeline();
		public int time=0;
		public Text timeText = new Text(90,60,Integer.toString(time));
		public int score;
		public int covered;//number of covered mines
		public int unMarkedMines; //number of unMarked mines
		public int numOfMarkedMines;//num of Marked mines
		public String winnerName = new String();

		
		HBox toolBox = new HBox(30);// HBox for two clickable rectangle to start each game	
		GridPane mineField = new GridPane();//main section(mineField) of the gameBoard
		HBox textBox1 = new HBox();//display number of Mines
		HBox textBox2 = new HBox();//display time used in secones
		
		 /**
	     * Construct an object instance of the GameBoard class.
	     *
	     *@param row the number of rows in the minefield grid
	     *@param col the number of columns in the minefield grid
	     */
		public GameBoard(int row, int col) {
		minePos = new int [row][col];
		boxList = new Box [row][col];
		timer();
		//set inital dimension and mine numbers of the game	
		this.row = row;//number of rows
		this.col = col;//number of cols
		covered = row *col;
		totalMine =(int) Math.ceil(1.0 * row * col /8.0);//set total number of mines
		unMarkedMines = totalMine;
		numOfMarkedMines=0;
		
		populateMines();
		//text in the mine left text box
		//remainMine= totalMine;//initialize remain mines == total mine
		Text minesLeft = new Text( 90,60,Integer.toString(unMarkedMines));//text field keep track of how many mines left minesLeft.
		minesLeft.setFill(Color.RED);
		minesLeft.setStyle(" -fx-font-size:22;");
		
		//toolBox styling
		toolBox.setPrefHeight(70);
		toolBox.setPadding(new Insets(10,10,10,10));
		toolBox.setStyle("-fx-alignment:center;");
		
		//mine left text box styling
		textBox1.setMaxHeight(50);
		textBox1.setPrefWidth(90);
		textBox1.setStyle("-fx-alignment:center; -fx-background-color: black");
		textBox1.getChildren().add(minesLeft);
		
		Button highScoreBtn = new Button("High Score");
		highScoreBtn.setOnAction(e->{
			highScoreWindow();
		});//highScoreBtn
		
		//timer text box styling
		textBox2.setMaxHeight(50);
		textBox2.setPrefWidth(90);
		textBox2.setStyle("-fx-alignment:center; -fx-background-color: black");
		timeText.setFill(Color.RED);
		timeText.setStyle(" -fx-font-size:22;");
		textBox2.getChildren().add(timeText);
		
		//tool Box add mine left text box, button for restart and timer text box
		toolBox.getChildren().addAll(textBox1,highScoreBtn,goBack,restart,textBox2);
		//put rectangle in each grid box  (set up mine field)
		for (int i = 0; i<row; i++) {
			for (int j =0; j< col; j++) {
				Box box = new Box(i,j);
				Text numOfMine = new Text("");
				//new for flag image
			    Image flagImage = new Image("3079-24x24x4.png");
			    ImageView flagImageView = new ImageView(flagImage);
				//set each mines responsive to mouse clicks(left and right)
				box.setOnMouseClicked(e->{
				MouseButton button = e.getButton();
				//right click to mark
				if(button ==MouseButton.SECONDARY&& box.marked==false&&box.revealed==false) {
					timeline.play();//start timing
					box.getChildren().add(flagImageView);//flag image added
					box.marked = true;//set box property to "marked"
					numOfMarkedMines++;
					if (minePos[box.row][box.col]==1) {
						unMarkedMines=unMarkedMines-1;
					}
					//wins if all mines are marked and marked mines equals total mine
					if(unMarkedMines==0&&numOfMarkedMines==totalMine) {
						win();
					}//if
				}//if
				//unmarked a mine
				else if(button == MouseButton.SECONDARY && box.marked == true&&box.revealed ==false) {
					box.getChildren().remove(flagImageView);
					box.marked = false;
					numOfMarkedMines--;
					if(minePos[box.row][box.col]==1) {
						unMarkedMines=unMarkedMines+1;
					}//if
				}//else if
				if (button == MouseButton.PRIMARY &&box.revealed == false && box.marked==false) {
					timeline.play();
					if(getNumAdjMines(box.row,box.col)==-1) {
						lose(box.row,box.col);
					}
					else {
						int number=getNumAdjMines(box.row,box.col);
						numOfMine.setText(Integer.toString(number));
							//reveal mine
							open(number,numOfMine,box);
							if(covered == totalMine) {
								win();
								setOnMouseClicked(null);
							}//if
						}//else
					}//if
				});//setOnAction
				mineField.add(box,j,i);//add the single grid box into mineField
				boxList[i][j]=box;//add box to the list
			}//for
		}//for			
		setTop(toolBox);//setting the top part of the game board
		setCenter(mineField);//setting the main part of the game board	
		}//constructor
		
		 /**
		  * Represent the each sqaure box with info about their index location on the gameboard and properties
		  * of that whether they are marked,revealed or contains mine..
		  *
		  *@author Wenhao Lin
		  *@author RuiJie Xu
		  *@since 2018-04-20
		  */
		public class Box extends StackPane{
			int row=0;
			int col =0;
			Rectangle box;
			Boolean marked = false;
			Boolean revealed = false;
			Boolean mined = false;
			
			/**
			 *Construct Object instance of the square box with defined index locations and box styles
			 *
			 *@param row row index of a particular box
			 *@param col column index of a particular box
			 */
			public Box(int row, int col) {
			setPrefWidth(28);
			setPrefHeight(28);
				box = new Rectangle(28,28);
				this.row=row;
				this.col=col;
				box.setStroke(Color.BLACK);//set style of the rectangle
				box.setFill(Color.SILVER);
				getChildren().addAll(box);
			}//constructor
		}//Box
	    
		/**
	     *set an timer on the game
	     */
		public void timer() {
			EventHandler<ActionEvent> handler = event -> {
				timeText.setText(Integer.toString(time));
				time ++;
			};//handler
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), handler);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.getKeyFrames().add(keyFrame);
			//timeline.play();
		}//timer
		
	    /**
	     *populate random mines and and place them on the squares
	     */
		public void populateMines() {
			Random rand = new Random();
			for (int i =0; i< totalMine;i++) {
				int x = rand.nextInt(row)+0;
				int y = rand.nextInt(col)+0;
				if (minePos[x][y]==1) {
						i--;
				}else {
					minePos[x][y]=1;
				}//if
			}//for
		}//populateMines
	    
		/**
	     *This method calculate the number of mines contained in the adjusant squares of the revealed square and update the
	     *<code>gridToShow</code> with the number to be shown in that square and set
	     *<code>mines</code> to that number.If that particular square contains the mine itself,<code> mines</code> will
	     *be set to -1.
	     *
	     *@param row an integer that represent the row of desired square.
	     *@param col an integer that represent the col of desired square.
	     *@return <code>mines</code>, an integer represent the number of mines.
	     */
		public int getNumAdjMines(int row,int col) {
			int mines=0;
			if(minePos[row][col]==1) {
				return -1;
			}//if
			for(int i = row; i<row+3;i++) {
				if(i-1<0)continue;
				else if(i-1 >=this.row)break;
				for(int j= col; j<col+3;j++) {
					if(j-1<0)continue;
					else if (j-1>=this.col)break;
					else if(minePos[i-1][j-1]==1)
						mines++;
				}//col
			}//row
			return mines;
		}//getAdjMines

		 /**
	     *This method reveals a box that does not contain a mine. It shows a number
	     *represents the number of adjacent mines or simply change the box color to
	     *represent that box does have any adjacent mines.In that case, this method 
	     *will enter the recursive calls and opens its adjacent boxes until it reaches
	     *the base case where a box does contain adjacent mines
	     *
	     *@param number the number of adjacent mines of the box to be opened
	     *@param numOfMine a Text object of the number of adjacent mines
	     *@param aBox the box to be opened.
	     */
		public void open(int number, Text numOfMine, Box aBox) {
			//opens the boxes according to the number of adjacent mine they have 1-8
			
			//base case
			switch(number) {
			case 1:
			    numOfMine.setFill(Color.BLUE);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			    break;
			case 2:
			    numOfMine.setFill(Color.GREEN);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			    break;
			case 3:
			    numOfMine.setFill(Color.RED);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			    break;
			case 4:
			    numOfMine.setFill(Color.NAVY);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			    break;
			case 5:
			    numOfMine.setFill(Color.MAROON);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			    break;
			case 6:
			    numOfMine.setFill(Color.MIDNIGHTBLUE);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			    break;
			case 7:
			    numOfMine.setFill(Color.DIMGREY);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			    break;
			case 8:
			    numOfMine.setFill(Color.BLACK);
			    numOfMine.setFont(Font.font ("Verdana", 20));
			    aBox.getChildren().add(numOfMine);
			    aBox.box.setFill(Color.GREY);
			    aBox.revealed=true;
			    covered--;
			}//switch
			
			//recursive case, if the box does not have any adjacent mines
			//keep on opening adjacent boxes that are not being reveal and does not contain mines
			//until it reaches the base case
			if(number ==0) {
				 aBox.box.setFill(Color.GREY);
				 numOfMine.setFont(Font.font ("Verdana", 20));
				aBox.revealed =true;
				covered--;
				for(int i = aBox.row; i<aBox.row+3;i++) {
					if(i-1<0)continue;
					else if(i-1 >=row)break;
					for(int j= aBox.col; j<aBox.col+3;j++) {
						if(j-1<0)continue;
						else if (j-1>=col)break;
						else if(getNumAdjMines(i-1,j-1)!=-1&& boxList[i-1][j-1].revealed == false && boxList[i-1][j-1].marked == false) {
							open(getNumAdjMines(i-1,j-1),new Text(Integer.toString(getNumAdjMines(i-1,j-1))),boxList[i-1][j-1]);
						}//else
					}//for			
				}//for
			}//if
		}//open
		
		 /**
	     *This method create a win mode for the game that stops the timer and create an pop up window
	     *that displays the score and some text.
	     */		
		public void win() {
			Stage winStage = new Stage();
			timeline.pause();
			score = (int) ((1.0 /time)*10000);
			Label winLabel = new Label("Congratulation! you won!");
			Label scoreLabel = new Label("Your score is: " + Integer.toString(score));
			
			//disabling buttons
			for (int i =0; i< boxList.length; i++) {
				for (int j=0; j<boxList[0].length;j++) {
					boxList[i][j].setDisable(true);
				}//for
			}//for
			
			//pop up window for score info display
			winStage.setMinWidth(20);
			winStage.setMinHeight(100);
			VBox vbox = new VBox();
			Scene scene = new Scene (vbox);
			winStage.setScene(scene);
			
			winStage.initModality(Modality.APPLICATION_MODAL);//block other window before this window is handled
			winStage.setTitle("YOU WON!");
			winStage.setWidth(250);
			
			vbox.getChildren().addAll(winLabel,scoreLabel);
			vbox.setAlignment(Pos.CENTER);
			winStage.sizeToScene();
			winStage.showAndWait();
			highScore();//check if the score can use in high score array
		}//win
		
		/**
		 *This method create a lose mode for the game that stops the timer and create an pop up window
		 *that displays the some text to inform player that they have lost.
		 */
		public void lose(int row, int col) {
			timeline.pause();
			Image mineImage1 = new Image("3078-24x24x4.png");
		    ImageView mineImageView1 = new ImageView(mineImage1);
			boxList[row][col].box.setFill(Color.RED);
			boxList[row][col].getChildren().add(mineImageView1);
			Label loseLabel = new Label("Sorry, You revealed a mine!");
			//disable the buttons and reveal all mines
			for (int i =0; i< boxList.length; i++) {
			    for (int j=0; j<boxList[0].length;j++) {
				boxList[i][j].setDisable(true);
				if(i == row && j==col){
					continue;
				}
				if(minePos[i][j]==1) {
				    //Text loseText = new Text("X");
					//boxList[i][j].getChildren().add(mineImageView);
					Image mineImage = new Image("3078-24x24x4.png");
				    ImageView mineImageView = new ImageView(mineImage);
					boxList[i][j].box.setFill(Color.GREY);
					boxList[i][j].getChildren().add(mineImageView);
				}//if
			    }//for	
			}//for
				
			Stage loseStage = new Stage();
			loseStage.setMinWidth(100);
			loseStage.setMinHeight(100);
			VBox vbox = new VBox();
			Scene scene = new Scene (vbox);
			loseStage.setScene(scene);
			
			loseStage.initModality(Modality.APPLICATION_MODAL);//block other window before this window is handled
			loseStage.setTitle("YOU LOST");
			loseStage.setWidth(250);
			loseStage.setHeight(300);
			vbox.getChildren().addAll(loseLabel);
			vbox.setAlignment(Pos.CENTER);
			loseStage.sizeToScene();
			loseStage.showAndWait();
		    }//lose
		
		/**
		 * this method keeps insert the new score into the high score list if there is any 
		 * score in the high score list is less than the newest score. if not, then no score will be
		 * inserted.
		 */
		public void highScore() {
		for (int i =0; i< 10; i++) {
			if (score > ArcadeApp.mineHighScore[i]) {//score higher than the number in the array
				int temp = ArcadeApp.mineHighScore[i];//save the current value in the array
				String tempName = ArcadeApp.winnerNamesMine[i];//save the current string in the array
				ArcadeApp.mineHighScore[i]=score;//current position of the array equals to the score
				ArcadeApp.winnerNamesMine[i]=winnerName;
				for (int j = 10-1; j> i; j--) {//this array starts from the back, pushes every array after the insertion back one spot
					ArcadeApp.mineHighScore[j]=ArcadeApp.mineHighScore[j-1];
					ArcadeApp.winnerNamesMine[j]=ArcadeApp.winnerNamesMine[j-1];
					if (j==i+1) {//at the pos one after the beginnig array, pos = temp saved in the beginning of the array, the old value of the current pos
						ArcadeApp.mineHighScore[j]=temp;
						ArcadeApp.winnerNamesMine[j]=tempName;
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
		 
		 VBox vbox = new VBox();//VBox for base template
		 
		 Text rank =new Text("High Score Board");//title
		 rank.setStyle("-fx-font: 20 impact;");
		 vbox.getChildren().add(rank);
		 //iterate thru the high score array and print out the saved name and score
		 for(int i =0; i< ArcadeApp.mineHighScore.length; i++) {
				HBox ranking = new HBox();//put all text in a row
			 	Text text1 = new Text(i+1 +".\t");//set numbers
				Text text2 = new Text("");
				Text text3 = new Text("");
				if(ArcadeApp.winnerNamesMine[i]!=null) {//if not empty, then set text
				 text2 = new Text(ArcadeApp.winnerNamesMine[i]+"\t");
				 text3 = new Text(ArcadeApp.mineHighScore[i]+ "");
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
	 }//highScoreWindow
	 
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
				winnerName=initial.getText().trim();//take the initial from all winning users
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
		}//ask for name
}//gameBoard



