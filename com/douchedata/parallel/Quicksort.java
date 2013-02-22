/**
 * Program Development in Functional and Object-oriented languages.
 * 
 * skers@kth.se
 * wikmans@kth.se
 * 
 * KTH 2013
 */
package com.douchedata.parallel;

public class Quicksort implements TestStrategy {
	
	public void execute(float [] array, int left, int right) {
		sort(array, left, right);
	}
	
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
		
		swap(array, pivotIndex, right);
		int storeIndex = left;
		
		for (int i = left; i < right; i += 1) {
			if (array[i] < pivotValue) {
				swap(array, i, storeIndex);
				storeIndex += 1;
			}
		}
		swap(array, storeIndex, right);
		return storeIndex;
	}
	
	private void swap(float[] array, final int a, final int b) {
		final float tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
}
