package com.tyn.jasca.common;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @author S.J.H.
 */
public class TempFileManager {
	
	private static final File tempDirectory;
	static {
		tempDirectory = new File(System.getProperty("java.io.tmpdir"), "Jasca");
		tempDirectory.mkdirs();
		tempDirectory.deleteOnExit();
		
		// TODO ���۽� ��� ���� ���� (recursive)
		// ���丮�� ������� ������ tempDirectory�� �������� ����
	}
	
	/**
	 * 
	 * @return
	 */
	public static File getTempDirectory() {
		return tempDirectory;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static File createTempFile() throws IOException {
		File tempFile = File.createTempFile("_jasca_", ".tmp", tempDirectory);
		tempFile.deleteOnExit();
		
		return tempFile;
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public static File createTempFileFromResource(String resource) {
		File tempFile = null;
		try {
			tempFile = createTempFile();
			Utils.resourceToFile(resource, tempFile.getCanonicalPath());
			
			return tempFile;
		}
		catch (IOException ioe) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public static String createTempFilepathFromResource(String resource) {
		try {
			return createTempFileFromResource(resource).getCanonicalPath();
		}
		catch (IOException ioe) {
			return null;
		}
	}
}
