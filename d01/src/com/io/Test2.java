package com.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//���ڱ�� ��Ʈ���� Reader�� Writer�� ����غ���!
public class Test2 {
	
	public static void main(String[] args) {
		String file = "c:\\network\\d01\\src\\text.txt";
		FileReader fis = null;
		BufferedReader bis = null;
		FileWriter fos = null;
		BufferedWriter bos = null;
		try {
			fis = new FileReader(file);
			bis = new BufferedReader(fis); // fis�� �־ ���Ȯ��, �ӵ�����
			fos = new FileWriter("text2.txt");
			bos = new BufferedWriter(fos); // fos�� �־ ���Ȯ��, �ӵ�����
			String data = "";
			// �� �ڸ��� �ƴҶ�����, readLine()�� BufferedReader���� �ִ�
			while((data=bis.readLine()) != null) {
				bos.write(data); // �о�帰�� �����Ѵ�
				System.out.println(data);  // ����Ʈ������ �о���
			}
			//System.out.println(fis.available());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fis�� ���� close��!
			if(bis != null) {
				try {
					bis.close();   // ** �� close�� ���־�� �Ѵ�!!! **
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// bos�� ���� close��!
			if(bos != null) {
				try {
					bos.close();   // ** �� close�� ���־�� �Ѵ�!!! **
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
	
	
	
	}
}


