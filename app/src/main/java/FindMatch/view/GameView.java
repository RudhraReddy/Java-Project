package FindMatch.view;


import FindMatch.controller.GameController;
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

    public GameView(GameController controller) {
		this.controller=controller;
        mainFrame = new JFrame("Find a Match");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setPreferredSize(new Dimension(500, 500));

        //ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/gameImage.jpg"));
        //System.out.println(getClass().getResource("/gameImage.jpg"));
        //mainFrame.setContentPane(new JLabel(backgroundImage));

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;

        gameNameLbl = new JLabel("FIND A MATCH");
        Font font = new Font("Serif", Font.BOLD, 24);
        gameNameLbl.setFont(font);
        gameNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(gameNameLbl, c);

        levelLbl = new JLabel("Select the game level!");
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
        mainFrame.setVisible(true);
    }
	public void addLevelButtonListener(ActionListener listener) {
    easyButton.addActionListener(listener);
    hardButton.addActionListener(listener);
    mediumButton.addActionListener(listener);
}
}
