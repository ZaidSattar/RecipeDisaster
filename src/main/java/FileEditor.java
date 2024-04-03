package src.main.java;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Provides methods for reading and editing CSV files.
 * 
 * @author Collin Zhong
 *
 */
public class FileEditor {
	/** The file to be read and edited */
	private File file;
	/** The absolute path of the file */
	private String absPath;
	/** The lines in the file, stored in an ArrayList */
	private ArrayList<String> lines;

	/**
	 * Constructor for a FileEditor.
	 * 
	 * Overload: if no file is set.
	 */
	public FileEditor() {
	}

	/**
	 * Constructor for a FileEditor.
	 * 
	 * Overload: if the name of the file is given.
	 * 
	 * @param title The name of the file
	 */
	public FileEditor(String title) {
		retrieveFile(title);
	}

	/**
	 * Constructor for a FileEditor.
	 * 
	 * Overload: if the file is given directly.
	 * 
	 * @param file The file object
	 */
	public FileEditor(File file) {
		this.file = file;
		retrieveFile(file.getName());
	}

	/**
	 * Returns a BufferedReader for the file.
	 * 
	 * @return A BufferedReader for the file
	 */
	public BufferedReader readFile() {
		try {
			return new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the file by its name. If the file does not exist, creates the file.
	 * Also gets the absolute path, as well as loads the file into this object.
	 * 
	 * @param title The name of the file
	 * @return True if the file is found, false if the file had to be created.
	 */
	public boolean retrieveFile(String title) {
		boolean foundFile = false;
		try {
			if (title.endsWith(".csv")) {
				file = new File(title);
			} else {
				file = new File(title + ".csv");
			}
			if (file.createNewFile()) {
				System.out.print("New file " + file + " created at ");
				foundFile = false;
			} else {
				System.out.print("Existing file " + file + " found at ");
				foundFile = true;
			}
			absPath = file.getAbsolutePath();
			System.out.println(absPath);
			lines = new ArrayList<>(); // reset lines ArrayList
			loadFile();
		} catch (Exception e) {
			System.out.println(e);
		}
		return foundFile;
	}

	/**
	 * Loads the contents of the file into the ArrayList.
	 */
	public void loadFile() {
		try {
			this.lines = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String line = br.readLine();
				// System.out.println(line);
				if (line == null) {
					System.out.println("File loaded: " + file.getName());
					break;
				} else {
					if (!line.isEmpty()) {
						lines.add(line);
					}
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Gets all values within a specified column in the file.
	 * 
	 * @param col The column number to retrieve from.
	 * @return An ArrayList that contains all values within the column.
	 */
	public ArrayList<String> getColumn(int col) {
		ArrayList<String> alstr = new ArrayList<String>();
		try {
			BufferedReader br = this.readFile();
			String line = br.readLine();
			while (line != null) {
				try {
					String[] splitLine = line.split(",");
					alstr.add(splitLine[col]);

				} catch (IndexOutOfBoundsException ioobe) {
					System.out.println("Error in FileEditor.getColumn: 1");
					ioobe.printStackTrace();
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alstr;
	}

	/**
	 * Finds the first line that starts with id and replaces the line with text
	 * 
	 * @param id   The beginning of the line to search for
	 * @param text The text to replace the line with
	 * @return True if a line was replaced, false if not.
	 */
	public boolean overwriteLine(String id, String text) {
		try {
			this.lines = new ArrayList<String>();
			this.loadFile();
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				String segment = line.split(",")[0]; // retrieve first part of line
				if (segment.equals(id)) {
					System.out.println("Line:\n\t" + lines.get(i) + "\nhas been replaced with");
					lines.set(i, text);
					System.out.println("\t" + lines.get(i));
					this.overwriteFile();
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("No line begins with {" + id + "}, so no lines were overwritten.");
		return false;
	}

	/**
	 * Overwrites the entire file with the contents of the ArrayList.
	 */
	public void overwriteFile() {
		try {
			this.resetFile();
			Iterator<String> itr = lines.iterator();
			FileWriter fw = new FileWriter(file, true);
			while (itr.hasNext()) {
				// System.out.println("write");
				fw.write(itr.next() + "\n");
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Appends a line to the file.
	 * 
	 * @param text The line to append
	 * @return True if the line was successfully added, false if not
	 */
	public boolean addLine(String text) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getName(), true));
			bw.write(text + "\n");
			bw.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public ArrayList<String> getLines() {
		return this.lines;
	}

	/**
	 * Counts the number of lines in the file passed as parameter.
	 * 
	 * @param file The file to count for
	 * @return How many lines there are
	 */
	public int getLineCount(File file) {
		try {
			this.loadFile();
			return this.lines.size();
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	/**
	 * Counts the number of lines in the file.
	 * 
	 * @return How many lines there are
	 */
	public int getLineCount() {
		return (getLineCount(file));
	}

	/**
	 * Sets the file
	 * 
	 * @param file The file to set
	 */
	public void setFile(File file) {
		this.file = file;
		this.loadFile();
	}

	/**
	 * Returns the file
	 * 
	 * @return The file stored in this object
	 */
	public File getFile() {
		return this.file;
	}

	/**
	 * Deletes the contents of the file.
	 */
	public void resetFile() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
