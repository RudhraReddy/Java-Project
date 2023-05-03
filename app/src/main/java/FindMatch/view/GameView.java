package FindMatch.view;

import FindMatch.controller.GameController;

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

    public GameView(GameController controller) {
        super("Find a Match");
        this.controller = controller;

        mainFrame = new JFrame("Find a Match");
        mainFrame.setPreferredSize(new Dimension(500,500));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	
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
    
    public void update() {
        showGrid();
    }

    public void showGrid() {
        setVisible(true);
    }
	public void addLevelButtonListener(ActionListener listener) {
    easyButton.addActionListener(listener);
    hardButton.addActionListener(listener);
    mediumButton.addActionListener(listener);
}
}
