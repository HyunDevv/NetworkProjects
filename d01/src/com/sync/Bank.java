package com.sync;

public class Bank {

	public static void main(String[] args) {
		Account acc = new Account(3000);
		Dth dt = new Dth(acc);
		Wth wt1 = new Wth(acc);
		Wth wt2 = new Wth(acc);
		Wth wt3 = new Wth(acc);
		wt1.start();
		wt2.start();
		wt3.start();
		dt.start();
		
	}

}
