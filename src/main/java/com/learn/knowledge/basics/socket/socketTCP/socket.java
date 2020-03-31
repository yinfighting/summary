package com.learn.knowledge.basics.socket.socketTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class socket {

	/**
	 * 客户端发送消息给服务端
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		Socket socket = new Socket("127.0.0.1", 9000);
//		OutputStream os = socket.getOutputStream();
//		DataOutputStream dos = new DataOutputStream(os);
//		dos.writeUTF("hello, server!");
//		dos.flush();
//		dos.close();
//		socket.close();
//
//	}
	
	
	/**
	 * 客户端接收从服务端返回的信息
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	/*public static void main(String[] args) throws UnknownHostException, IOException {
		try {
			Socket socket = new Socket("127.0.0.1", 9000);
			InputStream inputStream = socket.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			String readUTF = dataInputStream.readUTF();
			System.out.println(readUTF);
			dataInputStream.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("服务器连接失败" + e);
		}

	}*/
	
	
	/**
	 * 客户端与服务端相互传递消息
	 * 客户端先写后读
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	/*public static void main(String[] args) throws UnknownHostException, IOException {
		try {
			Socket socket = new Socket("127.0.0.1", 9000);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			
			dataOutputStream.writeUTF("hello, 服务器!");
			
			String  s= null;
			if((s=dataInputStream.readUTF()) != null) {
				System.out.println(s);
			}
			
			dataOutputStream.close();
			dataInputStream.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("服务器连接失败" + e);
		}

	}*/
	
	
	/**
	 * 客户端与服务端一对一会话
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		try {
			Socket socket = new Socket("127.0.0.1", 9000);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			Scanner sc = new Scanner(System.in);
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			
			System.out.println("请输入回话:");
			String  s= sc.nextLine();
			dataOutputStream.writeUTF(s);
			
			while(!s.equals("exit")) {
				System.out.println("clent: " + s);
				System.out.println("server: " + dataInputStream.readUTF());
				s = sc.nextLine();
				dataOutputStream.writeUTF(s);
			}
			
			dataOutputStream.close();
			dataInputStream.close();
			sc.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("服务器连接失败" + e);
		}

	}
	
}
