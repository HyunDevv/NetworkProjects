package com.thread;

// java�� thread ���! Runnable���� implements
class Th implements Runnable{

	String name;
	
	public Th() {}
	public Th(String name) {
		this.name = name;
	}
	
	// run �Լ��� ����Ѵ�
	@Override
	public void run() {
		for(int i=0;i<=100;i++) {
			System.out.println(name+":"+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


// ����
public class Test2 {

	public static void main(String[] args) {
		// ���ÿ� ���۵ȴ�!!!!!
		//Runnable�϶� ������ ����� ��� ����!! Thread�� ����� �� �ȿ� �ִ´�!
		Thread t1 = new Thread(new Th("T1"));
		t1.start();        // T1�� run�Լ��� ����ȴ�!
		Thread t2 = new Thread(new Th("T2"));
		t2.start();     
	}

}
