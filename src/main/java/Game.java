package src.main.java;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Methods related to the game state.
 * 
 * @author Collin Zhong
 *
 */
public class Game {
	/** The recipe the game is running off of */
	Recipe recipe;
	/** The list of ingredients the player has entered */
	ArrayList<Integer> ingredientsList;
	/** The list of steps the player has entered, ordered */
	ArrayList<Integer> stepsList;

	/**
	 * Creates a new Game object.
	 */
	public Game() {
		Ingredients i = new Ingredients(true);
		i.getIngredients();
		Steps s = new Steps(true);
		s.getSteps();
		ingredientsList = new ArrayList<Integer>();
		stepsList = new ArrayList<Integer>();
	}

	/**
	 * Creates a new Game object, using the given file as the recipe.
	 * 
	 * @param file The recipe the game will use
	 */
	public Game(File file) {
		Ingredients i = new Ingredients();
		i.getIngredients();
		Steps s = new Steps();
		s.getSteps();
		recipe = new Recipe(file);
		ingredientsList = randomIngredients();
		stepsList = randomSteps();
	}

	/**
	 * Creates, shuffles, and returns an ArrayList that contains all the correct
	 * ingredients for the given recipe, as well as a random number of incorrect
	 * ingredients, based on difficulty.
	 * 
	 * @return An ArrayList that contains the IDs of all the ingredients in
	 *         randomized order
	 */
	public ArrayList<Integer> randomIngredients() {
		ArrayList<Integer> randomIngredients = new ArrayList<Integer>();
		randomIngredients.addAll(recipe.getIngredients());
		for (int i = 0; i < (int) (recipe.getDifficulty() * Math.random()); i++) {
			Ingredients ing = new Ingredients();
			int rand = (int) (ing.getIngredients().size() * Math.random());
			if (!randomIngredients.contains(rand)) { // no duplicates
				// this may result in having less than the expected number of incorrect answers,
				// but that's fine.
				randomIngredients.add(rand);
			}
		}
		Collections.shuffle(randomIngredients);
		return randomIngredients;
	}

	/**
	 * Creates, shuffles, and returns an ArrayList that contains all the correct
	 * steps for the given recipe, as well as a random number of incorrect steps,
	 * based on difficulty.
	 * 
	 * @return An ArrayList that contains the IDS of all the steps in randomized
	 *         order
	 */
	public ArrayList<Integer> randomSteps() {
		ArrayList<Integer> randomSteps = new ArrayList<Integer>();
		randomSteps.addAll(recipe.getSteps());
		for (int i = 0; i < (int) (recipe.getDifficulty() * Math.random()); i++) {
			Steps ing = new Steps();
			int rand = (int) (ing.getSteps().size() * Math.random());
			if (!randomSteps.contains(rand)) { // no duplicates
				// this may result in having less than the expected number of incorrect answers,
				// but that's fine.
				randomSteps.add(rand);
			}
		}
		Collections.shuffle(randomSteps);
		return randomSteps;
	}

	/**
	 * Counts the number of correct ingredients by iterating over the Arraylist of
	 * ingredients, then checking if each is in the recipe's ingredients list. At
	 * the end, returns the number of correct ingredients. Unordered.
	 * 
	 * @param ingredients The ingredients to check against the recipe
	 * @return The number of correct ingredients
	 */
	public int checkIngredients(ArrayList<Integer> ingredients) {
		return checkIngredients(this.recipe, ingredients);
	}

	/**
	 * Counts the number of correct ingredients by iterating over the Arraylist of
	 * ingredients, then checking if each is in the recipe's ingredients list. At
	 * the end, returns the number of correct ingredients. Unordered.
	 * 
	 * @param recipe      The recipe to be checked against
	 * @param ingredients The ingredients to check against the recipe
	 * @return The number of correct ingredients
	 */
	private int checkIngredients(Recipe recipe, ArrayList<Integer> ingredients) {
		ArrayList<Integer> answer = recipe.getIngredients();
		int count = 0;
		Iterator<Integer> itr = ingredients.iterator();
		while (itr.hasNext()) {
			int ingredient = itr.next();
			if (answer.contains(ingredient)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Counts and returns the number of correct steps.
	 * 
	 * Counts the number of correct steps by iterating over the ArrayList of steps,
	 * then checking if each is in the same position in the recipe's steps list. At
	 * the end, returns the number of correct steps in the correct position.
	 * Ordered.
	 * 
	 * @param steps The steps to check against the recipe
	 * @return How many steps were correct
	 */
	public int checkSteps(ArrayList<Integer> steps) {
		return checkSteps(this.recipe, steps);
	}

	/**
	 * Counts and returns the number of correct steps.
	 * 
	 * Counts the number of correct steps by iterating over the ArrayList of steps,
	 * then checking if each is in the same position in the recipe's steps list. At
	 * the end, returns the number of correct steps in the correct position.
	 * Ordered.
	 * 
	 * @param recipe The recipe to check against the steps
	 * @param steps  The steps to check against the recipe
	 * @return How many steps were correct
	 * 
	 */
	private int checkSteps(Recipe recipe, ArrayList<Integer> steps) {
		ArrayList<Integer> answer = recipe.getSteps();
		int count = 0;
		Iterator<Integer> itr = steps.iterator();
		int stepNum = 0;
		while (itr.hasNext()) {
			int step = itr.next();
			if (answer.get(stepNum).equals(step)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Determines if the recipe was completely correctly answered. Checks if all of
	 * the ingredients were correct, and if all the steps were correct and in order.
	 * 
	 * @param ingredients The ingredients to check against the recipe
	 * @param steps       The steps to check against the recipe
	 * @return True if the recipe was correctly recreated, false otherwise.
	 */
	public boolean correctRecipe(ArrayList<Integer> ingredients, ArrayList<Integer> steps) {
		// how to use
		// ingredients is the list of ingredients the user chose, doesn't have to be in
		// order.
		// steps is the list of steps the user chose, has to be in order, otherwise the
		// method will not work correctly
		if (checkIngredients(recipe, ingredients) == ingredients.size()
				&& recipe.getIngredients().size() == ingredients.size() && checkSteps(recipe, steps) == steps.size()
				&& recipe.getSteps().size() == steps.size()) {
			return true;
		} else {
			return false;
		}
	}

}
