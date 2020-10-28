package com.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

// ������ �޾ƺ���
public class Test2 {

	public static void main(String[] args) {
		String urlstr = "http://192.168.0.103/network/mp.mp3";
		URL url = null;
		URLConnection con = null;
		// ������� �������� ������ �� ���̴�
		
		InputStream is = null;
		BufferedInputStream bis = null;
		
		// �� ��ǻ�ͷ� ������ �غ�
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			url = new URL(urlstr);
			con = url.openConnection();
			is = con.getInputStream();
			bis = new BufferedInputStream(is,100000000);
			
			// �ּҸ� �ٲٰ� �ʹٸ� �����θ� �Է����ָ� �ȴ�
			fos = new FileOutputStream("newmp.mp3");
			bos = new BufferedOutputStream(fos);
			
			//����Ʈ ������ ���� ������ int�� �޴´�
			int data = 0;
			//������ ������..
			while((data = bis.read()) != -1) {
				//System.out.println(data);
				bos.write(data);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		
		}
	}

}
