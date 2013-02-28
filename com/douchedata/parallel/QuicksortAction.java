/**
 * Program Development in Functional and Object-oriented languages.
 * 
 * skers@kth.se
 * wikmans@kth.se
 * 
 * KTH 2013
 */
package com.douchedata.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuicksortAction extends RecursiveAction implements TestStrategy {
	private static final long serialVersionUID = 1L;
	private static int THRESHOLD = 60_000;
	private float[] array;
	private int left;
	private int right;
	private int pivotIndex;
	
	public QuicksortAction(float[] array, int left, int right) {
		super();
		this.array = array;
		this.left = left;
		this.right = right;
		this.pivotIndex = left + (right-left)/2;
	}
	
	public void execute(float[] array, int left, int right) {
		ForkJoinPool pool = new ForkJoinPool();
		QuicksortAction task = new QuicksortAction(array, 0, array.length-1);
		pool.invoke(task);
	}
	
	public void compute() {
		if (right - left < THRESHOLD) {
			// Around and below this point, there's no point in forking anymore
			Quicksort.sort(array, left, right);
		} else if (left < right) {
			int pivotNewIndex = partition(array, left, right, pivotIndex);
			
			QuicksortAction worker1 = new QuicksortAction(array, left, pivotNewIndex - 1);
			QuicksortAction worker2 = new QuicksortAction(array, pivotNewIndex + 1, right);
			// fork 1, compute 2, join 1
			invokeAll(worker1, worker2);
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
