package src.main.java;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Creates a completely random recipe.
 * 
 * The name of this file, Spiral Abyss, is a reference to the mobile game
 * Genshin Impact's endgame domain, the Spiral Abyss.
 * 
 * @author Collin Zhong
 *
 */
public class SpiralAbyss {
	private int difficulty;
	private int id = 0;
	private File file;
	private ArrayList<Integer> ingredients;
	private ArrayList<Integer> steps;
	private Ingredients ing;
	private Steps ste;
	private String name;

	/**
	 * Creates a random recipe, with a given difficulty.
	 * 
	 * @param difficulty The difficulty of the recipe
	 */
	public SpiralAbyss(int difficulty) {
		if (difficulty < 1) {
			System.out.println("Difficulty too low. Aborting.");
		}
		this.difficulty = difficulty;
		this.ingredients = new ArrayList<Integer>();
		this.steps = new ArrayList<Integer>();
		ing = new Ingredients(true);
		ste = new Steps(true);
		generateRecipe();
		saveRecipe();
	}

	/**
	 * Generates the recipe completely randomly.
	 */
	private void generateRecipe() {
		FileEditor fe = new FileEditor();
		boolean foundFile = fe.retrieveFile(makeName());
		while (foundFile == true) {
			foundFile = fe.retrieveFile(makeName());
		}
		this.id--;
		fe.retrieveFile(makeName(this.id));
		this.file = fe.getFile();
		int numIngredients = (int) (2 * difficulty * Math.random()) + 1;
		int numSteps = (int) (2 * difficulty * Math.random()) + 1;
		for (int i = 0; i < numIngredients; i++) {
			int ingredient = (int) (ing.getIngredients().size() * Math.random());
			System.out.println(ingredient);
			if (!this.ingredients.contains(ingredient)) {
				this.ingredients.add(ingredient);
			}
		}
		for (int i = 0; i < numSteps; i++) {
			int step = (int) ((ste.getSteps().size() - 1) * Math.random()) + 1;
			System.out.println(step);
			if (!this.ingredients.contains(step)) {
				this.steps.add(step);
			}
		}
	}

	/**
	 * Saves the recipe into a file for future use.
	 */
	private void saveRecipe() {
		FileEditor fe = new FileEditor();
		fe.setFile(this.file);
		this.name = randomName();
		// identifier
		String identifierLine = this.name + "," + this.difficulty;
		// ingredients
		String ingredientsLine = "";
		Iterator<Integer> itr1 = ingredients.iterator();
		if (itr1.hasNext()) {
			ingredientsLine += itr1.next();
		}
		while (itr1.hasNext()) {
			ingredientsLine += ",";
			ingredientsLine += itr1.next();
		}

		// steps
		String stepsLine = "";
		Iterator<Integer> itr2 = steps.iterator();
		if (itr2.hasNext()) {
			stepsLine += itr2.next();
		}
		while (itr2.hasNext()) {
			stepsLine += ",";
			stepsLine += itr2.next();
		}
		// add it to the file
		fe.addLine(identifierLine);
		fe.addLine(ingredientsLine);
		fe.addLine(stepsLine);
		System.out.println(ingredientsLine);
		System.out.println(stepsLine);
	}

	/**
	 * Generates a file name, if the ID has not been decided yet.
	 * 
	 * @return The filename that was generated
	 */
	private String makeName() {
		String name = "infinity" + this.id++ + ".csv";
		return name;
	}

	/**
	 * Generates a file name, if the ID has been decided already.
	 * 
	 * @param id The ID that will be used
	 * @return The filename that was generated
	 */
	private String makeName(int id) {
		String name = "infinity" + id + ".csv";
		return name;
	}

	/**
	 * Returns a random name that consists of an adjective from the first array, and
	 * a noun from the second array.
	 * 
	 * @return
	 */
	private String randomName() {
		String[] pre = { "Dubious", "Questionable", "Existential", "Experimental", "Special", "Disastrous", "Strange",
				"Dangerous", "Apocalyptic", "Chaotic", "Secret", "Magical", "Abyssal", "Entropic" };
		String[] post = { "Meal", "Dish", "Creation", "Bowl", "Existence", "Experiment", "Recipe", "Potion", "Plate",
				"Manifestation", "Being", "Threat", "Disaster" };

		return getRandom(pre) + " " + getRandom(post);
	}

	/**
	 * Picks a random index from an array.
	 * 
	 * @param array The array to randomly choose from
	 * @return The value of the chosen index
	 */
	private String getRandom(String[] array) {
		int rnd = (int) (Math.random() * array.length);
		return array[rnd];
	}

	/**
	 * Gets the ID of the recipe.
	 * 
	 * @return The ID of the recipe
	 */
	public int getID() {
		return this.id;
	}

	public ArrayList<Integer> getIngredients() {
		return this.ingredients;
	}

	public ArrayList<Integer> getSteps() {
		return this.steps;
	}

	public Recipe getRecipe() {
		return new Recipe(this.id, this.name, this.difficulty, this.ingredients, this.steps);
	}

	public String getName() {
		return this.name;
	}
}
