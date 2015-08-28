package com.docgen.tempservice.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipService {

	public static void addToZipFile(File file, ZipOutputStream zipStream){
		try (FileInputStream inputStream = new FileInputStream(file)){
			ZipEntry entry = new ZipEntry(file.getName());
			entry.setTime(file.lastModified());
			entry.setComment("Created by CustomDocs Service");
			zipStream.putNextEntry(entry);
			
			byte[] readBuffer = new byte[2048];
			int amountRead;
			
			while ((amountRead = inputStream.read(readBuffer)) > 0){
				zipStream.write(readBuffer, 0, amountRead);
			}
			zipStream.closeEntry();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
}
