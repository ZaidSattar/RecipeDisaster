package src.main.java;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Holds a static ArrayList that contains all ingredients, for ease of access.
 * 
 * @author Collin Zhongs
 *
 */
public class Ingredients {

	/** The list of ingredients */
	public static ArrayList<String> ingredients;

	/**
	 * Empty constructor. Used to get access to the static ingredients ArrayList.
	 */
	public Ingredients() {
	}

	/**
	 * Constructor class. Used to get access to the static ingredients ArrayList, as
	 * well as loading the values from the CSV file into the ArrayList.
	 * 
	 * @param val If true, loads values from the CSV file.
	 */
	public Ingredients(boolean val) {
		if (val) {
			ingredients = new ArrayList<String>();
			loadIngredients();
		}
	}

	/**
	 * Loads ingredients from the CSV file into the ArrayList.
	 */
	private void loadIngredients() {
		try {
			FileEditor fe = new FileEditor("ingredients.csv");
			BufferedReader br = fe.readFile();
			br.readLine(); // title line
			String line = br.readLine();
			while (line != null) {// read all lines in the file
				if (!ingredients.contains(line)) {// do not take duplicate lines
					ingredients.add(line);
					System.out.println("Ingredients: loaded " + line);
				}
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the ArrayList of ingredients.
	 * 
	 * @return The static ArrayList of ingredients
	 */
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
}
