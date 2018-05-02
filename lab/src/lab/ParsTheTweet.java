/*
* ParsTheTweet.java

* Author: Lin,Wenhao
* Submission Date: 09/05/2017
*
* Purpose: This program can be use for sorting out information on tweets into 
* desired categories.        ]
* Statement of Academic Honesty:
*
* The following code represents my own work. I have neither
* received nor given inappropriate assistance. I have not copied
* or modified code from any source other than the course webpage
* CSCI 1301: Project 1 Page 3
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


package lab;
import java.util.Scanner;
public class ParsTheTweet {
	public static void main (String[] args) {
	//Declare Variable;
	String type,typeTrimmed, detail, detailTrimmed, location, locationTrimmed, tweet,
	part1, part2, part3, part4, part5, longtitude,longtitudeTrimmed, latitude,
	latitudeTrimmed;
	
	
	//Declare Scanner;
	Scanner keyboard = new Scanner (System.in);;
	tweet = keyboard. nextLine();
	
	/*extract Data from tweet "#typ offer; #det free essential supplies 4 evacs pets.; 
	 #loc2323 55th st, boulder; #lat 40.022; #lng -105.226;*/
	
	//splitting the tweet
	
	part1 = tweet;
	part2 = part1.substring(part1.indexOf(";")+2);
	part3 = part2.substring(part2.indexOf(";")+2);
	part4 = part3.substring(part3.indexOf(";")+2);
	part5 = part4.substring(part4.indexOf(";")+2);
	
	//type = part1. substring(tweet.indexOf(tweet. charAt(4)),tweet.indexOf(";"));
	type = part1.substring(4,tweet.indexOf(";"));
	type = type.toUpperCase();
	typeTrimmed = type.trim();
	System.out.println("Type:\t\t"+ typeTrimmed );
	
	//detail = part2.substring(part2. indexOf(part2.charAt(4)), part2.indexOf(";"));
	detail = part2.substring(5, part2.indexOf(";"));
	detail = detail.replace(',', '-');
	detailTrimmed = detail.trim();
	System.out.println("Detail:\t\t"+detailTrimmed);
	
	location = part3.substring(part3. indexOf(part3. charAt(4)), part3. indexOf(";"));
	location = location.replace(',','-');
	locationTrimmed= location.trim();
	System.out.println("Location:\t"+ locationTrimmed);
	
	latitude = part4.substring(part4. indexOf(part4. charAt(4)), part4. indexOf(";"));
	latitudeTrimmed = latitude.trim();
	System.out.println("Latitudet\t"+latitudeTrimmed);
	
	longtitude = part5.substring(part5. indexOf(part5. charAt(4)), part5. indexOf(";"));
	longtitudeTrimmed = longtitude.trim();
	System.out.print("Longtitude:\t"+ longtitudeTrimmed);
	
	
	
	}

}
