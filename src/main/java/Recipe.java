package src.main.java;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

/**
 * Recipe object class, which contains methods that pertain to the Recipe
 * object.
 * 
 * @author Collin Zhong
 *
 */
public class Recipe {
	/** The ID of the recipe, which will be used internally. */
	private int id;
	/** The name of the recipe, which will be displayed to the user. */
	private String name;
	/** The difficulty of the recipe, which is used in internal calculations. */
	private int difficulty;
	/** The file that holds the recipe's information */
	private File file;

	/** The ingredients of the recipe */
	private ArrayList<Integer> ingredients;
	/** The steps of the recipe, in order */
	private ArrayList<Integer> steps;

	/**
	 * Empty constructor class.
	 */
	public Recipe() {
	}

	/**
	 * Constructor class for a Recipe.
	 * 
	 * @param file The file that the recipe's information is stored in.
	 */
	public Recipe(File file) {
		this.file = file;
		ingredients = new ArrayList<Integer>();
		steps = new ArrayList<Integer>();
		loadRecipe();
	}

	/**
	 * Constructor class for a Recipe, which takes all of its information as
	 * parameters.
	 * 
	 * @param id          ID of the recipe
	 * @param name        Name of the recipe
	 * @param difficulty  Difficulty of the recipe
	 * @param ingredients Ingredients of the recipe
	 * @param steps       Steps of the recipe, in order
	 */
	public Recipe(int id, String name, int difficulty, ArrayList<Integer> ingredients, ArrayList<Integer> steps) {
		this.id = id;
		this.name = name;
		this.difficulty = difficulty;
		this.ingredients = ingredients;
		this.steps = steps;
	}

	/**
	 * Loads the data of the file.
	 */
	public void loadRecipe() {
		loadRecipe(file);
	}

	/**
	 * Loads the data of the file. Overload: allows specifying what file should be
	 * loaded
	 * 
	 * @param file The file to load
	 */
	private void loadRecipe(File file) {
		try {
			FileEditor fe = new FileEditor(file);

			getID(file);

			BufferedReader br = fe.readFile();

			getIdentifiers(br.readLine()); // identifiers

			getIngredients(br.readLine()); // ingredients

			getSteps(br.readLine()); // steps

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the ID of the recipe.
	 * 
	 * @param file The file to read
	 */
	private void getID(File file) {
		try {
			String fileName = file.getName();
			fileName = fileName.replaceAll("recipe", "");
			fileName = fileName.replaceAll(".csv", "");
			this.id = Integer.valueOf(fileName);
		} catch (Exception e) {
			System.out.println("Exception in Recipe.getID");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the name and difficulty of the recipe.
	 * 
	 * @param line The line that contains the identifiers.
	 */
	private void getIdentifiers(String line) {
		try {
			String[] idLine = line.split(",");
			name = idLine[0];
			try {
				difficulty = Integer.valueOf(idLine[1].strip());
			} catch (ArrayIndexOutOfBoundsException a) {
				System.out.println("No difficulty was found in " + this.name + ", defaulting to 0");
				difficulty = 0;
			}
		} catch (Exception e) {
			System.out.println("Exception in Recipe.getIdentifiers");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the ingredients of the recipe.
	 * 
	 * @param line The line that contains the ingredients.
	 */
	private void getIngredients(String line) {
		try {
			String[] ingredientsLine = line.split(",");
			for (int i = 0; i < ingredientsLine.length; i++) {
				try {
					this.ingredients.add(Integer.valueOf(ingredientsLine[i].strip()));
				} catch (Exception e) {
					System.out.println("Read error in Recipe.getIngredients: " + ingredientsLine[i]);
					e.printStackTrace();
				}
			}
		} catch (IndexOutOfBoundsException i) {
			System.out.println("Exception in Recipe.getIngredients: IndexOutOfBounds");
		} catch (Exception e) {
			System.out.println("Exception in Recipe.getIngredients");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the steps of the recipe.
	 * 
	 * @param line The line that contains the steps.
	 */
	private void getSteps(String line) {
		try {
			String[] stepsLine = line.split(",");
			for (int i = 0; i < stepsLine.length; i++) {
				this.steps.add(Integer.valueOf(stepsLine[i].strip()));
			}
		} catch (Exception e) {
			System.out.println("Exception in Recipe.getSteps");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the list of ingredients as strings in an ArrayList.
	 * 
	 * @return An ArrayList of all ingredients in String form
	 */
	public ArrayList<String> getIngredientsStrings() {
		ArrayList<String> ing = new ArrayList<String>();
		Ingredients i = new Ingredients(true);
		for (Integer integer : ingredients) {
			ing.add(i.getIngredients().get(integer));
		}
		return ing;
	}

	/**
	 * Returns the list of steps as Strings in an ArrayList.
	 * 
	 * @return An ArrayList of all steps in String form
	 */
	public ArrayList<String> getStepsStrings() {
		ArrayList<String> ste = new ArrayList<String>();
		Steps s = new Steps(true);
		for (Integer integer : ingredients) {
			ste.add(s.getSteps().get(integer));
		}
		return ste;
	}

	/*
	 * Getters and setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		this.getFile();
	}

	public ArrayList<Integer> getIngredients() {
		return ingredients;
	}

	public int getIngredient(int i) {
		return ingredients.get(i);
	}

	public void setIngredients(ArrayList<Integer> ingredients) {
		this.ingredients = ingredients;
	}

	public ArrayList<Integer> getSteps() {
		return steps;
	}

	public void setSteps(ArrayList<Integer> steps) {
		this.steps = steps;
	}
}
