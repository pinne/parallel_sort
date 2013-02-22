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

public class ParallelSort {
	private static final int LISTSIZE = 30_000_000;
	private static final int SAMPLESIZE = 4;
	private static long totalTime;
	
	public static void main(String[] args) {
		RandomList randList = new RandomList(LISTSIZE);
		float[] list;
		float[] preSorted = randList.getList();
		Arrays.sort(preSorted);
		
		TestContext context;
		TestParallelContext parContext;
		long duration;
		
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println(cores + " cores available");
		
		// Arrays.sort
		System.out.println("Built-in sort:");
		list = randList.getList();
		context = new TestContext(new BuiltinSort(), preSorted, randList);
		duration = context.executeStrategy(SAMPLESIZE);
		System.out.println("\tTime: " + duration);
		System.out.println("");
		
		// Quicksort
		System.out.println("Quicksort:");
		list = randList.getList();
		context = new TestContext(new Quicksort(), preSorted, randList);
		duration = context.executeStrategy(SAMPLESIZE);
		System.out.println("\tTime: " + duration);
		System.out.println("");

		// Without strategy pattern
		System.out.println("Parallel Quicksort:");
		for (int i = 0; i < SAMPLESIZE; i += 1) {
			list = randList.getList();
			System.gc();
			long startTime = System.nanoTime();
			ForkJoinPool pool = new ForkJoinPool();
			QuicksortAction task = new QuicksortAction(list, 0, list.length-1);
			pool.invoke(task);
			long endTime = System.nanoTime();
			duration = endTime - startTime;
			totalTime += duration;
    		// Test and print result
    		if (Arrays.equals(preSorted, list)) {
    			System.out.print(i + " ");
    		} else {
    			System.out.print("fail ");
    		}
		}
		duration = totalTime / SAMPLESIZE;
		System.out.println("\tTime: " + duration);
		System.out.println("");

		//		// QuicksortAction
		//		System.out.println("Parallel Quicksort:");
		//		list = randList.getList();
		//		QuicksortAction task = new QuicksortAction(list, 0, list.length-1);
		//		parContext = new TestParallelContext(task, preSorted, randList);
		//		duration = parContext.executeStrategy(SAMPLESIZE);
		//		System.out.println("\tTime: " + duration);
		//		System.out.println("");
	}

}
