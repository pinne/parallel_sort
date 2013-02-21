/**
 * Program Development in Functional and Object-oriented languages.
 * 
 * skers@kth.se
 * wikmans@kth.se
 * 
 * KTH 2013
 */
package com.douchedata.parallel;

import java.util.Random;

public class RandomList {
	private static final float HIGH = Float.MAX_VALUE;
	//private static final float LOW = -Float.MAX_VALUE;
	private Random gen = new Random();
	private float[] list;
	
	public RandomList(int size) {
		 list = new float[size];
//		 fillList(list);
		 fillRandomList(list);
	}
	
	/**
	 * returns a copy of the list
	 */
	public float[] getList() {
		float[] copy = new float[list.length];
		for (int i = 0; i < list.length; i += 1)
			copy[i] = this.list[i];
			
		return copy;
	}
	
	private void fillRandomList(float[] list) {
		for (int i = 0; i < list.length; i += 1)
			list[i] = gen.nextFloat() * HIGH;
	}
	
	@SuppressWarnings("unused")
	private void fillList(float[] list) {
		for (int i = 0; i < list.length; i += 1)
			list[i] = gen.nextInt(10);
			//list[i] = list.length - i;
	}
}

