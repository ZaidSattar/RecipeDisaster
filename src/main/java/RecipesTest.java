package src.main.java;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipesTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    	Recipes r = new Recipes(true);
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
    void testRecipes() {
        System.out.println("Test Recipes");
        Recipes recipe = new Recipes();
        assertNotNull(recipe);
    }

}