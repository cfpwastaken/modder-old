package de.cfp.modder.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	
	public static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
	    if (!destinationDirectory.exists()) {
	        destinationDirectory.mkdir();
	    }
	    for (String f : sourceDirectory.list()) {
	        copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
	    }
	}
	
	public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
	    if (source.isDirectory()) {
	        copyDirectory(source, destination);
	    } else {
	        copyFile(source, destination);
	    }
	}
	
	public static void copyFile(File sourceFile, File destinationFile) throws IOException {
	    try (InputStream in = new FileInputStream(sourceFile); 
	      OutputStream out = new FileOutputStream(destinationFile)) {
	        byte[] buf = new byte[1024];
	        int length;
	        while ((length = in.read(buf)) > 0) {
	            out.write(buf, 0, length);
	        }
	    }
	}
	
	public static boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	public static void applyMods(String profile) {
		deleteDirectory(new File(Constants.MODS));
		try {
			copyDirectory(new File(Constants.MODDER + "\\" + profile + "\\mods"), new File(Constants.MODS));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createProfile(String profile) {
		try {
			File profileFolder = new File(Constants.MODDER + "\\" + profile);
			profileFolder.mkdirs();
			
			FileWriter myWriter = new FileWriter(Constants.MODDER + "\\" + profile + "\\" + profile + ".mp");
			myWriter.write("Modder Profile. This file is TODO"); // TODO: Actually have a use for it duh
			myWriter.close();
			
			File modsFolder = new File(profileFolder, "\\mods");
			modsFolder.mkdirs();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openProfileFolder(String profile) {
		try {
			Runtime.getRuntime().exec("explorer.exe \"" + Constants.MODDER + "\\" + profile + "\\mods\"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteProfile(String profile) {
		deleteDirectory(new File(Constants.MODDER + "\\" + profile));
	}
	
	public static boolean profileExists(String profile) {
		return new File(Constants.MODDER + "\\" + profile).exists();
	}
	
}
