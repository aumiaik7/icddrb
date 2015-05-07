package com.icddrb.app.wbendLineenvsamplingd2;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

public class ReadWriteObject {

	public static void writeListToFile(Context contxt, MyListTracker ml) {
		ObjectOutputStream output = null;
		File fileToWrite = new File(contxt.getCacheDir(), "mylist" + ".data");
		try {
			output = new ObjectOutputStream(new FileOutputStream(fileToWrite));
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
		try {
			output.writeObject(ml);
		} catch (IOException e) {
			e.printStackTrace();
		} // output record
	}

	public static MyListTracker openFileAndRead(Context contxt) {
		ObjectInputStream input = null;
		MyListTracker ml = null;
		File fileFromRead = new File(contxt.getCacheDir(), "mylist" + ".data");
		try {
			input = new ObjectInputStream(new FileInputStream(fileFromRead));
		} catch (IOException ioException) {
			return ml;
		}
		try {
			ml = (MyListTracker) input.readObject();

		} catch (EOFException endOfFileException) {
			return ml;
		} catch (ClassNotFoundException classNotFoundException) {
			return ml;
		} catch (IOException ioException) {
			return ml;
		}
		try {
			if (input != null)
				input.close();
		} catch (IOException ioException) {
		}
		return ml;
	}

}
