package com.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

// 서버로 id/pwd를 입력받아 서버세
public class Test4 {

	public static void main(String[] args) throws InterruptedException {
		String urlstr = "http://192.168.0.103/network/login.jsp";
		URL url = null;
		// HttpURLConnection 사용!
		HttpURLConnection con = null;
		
		// 데이터를 주고 받으려면 I/O를 열어야 한다
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
			//이 안에 전체가 있어야 한다!
			try {
				Scanner sc = new Scanner(System.in);
				String id = sc.nextLine();
				String pwd = sc.nextLine();
				url = new URL(urlstr+"?id="+id+"&pwd="+pwd);
				con = (HttpURLConnection) url.openConnection();
				con.setReadTimeout(5000); // 5초동안 응답이 없으면 타임아웃
				con.setRequestMethod("POST"); // 어떤 방식으로 보낼지
				
				is = con.getInputStream();  // out.print가 이곳으로 날아온다
				isr = new InputStreamReader(is,"UTF-8"); // 한글깨짐방지
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
