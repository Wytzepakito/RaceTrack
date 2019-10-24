package gui;

import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TesterClass {
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					
					int[] arr1 = new int[] {1,2,3,4,5,6,8,9,10};
					int[] arr2 = Arrays.copyOfRange(arr1, 7, 10);
					int[] superarr1 = new int[8];
					for (int i  : arr2) {
						System.out.println(i);
					}


				}
			});
		}
	}

