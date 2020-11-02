package com.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.msg.Msg;

public class Server {

	int port;
	HashMap<String, ObjectOutputStream> maps;
	
	ServerSocket serverSocket;
	
	
	public Server() {}
	public Server(int port) {
		this.port = port;
		maps = new HashMap<>();
	}
	
	// 서버소캣을 통해 각각 클라이언트가 접속할 떄 소켓을 만들고 리시버를 작동시킨다
	public void startServer() throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Start Server ...");
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Socket socket = null;
						System.out.println("Ready Server ...");
						socket = serverSocket.accept();
						// 접속한 클라의 IPAddress
						System.out.println(socket.getInetAddress());
						makeOut(socket);
						new Receiver(socket).start();
					}catch(Exception e) {
						e.printStackTrace();
					}

				} // end while
			}
		};
		
		new Thread(r).start();
		
	}
	
	// 각각의 클라가 접속 했을 때 아웃풋 스트림을 만들어서 해쉬맵에 데이터를 집어 넣는 역할
	public void makeOut(Socket socket) throws IOException {
		ObjectOutputStream oo;
		oo = new ObjectOutputStream(socket.getOutputStream());
		maps.put(socket.getInetAddress().toString(), oo);
		System.out.println("접속자 수:" + maps.size());
	}
	
	// 스트림을 통해 받은 object를 확인하고 sendmsg에 data를 전달한다
	class Receiver extends Thread{
		Socket socket;
		ObjectInputStream oi;
		
		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			oi = new ObjectInputStream(socket.getInputStream());
		}

		@Override
		public void run() {
			while(oi != null) {
				Msg msg = null;
				try {
					msg = (Msg) oi.readObject();
					if(msg.getMsg().equals("q")) {
						throw new Exception();
					}else if(msg.getMsg().equals("1")) {
						String ip =
						socket.getInetAddress().toString();
						ArrayList<String> ips =
								new ArrayList<>();
						ips.add(ip);
						msg.setIps(ips);
						Set<String> keys = maps.keySet();
						HashMap<String, Msg> hm = 
								new HashMap<>();
						for(String k:keys) {
							hm.put(k,null);
						}
						// 1을 보낸 client
						// 서버의 접속자 ip들
						msg.setMaps(hm);
					}
					System.out.println(msg.getId()+msg.getMsg());
					sendMsg(msg);
				} catch (Exception e) {
					maps.remove(socket.getInetAddress().toString());
					System.out.println(socket.getInetAddress()+".. Exited");
					System.out.println("접속자수:"+maps.size());
					break;
				}
			} // end while
			
			try {
				if(oi != null) {
					oi.close();
				}
				if(socket != null) {
					socket.close();
				}
			}catch(Exception e){
				
			}
			
		}
		
	}
	
	// Receiver에서 받은 msg를 Sender를 호출하여 모든 클라에게 전송한다.
	public void sendMsg(Msg msg) {
		Sender sender = new Sender();
		sender.setMsg(msg);
		sender.start();
	}
	
	// Hashmap의 사용자 정보를 통해 메시지를 전달한다
	class Sender extends Thread{
		Msg msg;
		public void setMsg(Msg msg) {
			this.msg = msg;
		}
		@Override
		public void run() {
			Collection<ObjectOutputStream> cols = 
					maps.values();
			Iterator<ObjectOutputStream> it =
					cols.iterator();
			while(it.hasNext()) {
				try {
					// null 체크한다, 여러명에게 보내기
					if(msg.getIps() != null) {
						//System.out.println("test!!!!!!!!!"+msg.getIps());
						for(String ip:msg.getIps()) {
							maps.get(ip).writeObject(msg);
						}
						break;
					}
					// broadcast
					it.next().writeObject(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Server server = new Server(5555);
		try {
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
