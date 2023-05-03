package FindMatch.view;

import FindMatch.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame implements ObserverInterface, ActionListener {
    private final JButton EasyBtn;
    private final JButton MediumBtn;
    private final JButton HardBtn;
    private final GameController controller;

    public GameView(GameController controller) {
        super("Find a Match");
        this.controller = controller;

        // Set up main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        switch (source.getText()) {
            case "Easy" -> controller.setGridSize(4);
            case "Medium" -> controller.setGridSize(16);
            case "Hard" -> controller.setGridSize(36);
        }
        setVisible(false);
    }

    @Override
    public void update() {
        showGrid();
    }

    public void showGrid() {
        setVisible(true);
    }

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

}
