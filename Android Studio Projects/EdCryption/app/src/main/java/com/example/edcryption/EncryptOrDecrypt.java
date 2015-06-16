package com.example.edcryption;

import java.io.File;

import net.sqlcipher.database.SQLiteDatabase;

public class EncryptOrDecrypt {
	
	public static boolean encrypt(File unencrypFile,String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
    	File unencFile = new File(unencrypFile.getAbsolutePath());
		
		 
		 File encFile = new File(MainActivity.APP_PATH+"EDCryption/Encrypted DBs/"+unencrypFile.getName());
		 encFile.delete();
		  try{
			  SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(unencFile, "", null);
			  if (database.isOpen()) {
				  database.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s'",
						  encFile.getAbsolutePath(), password));
				  database.rawExecSQL("select sqlcipher_export('encrypted')");
				  database.rawExecSQL("DETACH DATABASE encrypted");
				  database = SQLiteDatabase.openOrCreateDatabase(encFile, password,
					 null);
				  if(database!=null)
					  flag = true;
				  database.close();

			  }
		  }
		  catch (Exception e)
		  {

		  }

		return flag;
		
	}

	public static boolean decrypt(File encFile,String password)
	{
		boolean flag = false;
		File decFile = new File(MainActivity.APP_PATH+"EDCryption/Decrypted DBs/"+encFile.getName());
		decFile.delete();

		try {
			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(encFile, password, null);
			if (database.isOpen()) {
				database.rawExecSQL(String.format("ATTACH DATABASE '%s' as plaintext KEY '';", decFile.getAbsolutePath()));
				database.rawExecSQL("SELECT sqlcipher_export('plaintext');");
				database.rawExecSQL("DETACH DATABASE plaintext;");
				android.database.sqlite.SQLiteDatabase sqlDB = android.database.sqlite.SQLiteDatabase.openOrCreateDatabase(decFile, null);
				if (sqlDB != null)
					flag = true;
				sqlDB.close();
				database.close();
			}
		}
		catch(Exception e)
		{

		}
		return flag;
		// databaseFile.delete();
	}
}
