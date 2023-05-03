package FindMatch.view;

import FindMatch.controller.GameController;
<<<<<<< HEAD
import FindMatch.view.GameImage;

import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;

public class GameView extends JFrame implements ActionListener,ObserverInterface{
    public JButton easyButton;
    public JButton mediumButton;
    public JButton hardButton;
    private JLabel gameNameLbl;
    private JLabel imageLabel;
    private JLabel levelLbl;
    private JPanel mainPanel;
    private JFrame mainFrame;
	private GameController controller;
    private JPanel contentPane;
=======

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame implements ObserverInterface, ActionListener {
    private final JButton EasyBtn;
    private final JButton MediumBtn;
    private final JButton HardBtn;
    private final GameController controller;
>>>>>>> a55c4a1cd76be3d1637c3a90c670b3d6d1a727c1

    public GameView(GameController controller) {
        super("Find a Match");
        this.controller = controller;

<<<<<<< HEAD
        //ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/gameImage.jpg"));
        //System.out.println(getClass().getResource("/gameImage.jpg"));
        //mainFrame.setContentPane(new JLabel(backgroundImage));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
=======
        // Set up main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
>>>>>>> a55c4a1cd76be3d1637c3a90c670b3d6d1a727c1
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        // Set up game name label
        JLabel gameNameLbl = new JLabel("FIND A MATCH");
        Font font = new Font("Serif", Font.BOLD, 24);
        gameNameLbl.setFont(font);
        gameNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(gameNameLbl, c);

        // Set up level label
        JLabel levelLbl = new JLabel("Select the game level!");
        levelLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font font1 = new Font("Serif", Font.BOLD, 18);
        levelLbl.setFont(font1);
        mainPanel.add(levelLbl, c);

<<<<<<< HEAD
        easyButton = new JButton("Easy");
        easyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		easyButton.addActionListener(this);
        hardButton = new JButton("Hard");
		hardButton.addActionListener(this);

        hardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumButton = new JButton("Medium");
		mediumButton.addActionListener(this);

        mediumButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(easyButton, c);
        mainPanel.add(mediumButton, c);
        mainPanel.add(hardButton, c);
        //mainPanel.add(imageLabel);

        //mainFrame.setContentPane(mainPanel);
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setPreferredSize(mainFrame.getSize());
        mainFrame.setVisible(true);
=======
        // Set up easy button
        EasyBtn = createRectangularButton("Easy", font1, this);
        mainPanel.add(EasyBtn, c);

        // Set up medium button
        MediumBtn = createRectangularButton("Medium", font1, this);
        mainPanel.add(MediumBtn, c);

        // Set up hard button
        HardBtn = createRectangularButton("Hard", font1, this);
        mainPanel.add(HardBtn, c);

        // Set up main frame
        setContentPane(mainPanel);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
>>>>>>> a55c4a1cd76be3d1637c3a90c670b3d6d1a727c1
    }

    @Override
    public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
	
         JButton source = (JButton) e.getSource();
         if (source.getText().equals("Easy")) {
             controller.setGridSize(4);
         } else if (source.getText().equals("Medium")) {
             controller.setGridSize(16);
         } else if (source.getText().equals("Hard")) {
             controller.setGridSize(36);
         }
	    mainFrame.setVisible(false);	
	}    
    
=======
        JButton source = (JButton) e.getSource();
        switch (source.getText()) {
            case "Easy" -> controller.setGridSize(4);
            case "Medium" -> controller.setGridSize(16);
            case "Hard" -> controller.setGridSize(36);
        }
        setVisible(false);
    }

    @Override
>>>>>>> a55c4a1cd76be3d1637c3a90c670b3d6d1a727c1
    public void update() {
        showGrid();
    }

    public void showGrid() {
        setVisible(true);
    }
<<<<<<< HEAD
	public void addLevelButtonListener(ActionListener listener) {
    easyButton.addActionListener(listener);
    hardButton.addActionListener(listener);
    mediumButton.addActionListener(listener);
}
=======

    public void addLevelButtonListener(ActionListener listener) {
        EasyBtn.addActionListener(listener);
        HardBtn.addActionListener(listener);
        MediumBtn.addActionListener(listener);
    }

    private JButton createRectangularButton(String text, Font font, ActionListener listener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(listener);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setFont(font);
        return button;
    }

>>>>>>> a55c4a1cd76be3d1637c3a90c670b3d6d1a727c1
}
