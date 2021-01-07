package com.jylee.crypto.klib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class KlibTest {
	
	public static void main(String args[]) {
		if(args.length != 1) {
			System.out.println("KlibTest args : filepath ");
			System.exit(1);
		}
		String filepath = args[0];
		
		try {
			System.out.println(CryptoLib.start());
	
	        byte[] lan = CryptoLib.getLandom(32);
	        byte[] key = CryptoLib.getKey();
	        byte[] eb=null, db=null;
	        
	        String planText = "";
	        
	        // byte[] planText1="start--asdfa;ljksoisjf03294u0iorejfsolkjlkjgfoujn9340vb0teu3tbv[4p2n3r5qi90cu5p0t94u3c90utmvosjf;loasdljgflk1234546890--end".getBytes();
	        byte[] inbytes = getBytesFromFile(new File(filepath));
	        // planText = new String(inbytes,0,inbytes.length);
	        // System.out.println("planText->/"+planText+"/ length:"+inbytes.length);
	        System.out.println("inbytes length :"+inbytes.length);
	        
	        long estime = System.nanoTime();
	        eb = CryptoLib.encrypt(key,inbytes);
	        long eetime = System.nanoTime();
	        long et = eetime-estime;
	        
	        System.out.println("encrypt time "+et);
	        System.out.println("encrypt length : "+eb.length);
	        
	        long dstime = System.nanoTime();
	        db = CryptoLib.decrypt(key,eb);
	        
	        long detime = System.nanoTime();
	        long dt = detime-dstime;
	        
	        System.out.println("decrypt time "+et);
	        System.out.println("decrypt length : "+db.length);
	        // System.out.println("decrypt str "+new String(db,0,db.length));
	        // System.out.println("decrypt str "+new String(db));
	        // System.out.println("getLandom->"+  new String(lan,0,lan.length));
	        // System.out.println("getKey->"+  new String(key,0,key.length));
	        CryptoLib.end();
	        // System.out.println(CryptoLib.end());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
	     InputStream is = new FileInputStream(file);
	     long length = file.length();
	     // You cannot create an array using a long type.
	     // It needs to be an int type.
	     // Before converting to an int type, check
	     // to ensure that file is not larger than Integer.MAX_VALUE.
	     if (length > Integer.MAX_VALUE) {
	         // File is too large
	     }
	     // Create the byte array to hold the data
	     byte[] bytes = new byte[(int)length];
	     // Read in the bytes
	     int offset = 0;
	     int numRead = 0;
	     while (offset < bytes.length
	            && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	         offset += numRead;
	     }
	     // Ensure all the bytes have been read in
	     if (offset < bytes.length) {
	         throw new IOException("Could not completely read file "+file.getName());
	     }
	     // Close the input stream and return bytes
	     is.close();
	     return bytes;
	 }
}