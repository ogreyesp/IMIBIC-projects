package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.ArrayList;

public class WriteReadFromFile {

	/**
	 * @param arg
	 * @return An array list with the different lines saved on the file
	 * @throws IOException
	 */
	public ArrayList<String> read(String arg) throws IOException {

		ArrayList<String> lines;
		
		try (BufferedReader read = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(arg))))) {
			lines = new ArrayList<String>();
			String line;
			
			while ((line = read.readLine()) != null) {

				line = line.trim();

				if (line.equals("")) {
					continue;
				}

				if (!line.startsWith("//") && !line.startsWith("%")) {
					lines.add(line);
				}

			}
		}

		return lines;
	}

	/**
	 * @param arg
	 *            the path of the directory
	 * @throws IOException
	 */
	public void createDirectories(String arg) throws IOException {

		String args[] = arg.split("/");

		StringBuilder path = new StringBuilder();

		for (int i = 0; i < args.length - 1; i++) {

			path.append(args[i].trim()).append("/");

			File file = new File(path.toString());

			if (!file.exists()) {
				Files.createDirectories(new File(path.toString()).toPath());
			}
		}
	}

	/**
	 * @param arg
	 *            the path of the directory
	 * @return 
	 * @throws IOException
	 */
	public BufferedWriter createFile(String arg) throws IOException {

		return new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File(arg))));
	}
}