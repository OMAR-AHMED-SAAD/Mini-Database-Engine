package views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import exceptions.DBAppException;
import fonts.Fonts;
import java.awt.Font;

@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener {
	Font font = new Fonts("").myfont.deriveFont(22f);
	Font fontw = new Fonts("welcome").myfont.deriveFont(22f);
	Font fontd = new Fonts("d").myfont.deriveFont(22f);
	Font fonti = new Fonts("i").myfont.deriveFont(22f);

	Container cPane = new Container();
	CardLayout crd = new CardLayout();

	private ImageIcon icon;

	JPanel mainJPanel;
	PanelWithBack leftSide;
	JPanel leftTop;
	JButton help;
	JButton goCode;

	JTextPane welcomeText;
	private String message = "Welcome to ZERO, a mini database engine where you can create, insert, delete or select rows, we've got you covered.\nJust don't ask us to divide by ZERO. That's a no-no. For more features and tips, check our help page. Enjoy your queries."
			+ "\n\t\t\t      ~ ZERO Team.";
	@SuppressWarnings("unused")
	private volatile boolean isCancelled = false;
	Controller controller;
	JPanel helpJPanel;
	PanelWithBack backPanel;
	JEditorPane frontPanel;
	JButton goback;

	JPanel QueryJPanel;
	JButton help2;
	JButton modeButton;
	JButton home;
	JButton newFile;
	JButton save;
	JButton open;
	JButton refresh;
	JButton closeTab;
	JButton run;
	JTextArea consoleJPanel;
	JTextPane leftPanel;
	JTabbedPane tabbedPane;

	String last = "a";
	String mode = "n";
	ImageIcon scaledIconN;
	ImageIcon scaledIconL;

	public View() throws IOException, DBAppException {
		// setting icon in dock
		icon = new ImageIcon("src/main/resources/Images/logo.PNG");
		setIconOnMac();

		// set content panel layout to card layout
		cPane.setLayout(crd);
		createMainPnl();
		createHelpPnl();
		createQueryPnl();
		controller = new Controller(this);
		controller.GetCurrTables();

		// set up the frame and add the cpane to it
		this.setContentPane(cPane);
		this.setTitle("ZERO DBMS");
		this.setSize(new Dimension(1150, 700));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createMainPnl() {
		mainJPanel = new JPanel();
		help = new JButton("Help !");
		goCode = new JButton("Let's Code");
		mainJPanel.setLayout(null);
		// set mainPanel background
		leftSide = new PanelWithBack("src/main/resources/Images/landingPage.JPEG", 1168, 700);
		leftSide.setBounds(0, 0, 1150, 700);

		// set side box of already existing tables
		leftTop = new JPanel();
		leftTop.setBounds(40, 230, 420, 320);
		leftTop.setBackground(new Color(255, 255, 255, 150));
		leftTop.setLayout(null);
		leftTop.setBorder(BorderFactory.createLineBorder(new Color(115, 28, 201), 1, true));

		welcomeText = new JTextPane();
		welcomeText.setEditable(false);
		welcomeText.setSelectionColor(Color.LIGHT_GRAY);
		welcomeText.setForeground(new Color(75, 2, 115));
		welcomeText.setFont(font.deriveFont(21f));
		welcomeText.setBounds(25, 20, 370, 230);
		// welcomeText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new
		// Color(75, 2, 115), 2, true),"Welcome To Zero"));
		welcomeText.setBorder(BorderFactory.createLineBorder(new Color(75, 2, 115), 1, true));
		leftTop.add(welcomeText);
		startTypingAnimation();

		// set button to redirect to helpPage
		help.setBackground(Color.lightGray);// new Color(75, 2, 115));
		help.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		help.setOpaque(true);
		help.setFont(font);
		help.setBounds(25, 263, 100, 40);
		help.addActionListener(this);
		leftTop.add(help);

		// set button to go to query services panel
		goCode.setForeground(Color.WHITE);
		goCode.setBackground(new Color(75, 2, 115));
		goCode.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		goCode.setOpaque(true);
		goCode.setFont(font);
		goCode.setBounds(195, 263, 200, 40);
		goCode.addActionListener(this);
		leftTop.add(goCode);

		// add background top box and bottom box to the main panel
		mainJPanel.add(leftTop);
		mainJPanel.add(leftSide);

		cPane.add(mainJPanel, "a");
	}

	private void startTypingAnimation() {
		new SwingWorker<Void, Character>() {
			@Override
			protected Void doInBackground() throws Exception {
				for (int i = 0; i < message.length(); i++) {
					if (isCancelled()) {
						break; // Stop the animation if cancelled
					}
					publish(message.charAt(i));
					Thread.sleep(60); // Adjust typing speed here
				}
				return null;
			}

			@Override
			protected void process(java.util.List<Character> chunks) {
				Document doc = welcomeText.getDocument();
				try {
					for (Character c : chunks) {
						doc.insertString(doc.getLength(), String.valueOf(c), null);
					}
				} catch (BadLocationException ex) {
					ex.printStackTrace();
				}
			}

			@Override
			protected void done() {
				if (isCancelled()) {
					System.out.println("Animation cancelled.");
				} else {
					System.out.println("Animation finished.");
				}
			}
		}.execute();
	}

	public void cancelAnimation() {
		isCancelled = true;
	}

	public void createHelpPnl() {
		helpJPanel = new JPanel();
		helpJPanel.setLayout(null);
		backPanel = new PanelWithBack("src/main/resources/Images/CretionBackgrd.JPEG", 1150, 675);
		backPanel.setBounds(0, 0, 1150, 700);

		frontPanel = new JEditorPane();
		frontPanel.setEditable(false);
		frontPanel.setBounds(0, 0, 750, 500);
		frontPanel.setBackground(new Color(255, 255, 255, 180));
		frontPanel.setLayout(null);
		frontPanel.setFont(fontw.deriveFont(24f));
		// frontPanel.setLineWrap(true);
		// frontPanel.setWrapStyleWord(true);
		readHelp();

		JScrollPane frontPanelj = new JScrollPane(frontPanel);
		frontPanelj.setBounds(200, 110, 750, 500);
		frontPanelj.setBorder(BorderFactory.createLineBorder(new Color(75, 2, 115), 2, true));
		frontPanelj.setBackground(new Color(255, 255, 255, 180));
		frontPanel.setCaretPosition(0);

		goback = new JButton();
		goback.setToolTipText("Go to previous page");
		goback.setBackground(Color.lightGray);
		// goback.setBackground(new Color(75, 2, 115));
		goback.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		goback.setOpaque(true);
		goback.setFont(font);
		goback.setBounds(200, 40, 60, 60);
		goback.addActionListener(this);
		ImageIcon gobackIcon = new ImageIcon("src/main/resources/Images/left.png");
		Image image = gobackIcon.getImage();
		Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		goback.setIcon(scaledIcon);

		helpJPanel.add(goback);
		helpJPanel.add(frontPanelj);
		helpJPanel.add(backPanel);
		cPane.add(helpJPanel, "b");
	}

	public void createQueryPnl() {
		QueryJPanel = new JPanel();
		cPane.add(QueryJPanel, "c");
		QueryJPanel.setLayout(null);
		QueryJPanel.setBackground(new Color(75, 2, 115));

		QueryJPanel.setBorder(BorderFactory.createLineBorder(new Color(115, 28, 201), 2, true));
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 1150, 40);
		topPanel.setBackground(new Color(41, 41, 41));
		topPanel.setLayout(null);
		topPanel.setBorder(BorderFactory.createLineBorder(new Color(115, 28, 201), 2, true));

		help2 = new JButton();
		help2.setToolTipText("Go to Help Page");
		// help2.setForeground(Color.white);
		help2.setBackground(Color.lightGray);// new Color(75, 2, 115));
		help2.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		help2.setOpaque(true);
		help2.setFont(font.deriveFont(16f));
		help2.setBounds(1110, 5, 30, 30);
		help2.addActionListener(this);
		Image image = new ImageIcon("src/main/resources/Images/interrogation.png").getImage();
		Image scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		help2.setIcon(scaledIcon);
		topPanel.add(help2);

		modeButton = new JButton();
		modeButton.setToolTipText("Tab light mode");
		modeButton.setBackground(Color.lightGray);// new Color(75, 2, 115));
		modeButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		modeButton.setOpaque(true);
		modeButton.setFont(font.deriveFont(16f));
		modeButton.setBounds(1070, 5, 30, 30);
		modeButton.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/brightness.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIconL = new ImageIcon(scaledImage);
		image = new ImageIcon("src/main/resources/Images/night-mode.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIconN = new ImageIcon(scaledImage);
		modeButton.setIcon(scaledIconL);
		topPanel.add(modeButton);

		home = new JButton();
		home.setToolTipText("Go to Home Page");
		home.setBackground(Color.lightGray);// new Color(75, 2, 115));
		home.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		home.setOpaque(true);
		home.setFont(font.deriveFont(16f));
		home.setBounds(1030, 5, 30, 30);
		home.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/home.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		home.setIcon(scaledIcon);
		topPanel.add(home);

		newFile = new JButton();
		newFile.setToolTipText("New File");
		newFile.setBackground(Color.lightGray);// new Color(75, 2, 115));
		newFile.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		newFile.setOpaque(true);
		newFile.setFont(font.deriveFont(16f));
		newFile.setBounds(10, 5, 30, 30);
		newFile.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/new-document.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		newFile.setIcon(scaledIcon);
		topPanel.add(newFile);

		save = new JButton();
		save.setToolTipText("Save File");
		save.setBackground(Color.lightGray);// new Color(75, 2, 115));
		save.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		save.setOpaque(true);
		save.setFont(font.deriveFont(16f));
		save.setBounds(50, 5, 30, 30);
		save.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/diskette.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		save.setIcon(scaledIcon);
		topPanel.add(save);

		open = new JButton();
		open.setToolTipText("Open file in new tab");
		open.setBackground(Color.lightGray);// new Color(75, 2, 115));
		open.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		open.setOpaque(true);
		open.setFont(font.deriveFont(16f));
		open.setBounds(90, 5, 30, 30);
		open.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/open-folder-with-document.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		open.setIcon(scaledIcon);
		topPanel.add(open);

		refresh = new JButton();
		refresh.setToolTipText("Refresh Contents");
		refresh.setBackground(Color.lightGray);// new Color(75, 2, 115));
		refresh.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		refresh.setOpaque(true);
		refresh.setFont(font.deriveFont(16f));
		refresh.setBounds(130, 5, 30, 30);
		refresh.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/refresh.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		refresh.setIcon(scaledIcon);
		topPanel.add(refresh);

		closeTab = new JButton();
		closeTab.setToolTipText("Close Current Tab");
		closeTab.setBackground(Color.lightGray);// new Color(75, 2, 115));
		closeTab.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		closeTab.setOpaque(true);
		closeTab.setFont(font.deriveFont(16f));
		closeTab.setBounds(170, 5, 30, 30);
		closeTab.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/close.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		closeTab.setIcon(scaledIcon);
		topPanel.add(closeTab);

		run = new JButton();
		run.setToolTipText("Run selected query (Command+r)");
		run.setBackground(Color.GREEN);// new Color(75, 2, 115));
		run.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		run.setOpaque(true);
		run.setFont(font.deriveFont(16f));
		run.setBounds(231, 5, 30, 30);
		run.addActionListener(this);
		image = new ImageIcon("src/main/resources/Images/play.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		run.setIcon(scaledIcon);

		run.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("meta R"), "refresh");
		run.getActionMap().put("refresh", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(run)) {
					JScrollPane js = (JScrollPane) tabbedPane.getSelectedComponent();
					JTextArea ta = (JTextArea) js.getViewport().getView();
					controller.run(ta.getSelectedText());
				}
			}
		});
		topPanel.add(run);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(1, 639, 1148, 30);
		bottomPanel.setOpaque(false);
		bottomPanel.setLayout(null);
		JButton sql = new JButton();
		sql.setBackground(Color.ORANGE);// new Color(75, 2, 115));
		sql.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		sql.setOpaque(true);
		sql.setFont(font.deriveFont(16f));
		sql.setBounds(1030, 0, 30, 30);
		image = new ImageIcon("src/main/resources/Images/files.png").getImage();
		scaledImage = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		scaledIcon = new ImageIcon(scaledImage);
		sql.setIcon(scaledIcon);
		JButton name = new JButton("ZERO\u00A9");
		name.setBackground(Color.ORANGE);// new Color(75, 2, 115));
		name.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		name.setOpaque(true);
		name.setFont(font.deriveFont(20f));
		name.setBounds(1070, 0, 70, 30);
		bottomPanel.add(name);
		bottomPanel.add(sql);

		leftPanel = new JTextPane();
		leftPanel.setEditable(false);
		leftPanel.setBounds(0, 0, 230, 600);
		leftPanel.setBackground(new Color(41, 41, 41));
		leftPanel.setForeground(Color.white);
		leftPanel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		JScrollPane leftPanelj = new JScrollPane(leftPanel);
		leftPanelj.setBounds(1, 70, 230, 565);
		leftPanelj.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
		leftPanel.setCaretPosition(0);

		JLabel label = new JLabel("\t Created Tables");
		label.setForeground(Color.white);
		label.setBackground(new Color(41, 41, 41));
		label.setOpaque(true);
		label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
		label.setFont(font);
		label.setBounds(1, 40, 230, 30);

		consoleJPanel = new JTextArea();
		consoleJPanel.setEditable(false);
		consoleJPanel.setBounds(0, 0, 900, 200);
		consoleJPanel.setFont(fontd);// new Font(Font.MONOSPACED, Font.PLAIN, 18));
		consoleJPanel.setBackground(new Color(41, 41, 41));
		consoleJPanel.setForeground(Color.GREEN);
		consoleJPanel.setText(" Output will appear here");
		// consoleJPanel.setLineWrap(true);
		// consoleJPanel.setWrapStyleWord(true);

		JScrollPane consoleJPanelj = new JScrollPane(consoleJPanel);
		consoleJPanelj.setBounds(242, 435, 900, 200);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Console");
		titledBorder.setTitleColor(Color.BLACK);
		titledBorder.setTitleFont(fontd.deriveFont(18f));
		titledBorder.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
		consoleJPanelj.setBorder(titledBorder);
		consoleJPanel.setCaretPosition(0);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(231, 40, 918, 400);
		tabbedPane.setBackground(new Color(75, 2, 115));
		tabbedPane.setOpaque(true);
		createNewTab(null);

		QueryJPanel.add(topPanel);
		QueryJPanel.add(consoleJPanelj);
		QueryJPanel.add(label);
		QueryJPanel.add(tabbedPane);
		QueryJPanel.add(leftPanelj);
		QueryJPanel.add(bottomPanel);

	}

	private void createNewTab(String s) {
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		textArea.setBackground(new Color(41, 41, 41));
		textArea.setForeground(new Color(235, 213, 246));
		textArea.setCaretPosition(0);
		textArea.setOpaque(true);
		textArea.setCaretColor(Color.white);
		LineNumberingTextArea lt = new LineNumberingTextArea(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setRowHeaderView(lt);
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				lt.updateLineNumbers();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				lt.updateLineNumbers();
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				lt.updateLineNumbers();
			}
		});
		lt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
		if (s == null)
			tabbedPane.addTab("Tab " + tabbedPane.getTabCount(), scrollPane);
		else
			tabbedPane.addTab(s, scrollPane);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
	}

	public void readHelp() {
		String helpPage = "<html>\n"
				+ "  <body style=\"font-family: Arial, sans-serif; font-size: 14px; margin: 20px;\">\n"
				+ "    <h1 style=\"color: #006699; font-size: 24px;\">ZERO DBMS Help Page</h1>\n"
				+ "    <hr style=\"border-top: 2px solid #ddd; margin-bottom: 20px;\">\n"
				+ "    <h2 style=\"color: #741f99; font-size: 20px;\">Query Types:</h2>\n"
				+ "    <ul style=\"margin-left: 20px;\">\n" + "      <li>Create table</li>\n"
				+ "      <li>Insert (with and without values)</li>\n" + "      <li>Update</li>\n"
				+ "      <li>Delete</li>\n" + "      <li>Select</li>\n" + "      <li>Create index (octree only)</li>\n"
				+ "    </ul>\n" + "    <h2 style=\"color: #741f99; font-size: 20px;\">Constraints:</h2>\n"
				+ "    <ul style=\"margin-left: 20px;\">\n"
				+ "      <li>Multiple queries execution is not currently supported.</li>\n"
				+ "      <li>Allowed data types: Int/Integer, Char/Varchar(x), Double(x,y), Date format->YYYY-MM-DD (Only).</li>\n"
				+ "      <li>Primary key must be specified in the table creation after entering the columns like this: PRIMARY KEY(column_name).</li>\n"
				+ "      <li>Only allowed to update one row through primary key column.</li>\n"
				+ "      <li>Delete will accept only ANDed conditions of equality operation.</li>\n" + "    </ul>\n"
				+ "    <h2 style=\"color: #741f99; font-size: 20px;\">Notes:</h2>\n"
				+ "    <ul style=\"margin-left: 20px;\">\n" + "      <li>The system is not case sensitive.</li>\n"
				+ "      <li>Not advised to use reserved words such as UPDATE, CREATE, INSERT but it is allowed.</li>\n"
				+ "      <li>String can be between single or double quotations.</li>\n"
				+ "      <li>Semicolons are optional.</li>\n" + "    </ul>\n"
				+ "    <h2 style=\"color: #741f99; font-size: 20px;\">Examples:</h2>\n"
				+ "    <p style=\"margin-left: 20px;\">\n" + "      <strong>Create table:</strong><br>\n"
				+ "      CREATE TABLE employees ( id INT, name VARCHAR(50), age INT, salary DOUBLE(6,2), dob DATE,PRIMARY KEY(id));<br><br>\n"
				+ "      <strong>Insert:</strong><br>\n"
				+ "      INSERT INTO employees (id, name, age, salary, dob) VALUES (1, 'John Smith', 30, 5000.50, '1992-05-20');<br><br>\n"
				+ "      <strong>Update:</strong><br>\n"
				+ "      UPDATE employees SET salary = 6000.75 WHERE id = 1;<br><br>\n"
				+ "      <strong>Delete:</strong><br>\n"
				+ "      DELETE FROM employees WHERE id = 1 AND age = 30;<br><br>\n"
				+ "      <strong>Select:</strong><br>\n"
				+ "      SELECT * FROM employees WHERE salary > 4000;<br><br>\n"
				+ "      <strong>Create index (octree only):</strong><br>\n"
				+ "      CREATE INDEX idx_employees ON employees (salary,name,age) USING OCTREE;<br>\n" + "    </p>\n"
				+ "  </body>\n" + "</html>";
		frontPanel.setContentType("text/html");
		frontPanel.setText(helpPage);

	}

	private void saveToFile(JScrollPane a) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SQL files", "sql");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try (PrintWriter writer = new PrintWriter(file)) {
				writer.print(((JTextArea) a.getViewport().getView()).getText());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
			}
		}
	}

	private void loadFromFile() {

		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SQL files", "sql");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			createNewTab(file.getName());
			JScrollPane js = (JScrollPane) tabbedPane.getSelectedComponent();
			JTextArea ta = (JTextArea) js.getViewport().getView();
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}
				ta.setText(sb.toString());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Error loading file: " + ex.getMessage());
			}
		}

	}

	public void setIconOnMac() {
		this.setIconImage(icon.getImage());
		final Taskbar taskbar = Taskbar.getTaskbar();
		try {
			taskbar.setIconImage(icon.getImage());
		} catch (final UnsupportedOperationException e) {
			System.out.println("The os does not support: 'taskbar.setIconImage'");
		} catch (final SecurityException e) {
			System.out.println("There was a security exception for: 'taskbar.setIconImage'");
		}
	}

	private class PanelWithBack extends JPanel {
		String imagePath;
		int width;
		int height;

		private PanelWithBack(String imagePath, int width, int height) {
			super();
			this.imagePath = imagePath;
			this.width = width;
			this.height = height;

		}

		@Override
		public void paintComponent(Graphics g) {
			ImageIcon i = new ImageIcon(imagePath);
			Image i1 = i.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
			i = new ImageIcon(i1);
			super.paintComponent(g);
			g.drawImage(i1, 0, 0, null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(help)) {
			last = "a";
			crd.show(cPane, "b");
		} else if (e.getSource().equals(goback))
			crd.show(cPane, last);
		else if (e.getSource().equals(goCode))
			crd.show(cPane, "c");
		else if (e.getSource().equals(help2)) {
			last = "c";
			crd.show(cPane, "b");
		} else if (e.getSource().equals(home))
			crd.show(cPane, "a");
		else if (e.getSource().equals(newFile))
			createNewTab(null);
		else if (e.getSource().equals(refresh))
			try {
				controller.GetCurrTables();
			} catch (DBAppException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if (e.getSource().equals(save) && tabbedPane.getSelectedComponent() != null)
			saveToFile((JScrollPane) tabbedPane.getSelectedComponent());
		else if (e.getSource().equals(open)) {
			loadFromFile();
		} else if (e.getSource().equals(run) && tabbedPane.getSelectedComponent() != null) {
			JScrollPane js = (JScrollPane) tabbedPane.getSelectedComponent();
			JTextArea ta = (JTextArea) js.getViewport().getView();
			if (ta.getSelectedText() == null) {
				consoleJPanel.setForeground(Color.RED);
				consoleJPanel.setText("Please select a query to run");
				return;
			}

			controller.run(ta.getSelectedText());

		} else if (e.getSource().equals(closeTab) && tabbedPane.getSelectedComponent() != null) {
			int selectedIndex = tabbedPane.getSelectedIndex();
			if (selectedIndex != -1)
				tabbedPane.remove(selectedIndex);
		} else if (e.getSource().equals(modeButton) && tabbedPane.getSelectedComponent() != null) {
			JScrollPane js = (JScrollPane) tabbedPane.getSelectedComponent();
			JTextArea ta = (JTextArea) js.getViewport().getView();
			if (mode.equals("n")) {
				ta.setBackground(Color.WHITE);
				ta.setForeground(Color.blue);
				ta.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
				ta.setCaretColor(Color.BLACK);
				modeButton.setToolTipText("Tab night mode");
				modeButton.setIcon(scaledIconN);
				mode = "l";
			} else {
				ta.setBackground(new Color(41, 41, 41));
				ta.setForeground(new Color(235, 213, 246));
				ta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				ta.setCaretColor(Color.white);
				modeButton.setToolTipText("Tab light mode");
				modeButton.setIcon(scaledIconL);
				mode = "n";
			}
		}
	}
}
