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

public class MergeSortAction extends RecursiveAction implements TestStrategy {
	public void execute(float[] array, int left, int right) {
		float[] sorted = mSort(array);
		copyArray(array, sorted);
	}

	private float[] mSort(float[] m) {
		float[] left = new float[m.length / 2];
		float[] right = new float[m.length - left.length];

		if (m.length <= 1) {
			return m;
		} else {
			int index = 0;
			int l = 0;
			while (l < m.length/2) {
				left[l++] = m[index++];
			}

			int r = 0;
			while (index < m.length) {
				right[r++] = m[index++];
			}

			left = mSort(left);
			right = mSort(right);

			return merge(left, right);
		}
	}

	private float[] merge(float[] left, float[] right) {
		float[] target = new float[left.length + right.length];

		int t = 0;
		int l = 0;
		int r = 0;

		/* merge lists */
		while (l < left.length && r < right.length) {
			if (left[l] < right[r])
				target[t++] = left[l++];
			else
				target[t++] = right[r++];
		}
		/* append lists */
		while (l < left.length)
			target[t++] = left[l++];
		while (r < right.length)
			target[t++] = right[r++];

		return target;
	}

	private void copyArray(float[] array, float[] tmp) {
		for (int i = 0; i < array.length; i += 1) {
			array[i] = tmp[i];
		}
	}
}
