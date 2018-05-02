
/*
* Life.java
* Author: Lin, Wenhao	
* Submission Date: 10/24/2017
*
* Purpose: This program is a simple automation of Conway's Game of life
*
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* or the course textbook. I recognize that any unauthorized
* assistance or plagiarism will be handled in accordance with
* the University of Georgia's Academic Honesty Policy and the
* policies of this course. I recognize that my work is based
* on an assignment created by the Department of Computer
* Science at the University of Georgia. Any publishing
* or posting of source code for this project is strictly
* prohibited unless you have written consent from the Department
* of 
*/
import java.util.Random;
public class Life {
	public static void main(String[] args) {
		int gridSize = 200;
		int cellSize = 3;
		Grid grid = new Grid(gridSize,cellSize,"The Game of Life");
		grid.setDelay(10);
		
		//set live or dead color. live= white; dead = black;
		int aliveColor = 1;
		int deadColor = 0;
		//declaring coordinate (x,y);
		int x = 0;
		int y = 0;
		//drawing a white line
		for(y = 25; y <=75;y++) {
			x = 10;
			grid.setPos(x, y, aliveColor);
			grid.update();
		}
		//clear the line
		grid.clearGrid();
		grid.update();
		
		//prepopulate the grids with ramdom values
		Random r = new Random();
		for( x = 0; x <= gridSize-1; x++) {
			for(y = 0; y <= gridSize-1; y++) {
				if(r.nextInt(100)>49) grid.setPos(x, y, aliveColor);
				else grid.setPos(x, y, deadColor);
				}
			//grid.update();
		}
		grid.update();//output randomized grid
		grid.initialize();
		
		//infinite loop for the game
		while(true) {
			for(x = 0; x <= gridSize -1; x++) {
				for(y=0; y <= gridSize -1; y++) {
					int checkCell = grid.getPos(x, y);
					int checkNeighbors = grid.matchingNeighbors(x, y, aliveColor);//return # of live neighbors
					//now update cells
					if(checkCell == 1) {
						if(checkNeighbors == 2 || checkNeighbors == 3) grid.setPos(x, y, aliveColor);
						else grid.setPos(x, y, deadColor);	
					}
					else {
						if(checkNeighbors == 3) grid.setPos(x, y, aliveColor);
						else grid.setPos(x, y, deadColor);
					}
				}
			}
			grid.update();
			
		}
	
		
	}

}
