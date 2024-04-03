package src.main.java;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpiralAbyssTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("infinity0.csv");
		file.delete();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		SpiralAbyss f12 = new SpiralAbyss(5);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		File file = new File("infinity0.csv");
		file.delete();
	}

/*
	@Test
	public void testGenerateRecipe() {
		SpiralAbyss f12 = new SpiralAbyss(5);
		assertNotNull(f12);
		assertNotNull(f12.getIngredients());
		assertNotNull(f12.getSteps());
	}
*/

	@Test
	public void testSaveRecipe() {
		SpiralAbyss f12 = new SpiralAbyss(5);
		assertTrue(new File("infinity0.csv").exists());
	}

	@Test
	public void testMakeName() {
		SpiralAbyss f12 = new SpiralAbyss(5);
		assertEquals(0, f12.getID());
	}

/*	@Test
	public void testRandomName() {
		SpiralAbyss f12 = new SpiralAbyss(5);
		assertNotNull(f12.getName());
	}

 */
}
