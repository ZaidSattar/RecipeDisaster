package src.main.java;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author colli
 *
 */
class FileEditorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("test.csv");
		file.createNewFile();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File file = new File("test.csv");
		file.delete();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		FileEditor fe = new FileEditor("test");
		assertTrue(fe.addLine("collin,zhong,251305521"));
		assertTrue(fe.addLine("nilloc,gnohz,335480356"));
		assertTrue(fe.addLine("wolf,claw,3812"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		FileEditor fe = new FileEditor("test");
		fe.resetFile();
	}

	@Test
	public void testRetrieveFile() {
		FileEditor fe = new FileEditor("test");
		assertTrue(fe.retrieveFile("test.csv"));
	}

	public void testReadFile() {
		FileEditor fe = new FileEditor("test");
		assertNotNull(fe.readFile());
	}

	@Test
	public void testAddLine() {
		FileEditor fe = new FileEditor("test");
		fe.resetFile();
		assertTrue(fe.addLine("collin,zhong,251305521"));
		assertTrue(fe.addLine("nilloc,gnohz,335480356"));
		assertTrue(fe.addLine("wolf,claw,3812"));

	}

	@Test
	public void testGetLineCount() {
		FileEditor fe = new FileEditor("test");
		assertEquals(3, fe.getLineCount());
	}

	@Test
	public void testGetColumn() {
		FileEditor fe = new FileEditor("test");
		ArrayList<String> column = fe.getColumn(2);
		assertEquals(3, column.size());
		assertEquals("251305521", column.get(0));
		assertEquals("335480356", column.get(1));
		assertEquals("3812", column.get(2));
	}

	@Test
	public void testOverwriteLine() {
		FileEditor fe = new FileEditor("test");
		assertTrue(fe.overwriteLine("collin", "Collin,Zhong,251305521"));
		assertFalse(fe.overwriteLine("glug", "incorrect"));
		assertEquals("Collin,Zhong,251305521", fe.getLines().get(0));
	}

	@Test
	public void testResetFile() {
		FileEditor fe = new FileEditor("test");
		fe.resetFile();
		fe.loadFile();
		assertEquals(fe.getLineCount(), 0);
	}

}
