/*
* Stat.java
* Author: Lin,Wenhao
* Submission Date: 11/07/2017
*
* Purpose: This Stat class stores an array of double values called
* data.Several methods are defined in this class. The program 
* compute the min,max,mode and average of these values.
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

public class Stat {

	//instance variable
	private double[] data;

	//default constructor
	public Stat() {
		data = new double[1];
		this.data[0] = 0.0;
	}

	//Constructor that contruct a stat object using values held in d
	public Stat(double[] d) {
		data = new double[d.length];
		for(int i = 0; i < d.length; i ++) {
			data[i] = d[i];
		}
	}

	//This is an accessor (get or getter) method used to retrieve the values of data
	public double[] getData() {
		double[] temp = new double[data.length];
		for(int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		return temp;
	}

	//This is a mutator (set or setter) method used to set the values of the data array
	public void setData(double[] d) {
		data = new double[d.length];
		for(int i = 0; i <d.length; i++) {
			data[i] = d[i];
		} 
	}

	//This method Returns the boolean value true if the data objects have the same values
	public boolean equals(Stat s) {
		boolean equal = true;
		for(int i = 0; i < s.getData().length;i++) {
			if(data[i] != s.getData()[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
	//Returns a String representation of the data elements stored in the Stat object.
	public String toString() {
		String s = "[ " ;
		for(int i = 0; i < data.length-1; i++) {
			s += data[i] +", ";
		}
		s += data[data.length-1] +" ]";
		return s;	
	}
	//Returns the min of data array
	public double min() {
		double minimum = data[0];
		for(int i = 1; i < data.length; i++) {
			if(data[i] < minimum) minimum = data[i];
		}
		return minimum;
	}
	//Returns the max of data array
	public double max() {
		double maximum = data[0];
		for(int i = 1; i < data.length; i++) {
			if(data[i] > maximum) maximum = data[i];
		}
		return maximum;
	}
	//Returns the mean value of data array
	public double average() {
		double total = 0;
		double mean = 0;
		for(int i = 0; i < data.length; i ++) {
			total += data[i];
		}
		mean = total/data.length;
		return mean;
	}
	/*Returns a value that occurs most often in the array, or returns Double NaN 
	if no unique value exist*/
	public double mode() {
		//double mode = Double.NaN;
		//double[] newData = new double[data.length];
		//reorder the array from the smallest to largest
		//Arrays.sort(data);
		double maxValue = Double.NaN;
		double maxValue1 = Double.NaN;
		double maxCount = 0;
		double maxCount1 = 0;
		for (int i = 0; i < data.length; ++i) {
			double count = 0;
			for (int j = 0; j < data.length; ++j) {
				if (data[j] == data[i])
					++count;
			}
			if (count > maxCount) {
				maxCount = count;
				maxValue = data[i];
			}
		}
		
		for (int i = 0; i < data.length; ++i) {
			double count = 0;
			for (int j = 0; j < data.length; ++j) {
				if(data[i] == maxValue) {
					continue;
				}else {
					if (data[j] == data[i]) {
						++count;
					}
				}
			}
			if (count > maxCount1) {
				maxCount1 = count;
				maxValue1 = data[i];
			}
		}

		if(maxCount == maxCount1) {
			return Double.NaN;
		}		
		return maxValue;	
	}
	public static void main(String[] args) {
		double[] data1 = { 1, 2, 3, 4, 5};
		Stat stat1 = new Stat(data1);
		String result = stat1.toString();
		System.out.println("data = "+ result);
		
	}
}

