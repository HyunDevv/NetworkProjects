package com.tcpip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	int port;
	String address;
	
	Socket socket;
	
	public Client() {};
	public Client(String address,int port) {
		this.address = address;
		this.port = port;
	}
	
	public void connet() throws InterruptedException {
		try {
			socket = new Socket(address,port);
			System.out.println("Connected ...");
		} catch (Exception e) {
			e.printStackTrace();
			
			while(true) {
				Thread.sleep(2000);
				try {
					socket = new Socket(address,port);
					System.out.println("Connected ...");
					break;
				} catch (IOException e1) {
					System.out.println("Re-try ...");
				}
				
			}
			
			
		}
	}
	
	// 메시지를 보내는 동안에도 다른 작업이 가능하도록 쓰레드로 만든다
	class Sender extends Thread{
		DataOutputStream dos;
		String msg;
		
		public Sender(String msg) {
			this.msg = msg;
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			if(dos != null) {
				try {
					// 메시지를 보낸다
					dos.writeUTF(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
//				finally {
//					if(dos != null) {
//						try {
//							dos.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//					
//				}
			}
		}
		
		
	}
	
	
	public void request() throws IOException {
		Scanner sc = new Scanner(System.in);
		try {
			// 문자열을 계속 보낼 수 있게 무한루프
			while(true) {
				System.out.println("[Input Mag:]");
				String msg = sc.nextLine();
				// q가 들어오면 break
				if(msg.equals("q")) {
					new Sender("q").start();
					Thread.sleep(1000);
					System.out.println("Exit Client ..");
					break;
				}
				//입력한 메시지를 보낸다
				new Sender(msg).start();
			}
		}catch(Exception e){
			
		}finally {
	         sc.close();
	         if(socket != null) {
	            socket.close();
	         }
		 }
	}
	
	
	public static void main(String[] args) {
		Client client = new Client("192.168.0.28",7777);
		try {
			client.connet(); // 소켓연결
			client.request();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
