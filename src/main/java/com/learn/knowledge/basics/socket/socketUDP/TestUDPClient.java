package com.learn.knowledge.basics.socket.socketUDP;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TestUDPClient {

	public static void main(String[] args) throws IOException {
		//byte[] buf = "Hello".getBytes();
		/*Long l =  new Long(12345l);  
		System.out.println(l.byteValue());
		System.out.println(l.intValue());
		byte aa = 57;
		Byte aaa= new Byte(aa);
		System.out.println(aaa.longValue());*/
		long n = 10000L;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(n);
		
		byte[] buf = baos.toByteArray();
		
		
		DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 5678));
		
		DatagramSocket ds = new DatagramSocket(9999);
		
		ds.send(dp);
		ds.close();
		
		
		
	}
}
