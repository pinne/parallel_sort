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

public class TestContext {
//	private static final int LISTSIZE = 10_000_000;
//	private static final int LISTSIZE = 1_000_000;
	private static final int LISTSIZE = 100_000;
	
	private TestStrategy strategy;
	private long totalTime;
	private RandomList randList ;
	 
    public TestContext(TestStrategy strategy) {
		this.randList = new RandomList(LISTSIZE);
        this.strategy = strategy;
    }
 
    /**
     * Runs the test n times and returns the average running time.
     */
    public long executeStrategy(int samples) {
    	float[] unsorted;
    	float[] preSorted = randList.getList();
		Arrays.sort(preSorted);
		
    	for (int i = 0; i < samples; i += 1) {
    		// Get the unsorted list
    		unsorted = randList.getList();
    		
    		System.gc();
    		
    		// Clock the sort
    		long startTime = System.nanoTime();
    		strategy.execute(unsorted, 0, unsorted.length-1);
    		long endTime = System.nanoTime();
    		long duration = endTime - startTime;
    		
    		if (i > 0)
    			this.totalTime += duration;
    		// Test and print result
    		if (Arrays.equals(preSorted, unsorted)) {
    			System.out.print(i + " ");
    		} else {
    			System.out.print("x ");
    		}
    	}
    	return (totalTime / samples-1);
    }
}
