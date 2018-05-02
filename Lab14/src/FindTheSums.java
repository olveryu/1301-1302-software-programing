/*
* FindTheSums.java
* Author: Wenhao,Lin
* Submission Date: 11/28/2017
*
* Purpose:This program has three methods. One is to change 2D array into String format.
* Two is the horizontalSum method which will return a 2D array with  numbers that add
* up to a desired sum horizontally. Three is the verticalSum metod which will return a 2D array with 
* numbers that add up to a desired sum vertically.
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
* of Computer Science at the University of Georgia.
*/
public class FindTheSums {
	public static String arrayToString(int[][] a) {
		//determine the dimension of the table
		int row = a.length;
		int column = a[0].length;
		String array = "";
		
		for(int i = 0; i < row; i ++) {//loop for rows
			int last = 0;
			if(i != row -1) {//for all rows before last
				for(int j = 0; j < column -1; j++) {//loop for column
				array += a[i][j]+" ";
				last++;
			}
			array += a[i][last] + "\n";//for last value in the row
			}
			else {//for last row
				for(int k = 0; k < column-1; k++ ) {
					array += a[row-1][k]+" ";
				}
				array += a[i][column-1];//for last value in the row
			}
		}
		return array;
	}
	
	public static int[][] horizontalSums(int[][] a, int sumToFind){
		int[][] b = new int[a.length][a[0].length];
		int row = b.length;
		int column = b[0].length;
		
		//initialize array b to 0's
		for(int z = 0; z < row; z++) {
			for(int y = 0; y < column; y++) {
				b[z][y] = 0;
			}
		}
		
		for(int i = 0; i < row; i++) {//loop for rows
			
		int start = 0;//start position in the row;
		
			while(start < column) {
					int sum = 0;
					for(int j = start; j < column && sum<sumToFind; j++) {
							sum += a[i][j];
								if(sum == sumToFind) {
									for(int k = start; k < j+1; k++) {
										b[i][k]=a[i][k];
									}
									break;
								}
					}
		start++;//move start position one space over
		}
	}
		return b;
	}
	public static int[][] verticalSums(int[][] a, int sumToFind){
		int[][] b = new int[a.length][a[0].length];//initialize dimention
		int row = b.length;
		int column = b[0].length;
		
		//initialize array b to with 0's
		for(int z = 0; z < row; z++) {
			for(int y = 0; y < column; y++) {
				b[z][y] = 0;
			}
		}
		
		for(int i = 0; i < column; i++) {// loop for first column
			int start = 0;
			while(start < row) {
				int sum = 0;
				for(int j = start; j < row && sum < sumToFind; j++) {
					sum += a[j][i];
					if(sum == sumToFind) {
						for(int k = start; k < j+1; k++) {
							b[k][i] = a[k][i];
						}
						break;
					}
				}
				start++;//move start position one space over
			}
			
		}
	return b;	
	}
	
	
}
