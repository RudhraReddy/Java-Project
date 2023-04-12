import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameGUI extends JFrame {
   private JButton[] buttons;
   private JPanel mainPanel;

   private JLabel status;
   private JLabel score;

   public GameGUI() {
      JFrame mainFrame = new JFrame("Memory Matcher");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400, 400);

      mainPanel = new JPanel();
      status = new JLabel("");
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

      JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

      buttons = new JButton[16];
      for (int i=0;i<16;i++) {
         buttons[i] = new JButton(" ");
         buttonPanel.add(buttons[i]);
      }
      mainPanel.add(buttonPanel, BorderLayout.CENTER);
      
      
      score = new JLabel("SCORE: 5");
      mainPanel.add(score,BorderLayout.SOUTH);


      buttons[0].setText("4");
      buttons[0].setEnabled(false);

      buttons[14].setText("4");
      buttons[14].setEnabled(false);
      status.setText("STATUS: Match found!");
      mainPanel.add(status);
      
      mainFrame.add(mainPanel);
      mainFrame.pack();
      mainFrame.setVisible(true);

   }
   public static void main(String[] args) {
      GameGUI game = new GameGUI();
   }
}
