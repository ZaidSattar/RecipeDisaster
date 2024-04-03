package src.main.java;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Holds the static ArrayList of steps for ease of access, as well as relevant
 * methods for loading the ArrayList from a CSV file.
 * 
 * @author Collin Zhong
 *
 */
public class Steps {
	/** The list of steps. */
	private static ArrayList<String> steps;

	/**
	 * Empty constructor, for access to the static ArrayList.
	 */
	public Steps() {
	}

	/**
	 * Constructor class that, if the parameter is set to true, loads all steps from
	 * storage.
	 * 
	 * @param val If true, loads steps from storage.
	 */
	public Steps(boolean val) {
		if (val) {
			steps = new ArrayList<String>();
			loadSteps();
		}
	}

	/**
	 * Loads steps from storage.
	 */
	private void loadSteps() {
		try {
			FileEditor fe = new FileEditor("steps.csv");
			BufferedReader br = fe.readFile();
			br.readLine(); // title line
			String line = br.readLine();
			while (line != null) {// read all lines
				if (!steps.contains(line)) {// do not take duplicates
					steps.add(line);
				}
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the static ArrayList.
	 * 
	 * @return The ArrayList that holds all steps
	 */
	public ArrayList<String> getSteps() {
		return steps;
	}
}
