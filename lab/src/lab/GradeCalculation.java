
package lab;
import java.util.Scanner;
//import java.util.Scanner;
/*
* GradeCalculation.java
* Author: [Lin,Wenhao]
* Submission Date: [09/14/2017]
*
* Purpose: This program is a helpful tool for students to calculate
* their final grades. It also can be used to determine what the 
* average item score needs to be to achieve any desire final grade.
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

public class GradeCalculation {
	public static void main(String[] args) {

		//declare variables
		String gradeToAchieve;
		int exam1, exam2, finalExam, labs, projects,
		attendance, quizzes;
		int exam1Grade= -1,
				exam2Grade= -1,
				finalExamGrade=-1,
				labsGrade=-1,
				projectsGrade=-1,
				attendanceGrade=-1,
				quizzesGrade=-1;
		String exam1YesOrNo,exam2YesOrNo,finalExamYesOrNo,
		labYesOrNo, projectYesOrNo, quizYesOrNo,attendanceYesOrNo;
		double currentGradeScore;
		double currentGradeScoreRound;
		double finalOverallScore;
		double aveToFinalLetterGrade=0;
		double aveToFinalLetterGradeRound=0;


		//printout grading scale
		System.out.println("Grading Scale: \nA\t90-100"
				+"\nB\t80-89"
				+"\nC\t70-79"
				+"\nD\t60-69"
				+"\nF\tbelow 60");

		//prompting use to input desire letter grade
		Scanner Object = new Scanner(System.in);
		System.out.print("What letter grade do you want to "
				+ "achive for the course?");
		gradeToAchieve = Object.next();

		//program terminate anything other than (A,B,C,D,F) is enters for
		//gradeToAchieve
		if(gradeToAchieve.equalsIgnoreCase("a") 
				|| gradeToAchieve.equalsIgnoreCase("b")
				||gradeToAchieve.equalsIgnoreCase("c") 
				|| gradeToAchieve.equalsIgnoreCase("d")
				|| gradeToAchieve.equalsIgnoreCase("f"))
			System.out.println("Enter Percentage Weights:");
		else 
		{	System.out.println("Input Error");
		System.exit(0);
		}
		//prompting user to enter percentage weights:
		//System.out.println("Enter Percentage Weights:");
		System.out.print("Exam 1:\t\t");
		exam1 = Object.nextInt();
		System.out.print("Exam 2:\t\t");
		exam2 = Object.nextInt();
		System.out.print("Final Exam:\t");
		finalExam = Object.nextInt();
		System.out.print("Labs:\t\t");
		labs = Object.nextInt();
		System.out.print("Projects:\t");
		projects = Object.nextInt();
		System.out.print("Attendance:\t");
		attendance = Object.nextInt();
		System.out.print("Quizzes:\t");
		quizzes = Object.nextInt();

		//check if weights add up to 100
		if(exam1+exam2+finalExam+labs+projects+attendance+quizzes==100)
			System.out.println("Enter your scores out of 100:");
		else
		{	System.out.println("Weight don't add up to 100, program exiting...");
		System.exit(0);
		}
		//prompting user to input score
		System.out.print("Do you know your Exam 1 score?");
		exam1YesOrNo = Object.next();
		if(exam1YesOrNo.equalsIgnoreCase("yes") 
				|| exam1YesOrNo.equalsIgnoreCase("y"))
		{{{	System.out.print("Score received on exam 1:");
		exam1Grade = Object.nextInt();
		System.out.print("Do you know your Exam 2 score?");
		exam2YesOrNo = Object.next();}

		if(exam2YesOrNo.equalsIgnoreCase("yes") 
				|| exam2YesOrNo.equalsIgnoreCase("y"))
		{{	System.out.print("Score received on exam2:");
		exam2Grade = Object.nextInt();

		System.out.print("Do you know your Final Exam score?");
		finalExamYesOrNo = Object.next();
		}
		if(finalExamYesOrNo.equalsIgnoreCase("yes") 
				|| finalExamYesOrNo.equalsIgnoreCase("y"))
		{	System.out.print("Score received on final exam:");
		finalExamGrade = Object.nextInt();

		System.out.print("Do you know your lab average?");
		labYesOrNo = Object.next();
		}
		else
		{	System.out.print("Do you know your lab Average?");
		labYesOrNo = Object.next();}
		}	

		else
		{	System.out.print("Do you know your lab Average?");
		labYesOrNo = Object.next(); }
		}}
		else
		{	System.out.print("Do you know your lab average?");
		labYesOrNo = Object.next(); }

		//LAB  and project INPUT
		if(labYesOrNo.equalsIgnoreCase("yes")||labYesOrNo.equalsIgnoreCase("y"))
		{	System.out.print("Average Lab Grade:");
		labsGrade = Object.nextInt();

		System.out.print("Do you know your project average?");
		projectYesOrNo = Object.next();
		}
		else 
		{	System.out.print("Do you know your project average?");
		projectYesOrNo = Object.next();
		}	
		//project and quiz  input
		if(projectYesOrNo.equalsIgnoreCase("yes") 
				|| projectYesOrNo.equalsIgnoreCase("y"))
		{	System.out.print("Average Project Grade:");
		projectsGrade = Object.nextInt();

		System.out.print("Do you know your quiz Average?");
		quizYesOrNo = Object.next();
		}
		else
		{	System.out.print("Do you know your quiz Average?");
		quizYesOrNo = Object.next();
		}	
		//quiz and attendance input
		if(quizYesOrNo.equalsIgnoreCase("yes") 
				|| quizYesOrNo.equalsIgnoreCase("y"))
		{	System.out.print("Average Quiz Grade:");
		quizzesGrade = Object.nextInt();

		System.out.print("Do you know your attendance average?");
		attendanceYesOrNo = Object.next();
		}
		else
		{	System.out.print("Do you know your attendance average?");
		attendanceYesOrNo = Object.next();
		}	

		//current score formula

		//attendance input
		if(attendanceYesOrNo.equalsIgnoreCase("yes")
				|| attendanceYesOrNo.equalsIgnoreCase("y"))
		{	System.out.print("Avrage Attendance Grade:");
		attendanceGrade = Object. nextInt();

		//remember to ask how to identify known input for formula
		if(exam1Grade == -1)
			exam1=0;
		if(exam2Grade == -1)
			exam2 = 0;
		if(finalExamGrade == -1)
			finalExam = 0;
		if(labsGrade == -1)
			labs = 0;
		if(projectsGrade == -1)
			projects = 0;
		if(attendanceGrade == -1)
			attendance = 0;
		if(quizzesGrade == -1)
			quizzes = 0;
		currentGradeScore =(double) (exam1*exam1Grade + exam2*exam2Grade
				+finalExam*finalExamGrade +labs*labsGrade
				+projects*projectsGrade +quizzes*quizzesGrade
				+attendance*attendanceGrade)/ (double) (exam1+exam2
						+finalExam+labs+projects+attendance
						+quizzes);
		currentGradeScoreRound = Math.ceil(currentGradeScore*100)/100d;


		System.out.println("Current Grade Score:"+currentGradeScoreRound );
		}
		else
		{	
			if(exam1Grade==-1)
				exam1=0;
			if(exam2Grade == -1)
				exam2 = 0;
			if(finalExamGrade == -1)
				finalExam = 0;
			if(labsGrade == -1)
				labs = 0;
			if(projectsGrade == -1)
				projects = 0;
			if(attendanceGrade == -1)
				attendance = 0;
			if(quizzesGrade == -1)
				quizzes = 0;
			currentGradeScore = (double)(exam1*exam1Grade + exam2*exam2Grade
					+finalExam*finalExamGrade +labs*labsGrade
					+projects*projectsGrade +quizzes*quizzesGrade
					+attendance*attendanceGrade)/(double)(exam1+exam2
							+finalExam+labs+projects+attendance
							+quizzes);
			currentGradeScoreRound = Math.ceil(currentGradeScore*100)/100d;
			System.out.printf("Current Grade Score:%.2f",currentGradeScoreRound);	
			System.out.println("");
		}	


		//printout your current letter grade
		if(currentGradeScoreRound<60)
			System.out.println("Your current letter grade is a F");
		if(currentGradeScoreRound>=60 && currentGradeScoreRound<70)
			System.out.println("Your current letter grade is a D");
		if(currentGradeScoreRound>=70 && currentGradeScoreRound<80)
			System.out.println("Your current letter grade is a C");
		if(currentGradeScoreRound>=80 && currentGradeScoreRound<90)
			System.out.println("Your current letter grade is a B");
		if(currentGradeScoreRound>=90 && currentGradeScoreRound<=100)
			System.out.println("Your current letter grade is a A");

		//final overall score
		if(gradeToAchieve.equalsIgnoreCase("a"))
			finalOverallScore=90;
		else if(gradeToAchieve.equalsIgnoreCase("b"))
			finalOverallScore=80;
		else if(gradeToAchieve.equalsIgnoreCase("c"))
			finalOverallScore=70;
		else if(gradeToAchieve.equalsIgnoreCase("d"))
			finalOverallScore=60;
		else
			finalOverallScore=0;

		//average to final letter grade
		double weightAndScore;
		weightAndScore = (double)(exam1*exam1Grade + exam2*exam2Grade
				+finalExam*finalExamGrade +labs*labsGrade
				+projects*projectsGrade +quizzes*quizzesGrade
				+attendance*attendanceGrade);

		double totalKnownGradeWeight;
		totalKnownGradeWeight = (double)(exam1+exam2
				+finalExam+labs+projects+attendance
				+quizzes);


		if(totalKnownGradeWeight != 100)
		{	
			aveToFinalLetterGrade =(100*finalOverallScore - weightAndScore)
					/(100-totalKnownGradeWeight);
			aveToFinalLetterGradeRound=Math.ceil(aveToFinalLetterGrade*100)
					/100d;
			if(aveToFinalLetterGradeRound <= 0)
				System.out.print("Congratulations! You received the "
						+ gradeToAchieve +" that you wanted!");
			else if (aveToFinalLetterGradeRound<=100)
				System.out.print("You have to score an average greater "
						+ "than or equal to "
						+aveToFinalLetterGradeRound +" in the remaining "
								+ "grade items to receive an "
						+gradeToAchieve +" in the course");
			else
				System.out.print("Sorry, you cannot receive an "+ 
			gradeToAchieve+ " in the course");
		}
		else
		{
			if(currentGradeScoreRound>=90 && finalOverallScore==90)
				System.out.print("Congratulations! You received the A"
						+ " that you wanted!");
			else if(currentGradeScoreRound>=80 && currentGradeScoreRound <90
					&& finalOverallScore==80)
				System.out.print("Congratulations! You received the B"
						+ " that you wanted!");
			else if(currentGradeScoreRound>=70 && currentGradeScoreRound <80 
					&& finalOverallScore==70)
				System.out.print("Congratulations! You received the C"
						+ " that you wanted!");
			else if(currentGradeScoreRound>=60 && currentGradeScoreRound <70
					&& finalOverallScore==60)
				System.out.print("Congratulations! You received the D"
						+ " that you wanted!");
			else if(currentGradeScoreRound<60 && finalOverallScore<60)
				System.out.print("Congratulations! You received the F"
						+ " that you wanted!");
		}

		



	}	







}




