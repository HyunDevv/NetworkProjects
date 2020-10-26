package com.thread;

class Th1 extends Thread{
	int sum;
	
	@Override
	public void run() {
		for(int i=1;i<=40;i++) {
			System.out.println("TH1..........");
			sum += i;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int getSum() {
		return sum;
	}
	
}



class Th2 extends Thread{
	int sum;
	
	@Override
	public void run() {
		for(int i=1;i<=40;i++) {
			System.out.println("TH2..........");
			sum += i;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int getSum() {
		return sum;
	}
}

public class Test5 {

	
	public static void main(String[] args) throws InterruptedException {
		Th1 th1 = new Th1();
		System.out.println("START");
		Th2 th2 = new Th2();
		th1.start();
		System.out.println("TH1 START");
		th2.start();
		System.out.println("TH2 START");
		
		th1.join(); //th1이 끝날때 까지 아래 구문 실행을 멈춘다
		th2.join(); //th2이 끝날때 까지 아래 구문 실행을 멈춘다
		
		System.out.println(th1.getSum()+" "+th2.getSum());
		System.out.println(th1.getSum()+th2.getSum());
	}

}
