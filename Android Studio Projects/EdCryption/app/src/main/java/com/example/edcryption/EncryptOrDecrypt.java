package com.example.edcryption;

import java.io.File;

import net.sqlcipher.database.SQLiteDatabase;

public class EncryptOrDecrypt {
	
	public static boolean encrypt(File newFile,String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
    	File unencFile = new File(newFile.getAbsolutePath());
		
		 
		 File encFile = new File(MainActivity.APP_PATH+"EDCryption/Encrypted DBs/"+newFile.getName());
		 encFile.delete();
		  
		  SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(unencFile, "", null); 
		  if (database.isOpen()) {
			  database.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s'",
					  encFile.getAbsolutePath(), password));
		      database.rawExecSQL("select sqlcipher_export('encrypted')");
		      database.rawExecSQL("DETACH DATABASE encrypted");
		      database.close();

			  database = SQLiteDatabase.openOrCreateDatabase(encFile, password,
                 null);
			  if(database!=null)
				  flag = true;
			  database.close();
			
		  }

		return flag;
		
	}
}
