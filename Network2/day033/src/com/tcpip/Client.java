package com.tcpip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.msg.Msg;

//Object Serialization ...

public class Client {

	int port;
	String address;
	
	Socket socket;
	Sender sender;
	
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
		
		sender = new Sender();  // 소켓이 만들어진 이후에 sender를 만든다
		
	}
	
	// 메시지를 보내는 동안에도 다른 작업이 가능하도록 쓰레드로 만든다
	class Sender implements Runnable{
		ObjectOutputStream dos;
		Msg mo;
		
		public void setMo(Msg mo) {
			this.mo = mo;
		}
		public Sender() {
			try {
				dos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			if(dos != null) {
				try {
					// 메시지를 보낸다
					//dos.writeInt(1);
					dos.writeObject(mo);
				} catch (IOException e) {
					System.out.println("Not Available ...");
					System.exit(0);
					//e.printStackTrace();
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
				System.out.println("[Input Msg:]");
				String msg = sc.nextLine();
				Msg mo = new Msg("192.168.0.??","ID",msg.trim());
				//입력한 메시지를 보낸다
				sender.setMo(mo);
				new Thread(sender).start();
				Thread.sleep(500);
				// q가 들어오면 break
				if(msg.equals("q")) {
					Thread.sleep(1000);
					System.out.println("Exit Client ..");
					break;
				}

			}
		}catch(Exception e){
			
		}
		 finally {
	         sc.close();
	         if(socket != null) {
	            socket.close();
	         }
		 }

	}
	
	
	public static void main(String[] args) {
		Client client = new Client("192.168.0.103",7777);
		try {
			client.connet(); // 소켓연결
			client.request();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
