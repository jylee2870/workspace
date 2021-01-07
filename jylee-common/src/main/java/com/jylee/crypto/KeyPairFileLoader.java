package com.jylee.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class KeyPairFileLoader {

	public static boolean saveKeyDataToFile(byte[] keyData, String filePath) {
		
		if (keyData == null || filePath == null)
			return false;
		
		try {
			
			ByteBuffer buffer = ByteBuffer.allocateDirect(keyData.length);
			buffer.put(keyData);
			
			buffer.position(0);
			
			FileOutputStream fos = new FileOutputStream(filePath);
			
			FileChannel cout = fos.getChannel();
			cout.write(buffer);
			
			cout.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Logging
			e.printStackTrace();
			return false;
		}
		catch (IOException e) {
			// TODO Logging
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static byte[] loadKeyFromFile(String filePath) {
		
		if (filePath == null)
			return null;
		
		byte[] bytes = null;
		try {
			File file = new File(filePath);
			long len = file.length();
			
			FileInputStream fis = new FileInputStream(filePath);
			
			FileChannel cin = fis.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate((int)len);
			cin.read(buffer);
			
			bytes = buffer.array();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		return bytes;
	}
}
