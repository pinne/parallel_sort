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

public class MergeSortAction extends RecursiveAction implements TestStrategy {
	private static final long serialVersionUID = 1L;
	private static int THRESHOLD = 60_000;
	private final float[] array;
	private final float[] tmp;
	private int left;
	private int right;
	
	public MergeSortAction(float[] array, float[] tmp, int left, int right) {
		this.array = array;
		this.tmp = tmp;
		this.left = left;
		this.right = right;
	}
	
	public void execute(float[] array, int left, int right) {
		ForkJoinPool pool = new ForkJoinPool();
		float[] tmp = new float[array.length];
		MergeSortAction task = new MergeSortAction(array, tmp, 0, array.length-1);
		pool.invoke(task);
	}

	protected void compute() {
		if (left >= right) { /* base case */
			return;
		} else if (right - left < THRESHOLD) {
			MergeSort.mSort(array, tmp, left, right);
		} else {
			int middle = left + (right-left) /2;

			MergeSortAction worker1 = new MergeSortAction(array, tmp, left, middle);
			MergeSortAction worker2 = new MergeSortAction(array, tmp, middle + 1, right);
			invokeAll(worker1, worker2);
			
			MergeSort.merge(array, tmp, left, right);
		}
	}

}
