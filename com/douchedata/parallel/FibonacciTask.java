/**
 * Program Development in Functional and Object-oriented languages.
 * 
 * skers@kth.se
 * wikmans@kth.se
 * 
 * KTH 2013
 */
package com.douchedata.parallel;

import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = -8294482541788169399L;
	public static final int THRESHHOLD = 5;
	private long result;
	private int n;
	
	public FibonacciTask(int n) {
		this.n = n;
	}
	
	public Long compute() {
		if (n < THRESHHOLD) {
			result = computeDirectly();
		} else {
			FibonacciTask worker1 = new FibonacciTask(n-1);
			FibonacciTask worker2 = new FibonacciTask(n-2);
			worker1.fork();
			result = worker2.compute() + worker1.join();
		}
		return result;
	}

	private long computeDirectly() {
		return fibonacci(n);
	}
	
	private long fibonacci(int n) {
		if (n <= 2)
			return 1;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}
}
