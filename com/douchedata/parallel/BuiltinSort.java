package com.douchedata.parallel;

import java.util.Arrays;

public class BuiltinSort implements TestStrategy {

	public void execute(float[] array, int left, int right) {
		Arrays.sort(array);
	}
}
