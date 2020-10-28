package com.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Test3 {

	public static void main(String[] args) throws InterruptedException {
		String urlstr = "http://192.168.0.103/network/car.jsp";
		URL url = null;
		// HttpURLConnection ���!
		HttpURLConnection con = null;
		
		// 5�ʿ� �� ���� ���� ��ǥ�� �����ϴ� �۾�
		while(true) {
			//�� �ȿ� ��ü�� �־�� �Ѵ�!
			try {
				Random rd = new Random();
				double lat = rd.nextDouble()*100;
				double lng = rd.nextDouble()*100;
				url = new URL(urlstr+"?lat="+lat+"&lng="+lng);
				con = (HttpURLConnection) url.openConnection();
				con.setReadTimeout(5000); // 5�ʵ��� ������ ������ Ÿ�Ӿƿ�
				con.setRequestMethod("POST"); // � ������� ������
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
