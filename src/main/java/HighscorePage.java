package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The HighscorePage class represents the window displaying the high score table
 * with the top 10 players.
 * 
 * It extends JFrame and contains a JTable to display the high scores along with
 * a main menu button.
 * 
 * The high score data is fetched from the Users class.
 * 
 * @author viktoriya.li
 */
public class HighscorePage extends JFrame {

	private JTable highscoreTable;
	private JButton menu;
	private JLabel backgroundLabel;
	private OutlinedLabel headingLabel, headingLabel2;
	private soundPlayer playSound;
	private String page = "h";

	/**
	 * Constructor for the HighscorePage class. Sets up the window, titles, table,
	 * and menu button.
	 */
	public HighscorePage(User userx) {

		// Set window properties
		setTitle("High Score Table");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set page titles
		headingLabel = new OutlinedLabel("HIGHSCORE TABLE ");
		headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 48));

		headingLabel2 = new OutlinedLabel("Top 10 Players ");
		headingLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		headingLabel2.setFont(new Font("Trebuchet MS", Font.BOLD, 48));

		// Create a label for background image
		backgroundLabel = new JLabel();
		add(backgroundLabel);

		// Create a panel to center components
		JPanel panel = new JPanel(new GridBagLayout());

		// Add keyboard shortcuts
		keyboard kb = new keyboard(page);
		panel.addKeyListener(kb);
		panel.setFocusable(true);
		panel.requestFocusInWindow();

		panel.setOpaque(false);

		backgroundLabel.setLayout(new BorderLayout());
		backgroundLabel.add(panel, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.CENTER;

		panel.add(headingLabel, gbc);
		gbc.gridy = 1; // empty row
		panel.add(headingLabel2, gbc);

		// Create table model
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

		// Populate table with top 10 usernames, scores, and ranks
		int count = 0;
		int rank = 1;
		for (User user : sortedUsers) {
			if (count < 10) {
				model.addRow(new Object[] { rank, user.getUsername(), user.getScore() });
				rank++;
				count++;
			} else {
				break;
			}
		}

		// Create the table
		highscoreTable = new JTable(model);

		// Set table font and row height
		highscoreTable.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		highscoreTable.setRowHeight(40);

		// Set column header font style and size
		JTableHeader header = highscoreTable.getTableHeader();
		header.setFont(new Font("Trebuchet MS", Font.BOLD, 20));

		// Set grid line color
		highscoreTable.setGridColor(Color.black);

		// Center-align column headers
		((DefaultTableCellRenderer) highscoreTable.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		// Center-align data inside the "Rank" and "High Score" columns
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		highscoreTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // rank column data
		highscoreTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // high score column data

		// Set column widths
		TableColumnModel columnModel = highscoreTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(50); // rank column width
		columnModel.getColumn(1).setPreferredWidth(200); // username column width
		columnModel.getColumn(2).setPreferredWidth(100); // high Score column width

		// Show the table
		JScrollPane scrollPane = new JScrollPane(highscoreTable);
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.gridx = 0;
		gbcScrollPane.gridy = 2;
		gbcScrollPane.anchor = GridBagConstraints.CENTER;
		panel.add(scrollPane, gbcScrollPane);

		// Create and configure the main menu button
		menu = new JButton("Main Menu");
		menu.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		menu.setPreferredSize(new Dimension(200, 50));

		GridBagConstraints gbcMenu = new GridBagConstraints();
		gbcMenu.gridx = 0;
		gbcMenu.gridy = 3;
		gbcMenu.anchor = GridBagConstraints.CENTER;
		panel.add(menu, gbcMenu);

		playSound = new soundPlayer();

		// Add action listener to the menu button
		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				String username = userx.getUsername();
				new menu(userx,users.getUser(username).getLevel());

				try {
					playSound.playSoundOnce("button sound.wav");

				} catch (Exception ex) {
					System.out.println("Sound not found");
				}

			}
		});

		// Add component listener to resize background image
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				try {
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(getClass().getResource("highscore.png"))
							.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
					backgroundLabel.setIcon(imageIcon);

				} catch (NullPointerException exception) {
					System.out.println("Image not found");

				}
			}

		});

		setVisible(true);
	}

	/**
	 * The main method to launch the HighscorePage.
	 * 
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
//		new HighscorePage(userx);

	}

}
