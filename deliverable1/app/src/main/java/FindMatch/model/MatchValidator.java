package FindMatch.model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MatchValidator extends JFrame implements ActionListener {
   private JButton[] buttons;
   private JPanel mainPanel;
   private JFrame mainFrame;
   private JPanel buttonPanel;

   private int[] values;
   private int[] found;
   private int selections;
   private int prevIndex;
   private JLabel status;
   private JLabel score;
   private int gridSize;
   private double gridDimension;
   private int score_val=0;
   public MatchValidator()
   {
   }

   public MatchValidator(int gridSize) {
    this.gridSize=gridSize;
    this.gridDimension=Math.sqrt(gridSize);

    mainFrame = new JFrame("Memory Matcher");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(400, 400);
    mainFrame.setLayout(new BorderLayout());

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    buttonPanel = new JPanel(new GridLayout((int)gridDimension, (int)gridDimension));
    buttonPanel.setPreferredSize(new Dimension(300, 300));
    buttonPanel.setMaximumSize(new Dimension(300, 300));

    buttons = new JButton[gridSize];
    values = new int[gridSize];
    found = new int[gridSize];

    // Generate random numbers for values array
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < gridSize/2; i++) {
        list.add(i);
        list.add(i);
    }
    Collections.shuffle(list);
    for (int i = 0; i < gridSize; i++) {
        buttons[i] = new JButton(" ");
        buttons[i].addActionListener(this);
        buttonPanel.add(buttons[i]);
        values[i] = list.get(i);

        found[i] = 0;
    }

    mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    mainPanel.add(buttonPanel, BorderLayout.CENTER);

    status = new JLabel("STATUS: ALL THE BEST!");
    status.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    mainPanel.add(status);

    score = new JLabel("SCORE: 0");
    score.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainPanel.add(score);
    mainFrame.add(mainPanel, BorderLayout.CENTER);

    // Create a timer to show the initial panel buttons for 2 seconds

	// Show buttons and disable them for 2 seconds
    for (int i = 0; i < gridSize; i++) {
        buttons[i].setText("" + values[i]);
        buttons[i].setEnabled(false);
    }
    javax.swing.Timer timer = new javax.swing.Timer(2000, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            for (int i = 0; i < gridSize; i++) {
                if (found[i] == 0) {
                    buttons[i].setText(" ");
                    buttons[i].setEnabled(true);
                }
            }
        }
    });
    timer.setRepeats(false);
    timer.start();

    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
}


   public void actionPerformed(ActionEvent e) {
      int index = -1;
      for (int i = 0; i < gridSize; i++) {
         if (e.getSource() == buttons[i]) {
            index = i;
            break;
         }
      }

      if (found[index] == 1) {
         status.setText("Already found!");
         return;
      }

      buttons[index].setText("" + values[index]);
      buttons[index].setEnabled(false);

      if (selections == 0) {
         prevIndex = index;
         selections++;
      }
      else {
         if (values[index] == values[prevIndex]) {
            found[index] = 1;
            found[prevIndex] = 1;
            selections = 0;
            status.setText("STATUS: Match found!");
            score_val = score_val+1;
            score.setText("Score: "+score_val);
         }
         else {
            buttons[prevIndex].setEnabled(true);
            buttons[index].setEnabled(true);
            buttons[prevIndex].setText(" ");
            buttons[index].setText(" ");
            selections = 0;
            status.setText("STATUS: No match found.");
            score_val = score_val-1;
            score.setText("Score: "+score_val);
         }
      }

      if (allFound()) {
         status.setText("STATUS: Congratulations! You found all matches.");
      }
   }

   public boolean allFound() {
      for (int i = 0; i < gridSize; i++) {
         if (found[i] == 0) {
            return false;
         }
      }
      return true;
   }
}