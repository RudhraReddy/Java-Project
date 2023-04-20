package FindMatch.view;


import FindMatch.controller.GameController;


import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameView extends JFrame implements ActionListener,ObserverInterface{
    private JButton easyBtn;
    private JButton mediumBtn;
    private JButton hardBtn;
    private JLabel gameNameLbl;
    private JLabel levelLbl;
    private JPanel mainPanel;
    private JFrame mainFrame;
	private GameController controller;

    public GameView(GameController controller) {
		this.controller=controller;
        mainFrame = new JFrame("Level Selector");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);

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

        easyBtn = new JButton("Easy");
        easyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		easyBtn.addActionListener(this);
        hardBtn = new JButton("Hard");
		hardBtn.addActionListener(this);

        hardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumBtn = new JButton("Medium");
		mediumBtn.addActionListener(this);

        mediumBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(easyBtn, c);
        mainPanel.add(mediumBtn, c);
        mainPanel.add(hardBtn, c);

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
    easyBtn.addActionListener(listener);
        hardBtn.addActionListener(listener);
        mediumBtn.addActionListener(listener);
}
}
