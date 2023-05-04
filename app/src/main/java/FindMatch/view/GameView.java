package FindMatch.view;

import FindMatch.controller.GameController;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JFrame implements ObserverInterface, ActionListener {
    public final JButton easyButton;
    public final JButton mediumButton;
    public final JButton hardButton;
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
        mainPanel.setBackground(Color.decode("#1d124e"));

        // Set up game name label
        JLabel gameNameLbl = new JLabel("FIND A MATCH");
        Font font = new Font("Serif", Font.BOLD, 24);
        gameNameLbl.setFont(font);
        gameNameLbl.setForeground(Color.WHITE);
        gameNameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(gameNameLbl, c);

        // Set up level label
        JLabel levelLbl = new JLabel("Select the game level!");
        levelLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font font1 = new Font("Serif", Font.BOLD, 18);
        levelLbl.setFont(font1);
        levelLbl.setForeground(Color.WHITE);
        mainPanel.add(levelLbl, c);

        // Set up easy button
        easyButton = createRoundButton("Easy", font1, "#f983f8", this);
        mainPanel.add(easyButton, c);

        // Set up medium button
        mediumButton = createRoundButton("Medium", font1, "#8ffdf4", this);
        mainPanel.add(mediumButton, c);

        // Set up hard button
        hardButton = createRoundButton("Hard", font1, "#fefebb", this);
        mainPanel.add(hardButton, c);

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
        easyButton.addActionListener(listener);
        hardButton.addActionListener(listener);
        mediumButton.addActionListener(listener);
    }

    public JButton createRoundButton(String text, Font font, String hexColor, ActionListener listener) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(listener);
        button.setFocusPainted(false);
        button.setFont(font);
        button.setPreferredSize(new Dimension(100, 40));

        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(Color.decode(hexColor));
        button.setForeground(Color.BLACK);

        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseEntered(MouseEvent e) {
                button.setUI(new BasicButtonUI() {
                    @Override
                    public void paint(Graphics g, JComponent c) {
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        AbstractButton button = (AbstractButton) c;
                        ButtonModel model = button.getModel();
                        if (model.isArmed()) {
                            g2d.setColor(button.getBackground().darker());
                        } else {
                            g2d.setColor(button.getBackground());
                        }
                        g2d.fillRoundRect(0, 0, button.getWidth(), button.getHeight(), button.getHeight(),
                                button.getHeight());
                        g2d.dispose();
                        super.paint(g, c);
                    }
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorderPainted(false);
                button.setUI(new RoundedButtonUI());
            }
        });
        button.setUI(new RoundedButtonUI());
        return button;
    }

    class RoundedButtonUI extends BasicButtonUI {
        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            AbstractButton button = (AbstractButton) c;
            button.setOpaque(false);
            button.setBorderPainted(false);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
            super.paint(g, c);
        }

        private void paintBackground(Graphics g, JComponent c, int yOffset) {
            Dimension size = c.getSize();
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground().darker());
            g2.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
            g2.dispose();
        }
    }

}
