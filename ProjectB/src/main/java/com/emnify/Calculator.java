package com.emnify;

//import org.apache.log4j.Logger;

public class Calculator {

	public static void main(String[] args) {
		Calculator calc = new Calculator();

		// result will be calculated and stored in cache
		System.out.println("1 + 2 = " + calc.sum(1, 2));

		// result will be retrieved from cache
		System.out.println("1 + 2 = " + calc.sum(1, 2));

		// ------------------------------------------------------

		// System.out.println("Faculty:");
		// // result will be calculated and stored in cache
		// System.out.println("5! = " + calc.faculty(5));
		// // result up to 5! will be retrieved from cache
		// System.out.println("6! = " + calc.faculty(6));
		//
	}

	@Cacheable
	public int sum(int a, int b) {
		System.out.println("Calculating " + a + " + " + b);
		try {
			// pretend this is an expensive operation
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.err.println("Something went wrong: " + e.getMessage());
		}
		return a + b;
	}

	@Cacheable
	public long faculty(int n) {
		if (n == 1)
			return 1L;
		else
			return faculty(n - 1) * (long) n;
	}
}
