package com.bsgco;

import static javax.swing.SwingUtilities.invokeLater;

public final class Runner {
	public static void main(String[] args) {
		invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
}
