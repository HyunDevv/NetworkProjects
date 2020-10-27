package com.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

// ������ id/pwd�� �Է¹޾� ������
public class Test4 {

	public static void main(String[] args) throws InterruptedException {
		String urlstr = "http://192.168.0.103/network/login.jsp";
		URL url = null;
		// HttpURLConnection ���!
		HttpURLConnection con = null;
		
		// �����͸� �ְ� �������� I/O�� ����� �Ѵ�
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
			//�� �ȿ� ��ü�� �־�� �Ѵ�!
			try {
				Scanner sc = new Scanner(System.in);
				String id = sc.nextLine();
				String pwd = sc.nextLine();
				url = new URL(urlstr+"?id="+id+"&pwd="+pwd);
				con = (HttpURLConnection) url.openConnection();
				con.setReadTimeout(5000); // 5�ʵ��� ������ ������ Ÿ�Ӿƿ�
				con.setRequestMethod("POST"); // � ������� ������
				
				is = con.getInputStream();  // out.print�� �̰����� ���ƿ´�
				isr = new InputStreamReader(is,"UTF-8"); // �ѱ۱�������
				br = new BufferedReader(isr);
				
				String str = "";
				str = str.trim();
				
				while((str = br.readLine()) != null) {
					if(str.equals("")) {
						continue;
					}
					if(str.equals("1")) {
						System.out.println("Login OK!");
					}else {
						System.out.println("Login fail!");
					}
					//System.out.println(str);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.disconnect();
			}


		
	}

}
