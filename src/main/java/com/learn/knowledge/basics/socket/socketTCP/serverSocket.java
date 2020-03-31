package com.learn.knowledge.basics.socket.socketTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class serverSocket {

	/**
	 * 服务端接收客户端的消息
	 * @param args
	 * @throws IOException
	 */
//	public static void main(String[] args) throws IOException {
//		ServerSocket serverSocket = new ServerSocket(9000);
//		while(true) {
//			Socket socket = serverSocket.accept();
//			DataInputStream dis = new DataInputStream(socket.getInputStream());
//			System.out.println(dis.readUTF());
//			dis.close();
//			socket.close();
//			System.out.println("a client connect");
//		}	
//	}
	
	
	/**
	 * 服务端发送消息给客户端
	 * @param args
	 * @throws IOException
	 */
	/*public static void main(String[] args) throws IOException {
		try {
			ServerSocket serverSocket = new ServerSocket(9000);
			while (true) {
				Socket socket = serverSocket.accept();
				OutputStream outputStream = socket.getOutputStream();
				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				dataOutputStream.writeUTF("Hello," + socket.getInetAddress() + "port#" + socket.getPort() + " bye-bye");
				dataOutputStream.close();
				socket.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("程序运行异常:" + e);
		}

	}*/
	
	
	/**
	 * 服务端喝客户端相互传递信息
	 * 服务端先读后写
	 * @param args
	 * @throws IOException
	 */
	/*public static void main(String[] args) throws IOException {
		try {
			ServerSocket serverSocket = new ServerSocket(9000);
			while (true) {
				Socket socket = serverSocket.accept();
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				
				DataInputStream dataInputStream = new DataInputStream(inputStream);
				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				
				String s = null;
				if((s= dataInputStream.readUTF()) != null) {
					System.out.println(s);
					System.out.println("inetaddress:"+ socket.getInetAddress());
					System.out.println("port:" + socket.getPort());
				}
				
				dataOutputStream.writeUTF("hello, 客户端");
				
				dataInputStream.close();
				dataOutputStream.close();
				socket.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("程序运行异常:" + e);
		}

	}*/
	
	
	/**
	 * 服务端与客户端一对一会话
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			ServerSocket serverSocket = new ServerSocket(9000);
				Socket socket = serverSocket.accept();
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				
				Scanner sc = new Scanner(System.in);
				DataInputStream dataInputStream = new DataInputStream(inputStream);
				DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
				
				String s = "";
				while(!s.equals("exit")) {
					System.out.println("client:" + dataInputStream.readUTF());
					System.out.println("server:" + s);
					s= sc.nextLine();
					dataOutputStream.writeUTF(s);
				}
				dataInputStream.close();
				dataOutputStream.close();
				sc.close();
				socket.close();
				serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("程序运行异常:" + e);
		}

	}
	
	
}
