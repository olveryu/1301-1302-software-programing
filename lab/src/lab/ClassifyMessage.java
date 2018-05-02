/*
* ClassifyMessage.java
* Author: Lin,Wenhao
* Submission Date: 09/11/2017
*
* Purpose: 
* This is a simple and useful program for sorting out important 
* information in massages. Its then separated into categories of 
* ALERT,NEED,OFFER,INFO AND UNKNOWN for people to act accordingly.
* This program can also classify whether the location is in range or 
* not by interpreting the latitude and longitude.
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
package lab;

import java.util.Scanner;

//enum MessageCategory {NEED, OFFER, ALERT, INFO, UNKNOWN}
public class ClassifyMessage {
	enum MessageCategory {NEED, OFFER, ALERT, INFO, UNKNOWN}
	public static void main(String  args[]) { 
		String catString, payload;
		double latitude, longitude;
		boolean isInRange;
		MessageCategory category;
		//Declare Constant
		double SOUTH = 39.882343,
				NORTH = 40.231315,
				WEST = -105.743511,
				EAST = -104.907864;
		
/*
 * TESTING MESSGAE
 * Offer 40.022 -105.226 free essential supplies 4 evacs pets, 2323 55th st, boulder */
		//prompting user to input
		System.out.println("Please enter a formatted message:");
		Scanner keyboard = new Scanner(System.in);
		
		//splitting the message
		String fullMassage, part2, part3, part4;
		fullMassage = keyboard. nextLine();
		part2 = fullMassage.substring(fullMassage.indexOf(" ")+1);
		part3 = part2.substring(part2.indexOf(" ")+1);
		part4 = part3.substring(part3.indexOf(" ")+1);
		
		catString = fullMassage.substring(fullMassage.indexOf(fullMassage.charAt(0)),
				fullMassage.indexOf(" "));
		String latitudeAsString;
		latitudeAsString = part2.substring(part2.indexOf(part2.charAt(0)),
				part2.indexOf(" "));
		String longitudeAsString;
		longitudeAsString = part3.substring(part3.indexOf(part3.charAt(0)), part3.indexOf(" "));
		payload = part4.substring(part4.indexOf(part4.charAt(0)));
		//System.out.println(payload);
		
		//trimming
		catString = catString.trim();
		payload = payload.trim();
		
		//step 7,comparing strings
		if (catString.equalsIgnoreCase("fire"))
			category= MessageCategory.ALERT;
		else if (catString.equalsIgnoreCase("smoke"))
			category = MessageCategory.ALERT;
		else if (catString.equalsIgnoreCase("need"))
			category = MessageCategory.NEED;
		else if (catString.equalsIgnoreCase("offer"))
			category= MessageCategory.OFFER;
		else if (catString.equalsIgnoreCase("structure"))
			category = MessageCategory.INFO;
		else if (catString.equalsIgnoreCase("road"))
			category = MessageCategory.INFO;
		else if (catString.equalsIgnoreCase("photo"))
			category = MessageCategory.INFO;
		else if (catString.equalsIgnoreCase("evac"))
			category = MessageCategory.INFO;
		else
			category = MessageCategory.UNKNOWN;
			
		System.out.println("Category:\t" + category);
		System.out.println("Raw Cat:\t" + catString);
		System.out.println("Message:\t" + part4);
		System.out.println("Latitude:\t" + latitudeAsString);
		System.out.println("longitude:\t" + longitudeAsString);
	
		double latitudeAsDouble;
		latitudeAsDouble = Double.parseDouble(latitudeAsString);
		double longitudeAsDouble = Double.parseDouble(longitudeAsString);
		
		//comparing the range
		if(latitudeAsDouble>=SOUTH)
			if(latitudeAsDouble<=NORTH)
				if(longitudeAsDouble>=WEST)
					if(longitudeAsDouble<=EAST)
						isInRange = true;
					else
						isInRange = false;
				else
					isInRange = false;
			else
				isInRange = false;
		else
			isInRange = false;
					
			
		//smoke 40.499812 -105.012075 its raining ash
		System.out.println("In Range:\t" + isInRange);
	
		keyboard.close();
	}
		
	
	
	
}
