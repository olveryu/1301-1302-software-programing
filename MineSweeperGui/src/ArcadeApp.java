

import javafx.animation.RotateTransition;
import javafx.animation.StrokeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ArcadeApp extends Application {
	
	public Scene scene;
	public static int [] mineHighScore= new int [10];
	public static String [] winnerNamesMine =new String[10];
	public static int [] tetrisHighScore = new int[10];
	public static String [] winnerNamesTetris =new String[10];
	
//main interface of the arcade
	BorderPane mainBoard= new BorderPane();
	
//top menu bar
	MenuBar menuBar = new MenuBar();
//menu items
	Menu file = new Menu("File");
	Menu options = new Menu("Options");
	Menu help = new Menu("Help");
	
//center place two games here
	FlowPane gameBoard = new FlowPane();
	Rectangle mineSweeperRec = new Rectangle(200,200);
	Rectangle tetrisRec = new Rectangle(200,200);

//create a new minesweeper game, this declaration is final so the game can place in 
//event handler for restart method
	GameBoard mineSweeper = new GameBoard(20,20);
    Tetris newGame = new Tetris();

    
    public void start(Stage stage) {
    //creating an animation intro scene
    	
    	//layout of animation
    	Pane openPane = new Pane();
    	
    	//setup openPane background image
    	Image backImg = new Image("vs2.jpg",550,300,false,true);
		BackgroundImage bkgd = new BackgroundImage(backImg,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		openPane.setBackground(new Background(bkgd));
		//set openpane size
    	openPane.setMinSize(550,300);
    	openPane.setPadding(new Insets(30,0,0,0));
    	
    	//Text for animation
    	Text opening= new Text("WELCOME TO ARCADE");
    	opening.setStyle("-fx-font: 45 impact;");
    	opening.setFill(Color.WHITESMOKE);
    	opening.setLayoutX(50);
    	opening.setLayoutY(100);
    	
    	Text names = new Text("Wenhao Lin \n Ruijie Xu");
    	names.setStyle("-fx-font: 20 impact;");
    	names.setFill(Color.WHITESMOKE);
    	names.setLayoutX(300);
    	names.setLayoutY(200);
    	
    	Text enter = new Text("Press any key to enter the game");
    	enter.setStyle("-fx-font: 20 impact;");
    	enter.setFill(Color.WHITESMOKE);
    	enter.setLayoutX(160);
    	enter.setLayoutY(270);
    	
    	openPane.getChildren().addAll(enter,opening, names);//add all text
    	//set welcome text animation
    	RotateTransition rotate = new RotateTransition();
    	rotate.setToAngle(360);
    	rotate.setDelay(Duration.seconds(1));//this sets allows rotation to happen 1 second after
    	rotate.setCycleCount(1);
    	rotate.setDuration(Duration.seconds(3));
    	rotate.setNode(opening);
    	rotate.play();
    	//set names text animation
    	RotateTransition rotate2 = new RotateTransition();
    	rotate2.setToAngle(360);
    	rotate2.setDelay(Duration.seconds(4));//this sets allows rotation to happen 1 second after
    	rotate2.setCycleCount(1);
    	rotate2.setDuration(Duration.seconds(3));//how long the rotation will last
    	rotate2.setNode(names);
    	rotate2.play();
    	//set the stroke of the text so it will changes color over time
    	StrokeTransition stroke = new StrokeTransition(Duration.millis(3000), enter, Color.GOLD, Color.RED);
        stroke.setCycleCount(4);
        stroke.setDuration(Duration.INDEFINITE);//this will constantly changes color
        stroke.setAutoReverse(true);
        stroke.play();
    
    	
    	//put in a scene
    	Scene intro = new Scene(openPane);
    	
    //when any key on the keyboard is pressed, the scene changes to the main board game scene
	intro.setOnKeyPressed(e->{
		scene=new Scene(mainBoard);//game scene
		stage.setScene(scene);
		stage.sizeToScene();
	});
	    stage.setScene(intro);//
    	stage.show();
    	
    	
    	
    	//when click on minesweeper rect, start a new minsweeper game
    	mineSweeperRec.setOnMouseClicked(e->{
    	mineSweeper = new GameBoard(20,20);
    	
    	Scene a = new Scene(mineSweeper);
		stage.setScene(a);
		stage.sizeToScene();
    	
    	});
    	
    	//when click on tetris rect, start a new tetris game
    	
        
    	tetrisRec.setOnMouseClicked(e-> {
    		newGame = new Tetris();
    		
    	Scene ab = new Scene(newGame);
    		ab.setOnKeyPressed(handler->{
            	//left arrow pressed
            	if(handler.getCode()==KeyCode.LEFT) {
            		Boolean hasSpace=true;
            		//check for space
            		for(int i = 17; i >= 0 ; i--) {
            			for(int j = 0; j < 10; j++) {
            				if(newGame.internalBoard[i][j] == 1) {
            					if(j-1 < 0 || newGame.internalBoard[i][j-1]==-1 ) {
            						hasSpace=false;
        						}//if
        					}//if
        				}//for
        			};//for
        			//if there is space move left
        			if(hasSpace) {
        				for(int i = 17; i >= 0 ; i--) {
        					for(int j = 0; j < 10; j++) {
        						if(newGame.internalBoard[i][j]==1) {
        							newGame.internalBoard[i][j]=0;
        							newGame.internalBoard[i][j-1]=1;
        							newGame.internalColorBoard[i][j]=0;
        							newGame.internalColorBoard[i][j-1]=newGame.shape;
        						}
        					}//for
        				}//for
        				newGame.update();
        			}//if
        			hasSpace=true;
            	}//if
            	
            	//right arrow pressed
            	if(handler.getCode()==KeyCode.RIGHT) {
            		Boolean hasSpace=true;
            		//check for space
            		for(int i = 17; i >= 0 ; i--) {
            			for(int j = 0; j < 10; j++) {
            				if(newGame.internalBoard[i][j] == 1) {
            					if(j+1 >9  || newGame.internalBoard[i][j+1]==-1 ) {
            						hasSpace=false;
        						}//if
        					}//if
        				}//for
        			};//for
        			//if there is space move right
        			if(hasSpace) {
        				for(int i = 17; i >= 0 ; i--) {
        					for(int j = 9; j >= 0; j--) {
        						if(newGame.internalBoard[i][j]==1) {
        							newGame.internalBoard[i][j]=0;
        							newGame.internalBoard[i][j+1]=1;
        							newGame.internalColorBoard[i][j]=0;
        							newGame.internalColorBoard[i][j+1]=newGame.shape;
        						}
        					}//for
        				}//for
        				newGame.update();
        			}//if
        			hasSpace=true;
            	}//if        		
    	    });//setOnKeyPressed
            
    		stage.setScene(ab);
    		stage.sizeToScene();
    	});
    	
    	
    	//create a new game everytime press on the restart button
    	GameBoard.restart.setOnAction(e->{
    		mineSweeper = new GameBoard(20,20);
    		
    		Scene a = new Scene(mineSweeper);
    		stage.setScene(a);
    		stage.sizeToScene();
    		
    	});
    	
    	//minesweeper button, allows the game go back to main game board
    	GameBoard.goBack.setOnAction(e->{
    		
			stage.setScene(scene);//put the main game board scene on to the stage
			stage.sizeToScene();
		});
    	
    	Tetris.restart.setOnAction(e->{
    		newGame = new Tetris();
    		
    		Scene a = new Scene(newGame);
    		stage.setScene(a);
    		stage.sizeToScene();
    		
    	});
    	
    	//Tetris button, allows the game go back to main game board
    	Tetris.otherGames.setOnAction(e->{
    		
    		stage.setScene(scene);//put the main game board scene on to the stage
			stage.sizeToScene();
    	});
    	
    	
    	//set minesweeper image into minesweeper box
    	Image mineImg = new Image("mine.jpg",200,200,false,true);
    	mineSweeperRec.setFill(new ImagePattern(mineImg));
    	
    	//set tetris image into tetris box
    	Image tetrisImg = new Image("tetris.png",200,200,false,true);
    	tetrisRec.setFill(new ImagePattern(tetrisImg));
    	
    	//setup background image in flowpane
    	Image backImage = new Image("vs.jpg",550,300,false,true);
        		BackgroundImage background = new BackgroundImage(backImage,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        		          BackgroundSize.DEFAULT);
        gameBoard.setBackground(new Background(background));
    	//set flowpane layout
        gameBoard.setPadding(new Insets(50,50,50,50));
    	gameBoard.setMinHeight(300);
    	gameBoard.setMinWidth(550);
    	gameBoard.setHgap(50);
    	
    	//add two clickable rectangle into flow pane
    	gameBoard.getChildren().addAll(mineSweeperRec, tetrisRec);//flowpane - add minesweeper and tetris
    	
    	//add menus into menubar
    	menuBar.getMenus().addAll(file,options,help);//menubar adds all menu items	
    	mainBoard.setTop(menuBar);//set menu to top of the border pane
    	mainBoard.setCenter(gameBoard);//set flow pane with game to center of the border pane
       


	// the group must request input focus to receive key events
	// @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
	
    } // start

    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main

} // ArcadeApp
