package com.douchedata.parallel;

public class MergeSort implements TestStrategy {
	float[] array;
	float[] tmp;
	int left;
	int right;
	
	public MergeSort(float[] array, float[] tmp, int left, int right) {
		this.array = array;
		this.tmp = tmp;
		this.left = left;
		this.right = right;
	}
	
	public void execute(float[] array, int left, int right) {
		float[] tmp = new float[array.length];
		mSort(array, tmp, left, right);
	}
	
	public static void mSort(float[] array, float[] tmp, int left, int right) {
		if (left >= right) /* base case */
			return;

		int middle = left + (right-left) /2;

		mSort(array, tmp, left, middle);
		mSort(array, tmp, middle + 1, right);

		merge(array, tmp, left, right);
	}
	
	public static void merge(float[] array, float[] tmp, int left, int right) {
		/* copy active part of array to tmp */
		for (int i = left; i <= right; i++)
			tmp[i] = array[i];

		/* merge lists */
		int k = left;
		int mid = left + (right-left) / 2;
		int j = mid+1;
		while (k <= right) {
			if (left > mid)
				array[k++] = tmp[j++];
			else if (j > right)
				array[k++] = tmp[left++];
			else if (tmp[left] < tmp[j])
				array[k++] = tmp[left++];
			else
				array[k++] = tmp[j++];
		}
	}
}
