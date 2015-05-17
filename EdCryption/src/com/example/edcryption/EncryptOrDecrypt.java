package com.example.edcryption;

import java.io.File;

import net.sqlcipher.database.SQLiteDatabase;

public class EncryptOrDecrypt {
	
	public static void encrypt(String absolutePath,String password) {
		// TODO Auto-generated method stub
    	File unencFile = new File(absolutePath);
		
		 
		 File encFile = new File("enCIFHEVSurveillance.sqlite");
		  encFile.delete();
		  
		  SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(unencFile, "", null); 
		  if (database.isOpen()) {
			  database.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s'",
					  encFile.getAbsolutePath(), "test123"));
		      database.rawExecSQL("select sqlcipher_export('encrypted')");
		      database.rawExecSQL("DETACH DATABASE encrypted");
		      database.close();
		   
		   database = SQLiteDatabase.openOrCreateDatabase(encFile, "test123",
                 null);
			
			database.close();
			
		  }
		
	}
}
