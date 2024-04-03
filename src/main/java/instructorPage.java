package src.main.java;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * This is the instructor page that display the contents of the instructor mode
 * that consists of highscore and usernames
 * 
 * @author regina.liang
 * @author viktoriya.li
 */
public class instructorPage extends JFrame {
	private OutlinedLabel headingLabel, headingLabel2;
	private JPanel panel;
	private JButton quit;
	private JTable scoreTable;
	private String page = "I";
	private JLabel backgroundLabel;
	private soundPlayer playSound;

	/**
	 * instructorPage constructor. Construct a panel for displaying the content in
	 * instructor mode Including a table with highscore and usernames
	 */
	public instructorPage() {
		// Set the window for Instructor Mode
		setTitle("Instructor Page");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// create instance for the music to play
		playSound = new soundPlayer();
		playSound.playSoundLoop("instructor sound.wav");

		// Display game title in the centre
		headingLabel = new OutlinedLabel("INSTRUCTOR MODE ");
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 48));
		add(headingLabel, BorderLayout.NORTH);

		// same principle for the second page title
		headingLabel2 = new OutlinedLabel("View all players: ");
		headingLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 48));

		// create a label with an empty icon for background image
		backgroundLabel = new JLabel();
		add(backgroundLabel);

		panel = new JPanel(new GridBagLayout());

		// Add keyboard shortcuts
		keyboard kb = new keyboard(page);
		panel.addKeyListener(kb); // add keyboard listener
		panel.setFocusable(true); // allow to panel to receive keyboard key events
		panel.requestFocusInWindow(); // Request focus for the panel

		// make the panel transparent so the image is visible
		panel.setOpaque(false);

		// set the layout for the panel and add it in the centre
		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(panel, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;

		panel.add(headingLabel, gbc);
		gbc.gridy = 1; // empty row
		panel.add(headingLabel2, gbc);

		// create a table model with "Rank", "Username" and "High Score" columns
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("Rank");
		model.addColumn("Username");
		model.addColumn("High Score");

		// Fetch sorted scores from Users class
		Users users = new Users(true);
		ArrayList<User> sortedUsers = users.sortScores();

		// Populate table with all usernames and scores 
		// unlike Highscore table, instructor is able to view all the users
		int rank = 1;
		for (User user : sortedUsers) {
			model.addRow(new Object[] { rank, user.getUsername(), user.getScore() });
			rank++;
		}

		// Create the table using the table model
		scoreTable = new JTable(model);

		// Set table font and row height
		scoreTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		scoreTable.setRowHeight(40);

		// set the column header font style and size
		JTableHeader header = scoreTable.getTableHeader();
		header.setFont(new Font("Trebuchet MS", Font.BOLD, 20));

		// Set grid line color
		scoreTable.setGridColor(Color.BLACK);

		// center-align column headers
		((DefaultTableCellRenderer) scoreTable.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);

		// center-align data inside the "Rank" and "High Score" columns
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		scoreTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // rank column data
		scoreTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // high score column data

		// set column widths
		TableColumnModel columnModel = scoreTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50); // rank column width
		columnModel.getColumn(1).setPreferredWidth(200); // username column width
		columnModel.getColumn(2).setPreferredWidth(100); // high Score column width

		// code to show the table, put after the table is created so it's visible
		// create a scroll pane for the table
		JScrollPane scrollPane = new JScrollPane(scoreTable);

		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 2;
		gbcScrollPane.anchor = GridBagConstraints.CENTER;
		panel.add(scrollPane, gbcScrollPane);

		// code for the main menu button
		// create a button name, set the font and size
		// add the button function
		quit = new JButton("Quit");
		quit.setFont(new Font("Trebuchet MS", Font.BOLD, 20)); // set font style and size for button text
		quit.setPreferredSize(new Dimension(200, 50)); // set button size

		// add the menu button under the table
		// spacing for the button
		GridBagConstraints gbcMenu = new GridBagConstraints();
		gbcMenu.gridx = 0;
		gbcMenu.gridy = 3;
		gbcMenu.anchor = GridBagConstraints.CENTER;
		panel.add(quit, gbcMenu);

		quit.addActionListener(new ActionListener() {
			/**
			 * actionPerformed method for the button: quit It will perform an action after
			 * the button being clicked
			 * 
			 * @param e
			 */
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		setVisible(true);

		// method for accessing and stretching the image to fit full screen
		// Add a ComponentListener to the JFrame
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				// try-catch block in case there's no picture
				try {
					// scale the image icon every time the JFrame is resized
					// use the relative path to the file
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("highscore.png"))
							.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
					backgroundLabel.setIcon(imageIcon);

				} catch (NullPointerException exception) {
					System.out.println("Image not found");

				}
			}

		});

	}

	/**
	 * This is the main method, it can run the instructorPage by creating an new
	 * instructorPage object
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new instructorPage();
	}

}