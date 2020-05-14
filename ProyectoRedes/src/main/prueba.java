package main;

import javax.sound.midi.Soundbank;

public class prueba {
	public static void main(String[] args) {
		int[] arr = { 4, 5, 2, 3, 4, 1, 6, 1 };
		movePlayer();
	}

	static void findPair(int arr[], int sum) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] + arr[j] == sum) {
					System.out.println(arr[i] + "," + arr[j]);
					return;
				}
			}
		}
		System.out.println("Pair not found");
	}

	static void movePlayer() {

		char[][] Matrix = { { 000 }, { 000 }, { 000 } };
		int[] num = { 1, 2, 3, 4, 5 };
		for (int i = 0, s = 0; i < 5; i += s, s = -s + 1) {
			System.out.println(num[i]);
		}
	}

}
