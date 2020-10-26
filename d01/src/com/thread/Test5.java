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
		
		th1.join(); //th1�� ������ ���� �Ʒ� ���� ������ �����
		th2.join(); //th2�� ������ ���� �Ʒ� ���� ������ �����
		
		System.out.println(th1.getSum()+" "+th2.getSum());
		System.out.println(th1.getSum()+th2.getSum());
	}

}
