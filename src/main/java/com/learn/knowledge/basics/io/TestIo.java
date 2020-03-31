package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestIo {

	/**
	 * inputStream    outputStream
	 * Reader         Writer
	 * 四个管道
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	
	@Test
	public void inputStreamTest() throws IOException, InterruptedException {
		/**
		 * FileInoutStream
		 */
		FileInputStream fis = new FileInputStream("E:\\BaiduNetdiskDownload\\test.txt");
		FileOutputStream fos = new FileOutputStream("E:\\BaiduNetdiskDownload\\test1.txt");
		long num = 0;
		int b;
		while((b=fis.read()) != -1) {
			System.out.println((char)b);
			fos.write(b);
			num++;
		}
		fis.close();
		fos.close();
		
		System.out.println(num);
		/**
		 * int read()                                     write(int b)
		 * int read(byt[] buffer)						  write(byte[] b)
		 * int read(byt[] buffer,int off, int length)     write(byte[] b, int off, int len)
		 * 				                                  void flush();
		 * 												  void close();
		 */
		
	}
	
	@Test
	public void FileReaderTest() throws IOException {
		
		FileReader fr = new FileReader("E:\\BaiduNetdiskDownload\\test.txt");
		FileWriter fw = new FileWriter("E:\\BaiduNetdiskDownload\\test2.txt");
		int b = 0;
		int num = 0;
		while((b=fr.read()) != -1) {
			System.out.println((char)b);
			fw.write(b);
			num++;
		}
		System.out.println(num);
		fr.close();
		fw.close();
			
		
	}
	
	/**
	 * 处理流
	 * @throws IOException
	 */
	@Test
	public void bufferFileReaderTest() throws IOException {
		
		FileReader fr = new FileReader("E:\\BaiduNetdiskDownload\\test.txt");
		FileWriter fw = new FileWriter("E:\\BaiduNetdiskDownload\\test2.txt");
		BufferedReader br = new BufferedReader(fr);
		int b = 0;
		int num = 0;
		while((b=fr.read()) != -1) {
			System.out.println((char)b);
			fw.write(b);
			num++;
		}
		System.out.println(num);
		fr.close();
		fw.close();
			
		
	}
	
	@Test
	public void pohoto() throws IOException {
//		long start1 = System.currentTimeMillis();
//		FileInputStream fis = new FileInputStream("F:\\导包\\Saved Pictures\\&壁纸\\风景\\5 (2).jpg");
//		BufferedInputStream bis = new BufferedInputStream(fis);
//		FileOutputStream fos = new FileOutputStream("F:\\test.jpg");
//		BufferedOutputStream bos = new BufferedOutputStream(fos);
//		byte[] b = new byte[1024];
//		int len = bis.read(b);
//		while(len != -1) {
//			bos.write(b,0,len);
//			len = bis.read(b);
//		}
//		long end1 = System.currentTimeMillis();
//		fos.close();
//		fis.close();
//		bos.close();
//		bis.close();
//		System.out.println("程序运行时间： "+(end1-start1)+"ms");   //  程序运行时间： 1ms

		long start1 = System.currentTimeMillis();
		FileInputStream fis = new FileInputStream("F:\\导包\\Saved Pictures\\&壁纸\\风景\\5 (2).jpg");
		FileOutputStream fos = new FileOutputStream("F:\\test.jpg");
		int b = fis.read();
		while(b != -1) {
			fos.write(b);
			b = fis.read();
		}
		long end1 = System.currentTimeMillis();
		fos.close();
		fis.close();
		System.out.println("程序运行时间： "+(end1-start1)+"ms");   //程序运行时间： 1261ms
		
		
	}
	
	/**
	 * 传输多张图片
	 * @throws IOException
	 */
	@Test
	public void pohotos() throws IOException {
		long start1 = System.currentTimeMillis();
		File file = new File("F:\\导包\\Saved Pictures\\&壁纸\\风景\\");
		File[] files = file.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				System.out.println("File "+files[i].getName()+ "num:"+i+1);
				//this.buffFile(files[i]);         //程序运行时间： 467ms
				//this.byteFile(files[i]);       //only程序运行时间： 752785ms
				//this.fileChannel(files[i]);    //程序运行时间： 355ms    复制大文件这个最快(网上测试)
				this.filesCopy(files[i]);        //程序运行时间： 332ms
			}else if(files[i].isDirectory()) {
				System.out.println("Directory "+files[i].getName());
			}
		}
		
		long end1 = System.currentTimeMillis();
		System.out.println("程序运行时间： "+(end1-start1)+"ms");  //buff程序运行时间： 2064ms
		
	/*	
	    long start1 = System.currentTimeMillis();
	   
		System.out.println("程序运行时间： "+(end1-start1)+"ms");
		*/
		
		
		
	}
	private void byteFile(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream("F:\\ztest\\"+file.getName());
			int b = fis.read();
			while(b != -1) {
				fos.write(b);
				b = fis.read();
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	/**
	 * 使用传统io
	 * @param file
	 * @throws IOException
	 */
	private void buffFile(File file) throws IOException {
		try {
			
			
			
			
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			File newFile = new File("F:\\ztest\\1111\\2222\\"+file.getName());
			File parentFile = newFile.getParentFile();
			 if (!parentFile.exists()) {
				 parentFile.mkdirs(); 
             }
			FileOutputStream fos = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] b = new byte[1024];
			int len = bis.read(b);
			while(len != -1) {
				bos.write(b,0,len);
				len = bis.read(b);
			}
			
			fos.close();
			fis.close();
			bos.close();
			bis.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 使用fileChannel
	 * @param file
	 * @throws IOException
	 */
	private void fileChannel(File file) throws IOException {
		try {
			FileChannel channelI = new FileInputStream(file).getChannel();
			File newFile = new File("F:\\ztest\\1111\\2222\\"+file.getName());
			File parentFile = newFile.getParentFile();
			 if (!parentFile.exists()) {
				 parentFile.mkdirs(); 
             }
			 FileChannel channelO = new FileOutputStream(newFile).getChannel();

			 channelO.transferFrom(channelI, 0, channelI.size());
			
			 channelI.close();
			 channelO.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 使用Java7的Files类复制
	 * @param file
	 * @throws IOException
	 */
	private void filesCopy(File file) throws IOException {
		try {

			File newFile = new File("F:\\ztest\\1111\\2222\\"+file.getName());
			File parentFile = newFile.getParentFile();
			 if (!parentFile.exists()) {
				 parentFile.mkdirs(); 
             }
			 Files.copy(file.toPath(), newFile.toPath());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 转换流
	 * 字节转字符
	 */
	
	
	/**
	 * 数据流 data流
	 */
	
}
