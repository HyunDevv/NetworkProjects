package com.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Test3 {

	public static void main(String[] args) throws InterruptedException {
		String urlstr = "http://192.168.0.103/network/car.jsp";
		URL url = null;
		// HttpURLConnection 사용!
		HttpURLConnection con = null;
		
		// 5초에 한 번씩 랜덤 좌표를 전달하는 작업
		while(true) {
			//이 안에 전체가 있어야 한다!
			try {
				Random rd = new Random();
				double lat = rd.nextDouble()*100;
				double lng = rd.nextDouble()*100;
				url = new URL(urlstr+"?lat="+lat+"&lng="+lng);
				con = (HttpURLConnection) url.openConnection();
				con.setReadTimeout(5000); // 5초동안 응답이 없으면 타임아웃
				con.setRequestMethod("POST"); // 어떤 방식으로 보낼지
				con.getInputStream();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.disconnect();
			}
			Thread.sleep(5000);

		}


		
	}

}
