package com.tyn.jasca.testapp.group4;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.tyn.jasca.testapp.common.Log;
import com.tyn.jasca.testapp.common.Printer;

public class V36GeneralExceptionClass {
	
	public void readFile(String filename) {
		FileReader filereader = null;
		try {
			File file = new File(filename);
			filereader = new FileReader(file);
			Printer.print(filereader.read());
			filereader.close();
		}
		catch (Exception e) {
			Log.log("����");
		}
		finally {
			try {
				if (filereader != null) {
					filereader.close();
				}
			}
			catch (IOException ioe) {
				Log.log("����");
			}
		}
	}
}
