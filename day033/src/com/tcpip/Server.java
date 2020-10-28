package com.tcpip;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.msg.Msg;

// Object Serialization ...

public class Server {
	
	int port;
	ServerSocket serverSocket;
	Socket socket;
	
	public Server() {}
	public Server(int port) {
		this.port = port;
	}
	
	class Receiver extends Thread{
		ObjectInputStream dis;
		Socket socket = null;
		
		public Receiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new ObjectInputStream(socket.getInputStream());
				System.out.println(dis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}

		@Override
		public void run() {
			while(dis != null) {
				Msg mo = null;
				try {
					// Client에서 보낸 메시지를 받는다
					mo = (Msg) dis.readObject();
					if(mo.getMsg().equals("q")) {
						System.out.println(mo.getId()+"님이 나갔습니다");
						break;
					}
					System.out.println("["+mo.getId()+"]"+mo.getMsg());
					
					
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.println("아무개가 나갔습니다");
					break;
				}
			}
			
			
			if(dis != null) {
				try {
					dis.close();
					System.out.println("11");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
					System.out.println("22");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
	
	
	public void startServer() throws Exception {
		System.out.println("TCP/IP Server Start....");
		try {
			serverSocket = new ServerSocket(port);
			while(true) {
				System.out.println("Ready Server ..");
				socket = serverSocket.accept();
				System.out.println("Connected..");
				// 쓰레드를 통해서 클라이언트가 연결 될 때마다 소켓이 생성된다!!
				new Receiver(socket).start();
			}
		}catch(Exception e) {
			throw e;
		}
		
	}
	

	public static void main(String[] args) {
		Server server = new Server(7777);
		try {
			server.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
