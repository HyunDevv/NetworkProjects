package com.thread;

import java.util.Scanner;

//class Thread1 extends Thread{
class Thread1 implements Runnable{

	boolean flag = true;
	
	public Thread1() {}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// run 함수를 사용한다
	@Override
	public void run() {
		System.out.println("Start");
		while(true) {
			if(flag == false) {
				System.out.println("Stop....");
				break;
			}
			//System.out.println("Connecting ...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("End");
	}
	
}


// 메인
public class Test3 {

	public static void main(String[] args) throws InterruptedException {
		//Runnable 객체는 한번만 만들어진다
		Thread1 r = new Thread1();
		Thread t1 = null;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Input Cmd");
			String cmd = sc.nextLine();
			if(cmd.equals("start")) {
				// 멈춘이후 다시 사용하려면 생성하고 사용해야 한다!!
				// Thread객체를 여기서 계속 생성된다
				t1 = new Thread(r);
				t1.start();
			}else if(cmd.equals("stop")) {
				r.setFlag(false);
			}else {
				break;
			}
		}
		sc.close();
		System.out.println("Exit Application...");
	}

}
