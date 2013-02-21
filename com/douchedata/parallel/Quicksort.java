/**
 * Program Development in Functional and Object-oriented languages.
 * 
 * skers@kth.se
 * wikmans@kth.se
 * 
 * KTH 2013
 */
package com.douchedata.parallel;

public class Quicksort {
	
	public void sort(float[] array, int left, int right) {
		if (left < right) {
			int pivotIndex = left + (right-left)/2;
			int pivotNewIndex = partition(array, left, right, pivotIndex);
			
			sort(array, left, pivotNewIndex - 1);
			sort(array, pivotNewIndex + 1, right);
		}
	}
	
	private int partition(float[] array, int left, int right, int pivotIndex) {
		float pivotValue = array[pivotIndex];
		
		// swap array[pivotIndex] and array[right]
		float tmp = array[pivotIndex];
		array[pivotIndex] = array[right];
		array[right] = tmp;
		
		int storeIndex = left;
		
		for (int i = left; i < right; i += 1) {
			if (array[i] < pivotValue) {
				// swap array[i] and array[storeIndex]
				tmp = array[i];
				array[i] = array[storeIndex];
				array[storeIndex] = tmp;

				storeIndex += 1;
			}
		}
		// swap array[storeIndex] and array[right]
		tmp = array[storeIndex];
		array[storeIndex] = array[right];
		array[right] = tmp;
		
		return storeIndex;
	}
}
