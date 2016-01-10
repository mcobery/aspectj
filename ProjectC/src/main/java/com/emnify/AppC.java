package com.emnify;

public class AppC {
	public static void main(String[] args) {
		System.out.println("C start");
		AppC app = new AppC();
		app.hello("C world");
		System.out.println("C end");
	}

	public void hello(String name) {
	}
}
