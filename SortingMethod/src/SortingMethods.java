

import java.util.*;
public class SortingMethods {
	public static void main(String[] args) {
		int[] array = {2,3,5,1,29,24,66,12,34,2,3,5,1,29,24,66,12,34};
		int size = array.length;
		System.out.println("using selection sort method: ");
		int start = 0;
		long startTime = System.nanoTime();
		while(start<size) {
			int min= array[start];
			int index = start;
			for(int i = start; i<size;i++) {
				if(array[i] < min) {
					min =array[i];	
					index = i;
				}
			}
			int temp = array[start];
			array[start]=array[index];
			array[index]=temp;
			start++;
		}
		long endTime = System.nanoTime();
		long total = endTime-startTime;
		System.out.println("time:" + total);
		
		for(int i =0;i<size;i++) {
			System.out.print(array[i]+ " ");
		}
		System.out.println();
		
		System.out.println("using bubble sort method: ");
		int[] array1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		int start1 = 0;
		
		startTime = System.nanoTime();
		while(start1<size) {
			boolean swap = false;
			for(int i = 0;i<size-1;i++) {
				if(array1[i]>array1[i+1]) {
					int temp = array1[i];
					array1[i] = array1[i+1];
					array1[i+1] = temp;
					swap = true;
				}
			}
			start1++;
			if(!swap) break;
		}
		endTime = System.nanoTime();
		total = endTime-startTime;
		System.out.println("time:" + total);
		
		for(int i =0;i<size;i++) {
			System.out.print(array1[i]+ " ");
		}



	}


}

