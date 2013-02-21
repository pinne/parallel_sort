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
	private static final int LISTSIZE = 10_000_000;
	
	public static void main(String[] args) {
		RandomList randList = new RandomList(LISTSIZE);
		float[] list;
		float[] preSorted = randList.getList();
		Arrays.sort(preSorted);
		
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println(cores + " cores available");
		System.out.print("Time\t\t");
		System.out.print("Sorting\t\t\t");
		System.out.print("In order\n");
		
		// Arrays.sort
		list = randList.getList();
		System.gc();
		long startTime = System.nanoTime();
		Arrays.sort(list);
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		System.out.print(duration + "\tBuilt-in sort\t");
		System.out.println("\t" + Arrays.equals(preSorted, list));
		
		// Quicksort
		list = randList.getList();
		System.gc();
		startTime = System.nanoTime();
		Quicksort quicksort = new Quicksort();
		quicksort.sort(list, 0, list.length-1);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.print(duration + "\tQuicksort\t");
		System.out.println("\t" + Arrays.equals(preSorted, list));
		
		// QuicksortTask
		list = randList.getList();
		System.gc();
		startTime = System.nanoTime();
		ForkJoinPool pool = new ForkJoinPool();
		QuicksortTask task = new QuicksortTask(list, 0, list.length-1);
		pool.invoke(task);
		endTime = System.nanoTime();
		duration = endTime - startTime;
		System.out.print(duration + "\tParallel quicksort");
		System.out.println("\t" + Arrays.equals(preSorted, list));
	}
}
