/**
 * Program Development in Functional and Object-oriented languages.
 * 
 * skers@kth.se
 * wikmans@kth.se
 * 
 * KTH 2013
 */
package com.douchedata.parallel;

import java.util.concurrent.RecursiveAction;

public class QuicksortTask extends RecursiveAction {
	
	private static final long serialVersionUID = 1L;
	private float[] array;
	private int left;
	private int right;
	private int pivotIndex;
	
	public QuicksortTask(float[] array, int left, int right) {
		this.array = array;
		this.left = left;
		this.right = right;
		this.pivotIndex = left + (right-left)/2;;
	}
	
	public void compute() {
		if (left < right) {
			int pivotNewIndex = partition(array, left, right, pivotIndex);
			
			QuicksortTask worker1 = new QuicksortTask(array, left, pivotNewIndex - 1);
			QuicksortTask worker2 = new QuicksortTask(array, pivotNewIndex + 1, right);
			// fork 1, compute 2, join 1
			invokeAll(worker1, worker2);
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
