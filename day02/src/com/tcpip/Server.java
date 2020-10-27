package com.tcpip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port = 9999; // 사용하고 있지 않는 포트 아무숫자
	ServerSocket serverSocket;
	Socket socket;
	
	// 소켓연결 이후 스트림으로 데이터를 보내야한다
	InputStreamReader ir;
	BufferedReader br;
	
	public Server() {
		
	}
	
	// 서버시작 함수
	public void startServer() throws IOException {
		serverSocket = new ServerSocket(port);
		// 스트림 에러가 발생할 수 있으니 트라이캐치로 잡아준다!
		try {
			// 서버가 연결되어도 죽지 않도록 계속 실행 시킨다
			while(true) {
				System.out.println("Ready Server ....");
				// 여기서 받기를 기다린다
				socket = serverSocket.accept();
				System.out.println("Connected ....");
				
				ir = new InputStreamReader(socket.getInputStream());
				br = new BufferedReader(ir);
				
				String msg = "";
				msg = br.readLine();
				System.out.println(msg);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			if(br != null) {
				br.close();
			}
			if(socket != null) {
				socket.close();
			}
		}


	}
	
	
	
	public static void main(String[] args) {
		Server server = null;
		server = new Server();
		try {
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End Server");
	}

}
