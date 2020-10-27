package com.tcpip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port = 9999; // ����ϰ� ���� �ʴ� ��Ʈ �ƹ�����
	ServerSocket serverSocket;
	Socket socket;
	
	// ���Ͽ��� ���� ��Ʈ������ �����͸� �������Ѵ�
	InputStreamReader ir;
	BufferedReader br;
	
	public Server() {
		
	}
	
	// �������� �Լ�
	public void startServer() throws IOException {
		serverSocket = new ServerSocket(port);
		// ��Ʈ�� ������ �߻��� �� ������ Ʈ����ĳġ�� ����ش�!
		try {
			// ������ ����Ǿ ���� �ʵ��� ��� ���� ��Ų��
			while(true) {
				System.out.println("Ready Server ....");
				// ���⼭ �ޱ⸦ ��ٸ���
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
