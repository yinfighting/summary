package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TxtZip {

	public static void main(String[] args) throws Exception {
		
		List<String> list = new ArrayList<String>();
		list.add("111111111111111111111");
		list.add("111222222222222222222");
		list.add("33333333333333333333333");
		FileWriter fw = null;
		
		BufferedWriter bw = null;
		List<File> files = new ArrayList<File>();
			
		for (int i = 0; i < list.size(); i++) {
			String result =list.get(i);
			fw = new FileWriter("test"+i+".txt", true);
			bw = new BufferedWriter(fw, 100);
			bw.write(result);
			bw.flush();
			files.add(new File("test"+i+".txt"));
		}
	
		bw.close();
		fw.close();
		
		byte[] buffer = new byte[1024];
		String strZipName = "Demo.zip";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream out = new ZipOutputStream(outputStream);
	
		for(int i=0;i<files.size();i++) {
			File temp = files.get(i);
			FileInputStream fis = new FileInputStream(temp);
			out.putNextEntry(new ZipEntry(temp.getName()));
			int len;
			while((len = fis.read(buffer))>0) {
				out.write(buffer,0,len);
			}
			out.closeEntry();
			fis.close();	
		}
		byte[] data = outputStream.toByteArray();
		response.reset();
        response.setHeader("Content-Disposition","attachment;fileName="+downloadName);
        response.addHeader("Content-Length", ""+data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        IOUtils.write(data, out);
        out.flush();
        out.close();
		out.close();
		
		System.out.println("生成Demo.zip成功");
	
	}
}
