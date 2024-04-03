package src.main.java;
import java.util.ArrayList;

/**
 * Holds a static ArrayList of all recipes, for ease of access.
 * 
 * @author Collin Zhong
 *
 */
public class Recipes {
	/** The list of recipes */
	public static ArrayList<Recipe> recipes;

	/**
	 * Empty constructor, for access to the static ArrayList
	 */
	public Recipes() {
	}

	/**
	 * Constructor that, if the parameter is set to true, loads all recipes from
	 * memory.
	 * 
	 * @param val If true, loads recipes from memory. Else, acts as an access point
	 *            to the static ArrayList.
	 */
	public Recipes(boolean val) {
		if (val) {
			recipes = new ArrayList<Recipe>();
			int count = 0;
			FileEditor fe = new FileEditor();
			while (fe.retrieveFile("recipe" + count + ".csv")) {// while there is a preexisting one
				count++;
				Recipe r = new Recipe(fe.getFile());
				r.loadRecipe();
				recipes.add(r);
			}
		}
	}

}
