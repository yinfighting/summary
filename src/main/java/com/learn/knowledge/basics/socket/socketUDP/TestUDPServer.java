package com.learn.knowledge.basics.socket.socketUDP;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TestUDPServer {

	public static void main(String[] args) throws IOException {
		byte buf[] = new byte[1024];
		
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		DatagramSocket ds = new DatagramSocket(5678);
		while(true) {
			ds.receive(dp);  //阻塞式得方法
			//System.out.println(new String(buf, 0, dp.getLength()));
			ByteArrayInputStream bais = new ByteArrayInputStream(buf);
			DataInputStream dis= new DataInputStream(bais);
			long l = dis.readLong();
			System.out.println(l);
		}
		
	}
	
	
	
}
