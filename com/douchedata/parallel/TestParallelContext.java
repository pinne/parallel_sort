/**
 * Program Development in Functional and Object-oriented languages.
 * 
 * skers@kth.se
 * wikmans@kth.se
 * 
 * KTH 2013
 */
package com.douchedata.parallel;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class TestParallelContext {
	private static final long serialVersionUID = 1L;

	private static final int LISTSIZE = 10_000_000;

	private TestStrategy strategy;
	private long totalTime;
	private float[] preSorted;
	private float[] unsorted;
	private RandomList randList ;

	public TestParallelContext(TestStrategy strategy, float[] preSorted, RandomList randList) {
		this.preSorted = preSorted;
		this.randList = randList;
		this.strategy = strategy;
	}

	/**
	 * WARNING! This doesn't work quite right.
	 */
	public long executeStrategy(int samples) {
		ForkJoinPool pool = new ForkJoinPool();
		// Get the unsorted list
		this.unsorted = randList.getList();

		System.gc();

		// Clock the sort
		long startTime = System.nanoTime();
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		this.totalTime += duration;

		// Test and print result
		if (Arrays.equals(preSorted, unsorted)) {
			System.out.print("success ");
		} else {
			System.out.print("fail ");
		}
		return (totalTime / samples);
	}
}
