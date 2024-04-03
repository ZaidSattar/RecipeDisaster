package src.main.java;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientsTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testIngredients() {
        System.out.println("Test Ingredients");
        Ingredients ingredients = new Ingredients();
        assertNotNull(ingredients);
    }

    @Test
    void testIngredientsBoolean() {
        System.out.println("Test IngredientsBoolean");
        // Test constructor with boolean parameter set to true
        Ingredients ingredients = new Ingredients(true);
        ArrayList<String> ingredient = ingredients.ingredients;
        assertNotNull(ingredient);
        // Assuming there are some ingredients loaded
        assertFalse(ingredients.getIngredients().isEmpty());
    }

    @Test
    void testGetIngredients() {
        System.out.println("Test GetIngredients");
        // Test the getIngredients() method
        Ingredients ingredients = new Ingredients();
        ArrayList<String> ingredient = ingredients.ingredients;
        assertNotNull(ingredient);
    }

}
