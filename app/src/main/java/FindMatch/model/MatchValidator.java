package FindMatch.model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class MatchValidator extends JFrame implements ActionListener {
   private JButton[] buttons;
   private JPanel mainPanel;
   private JFrame mainFrame;
   private JPanel buttonPanel;
   private JButton startButton;
   private JButton flipButton;


   private int[] values;
   private int[] found;
   private int selections;
   private int prevIndex;
   private JLabel status;
   private JLabel score;
   private int gridSize;
   private double gridDimension;
   private int score_val = 0;
   private String scoreFilePath = "scores.txt";

   public MatchValidator() {}

   public MatchValidator(int gridSize) {
      this.gridSize = gridSize;
      this.gridDimension = Math.sqrt(gridSize);

      mainFrame = new JFrame("Find a Match");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setPreferredSize(new Dimension(700, 600));
      //mainFrame.setLayout(null);

      mainPanel = new JPanel();
      mainPanel.setLayout(null);

      buttonPanel = new JPanel(new GridLayout((int)gridDimension, (int)gridDimension));
      buttonPanel.setPreferredSize(new Dimension(500, 500));
      buttonPanel.setMaximumSize(new Dimension(500, 500));
      buttonPanel.setBounds(125,100,400,400);
      buttonPanel.setEnabled(false);

      buttons = new JButton[gridSize];
      values = new int[gridSize];
      found = new int[gridSize];

      startButton = new JButton("Start Game");
      startButton.setBounds(290,30,100,40);
      startButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showButtonsFor2Seconds();
            mainPanel.add(buttonPanel);
            flipButton.setEnabled(true);
         }
      });

      flipButton = new JButton("Flip for Help");
      flipButton.setBounds(530,490,120,40);
      flipButton.setEnabled(false);
      flipButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      flipButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showButtonsFor2Seconds();
            mainPanel.add(buttonPanel, BorderLayout.CENTER);
            flipButton.setEnabled(false);
         }
      });

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
      mainPanel.add(startButton);
      mainPanel.add(flipButton);
      mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
      

      status = new JLabel("ALL THE BEST!");
      status.setBounds(270,460,150,100);
      mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
      mainPanel.add(status);

      score = new JLabel("SCORE: 0");
      score.setBounds(300,480,100,100);
      mainPanel.add(score);
      mainFrame.add(mainPanel, BorderLayout.CENTER);

      mainFrame.pack();
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setVisible(true);
   }

   private void showButtonsFor2Seconds() {
      // Disable the start button
      startButton.setEnabled(false);
      for (int i = 0; i < gridSize; i++) {
         buttons[i].setText("" + values[i]);
         buttons[i].setFont(new Font("Bold", Font.PLAIN, 24));
         buttons[i].setEnabled(false);
         mainPanel.add(buttonPanel, BorderLayout.CENTER);
      }
      javax.swing.Timer timer = new javax.swing.Timer(2000, new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            for (int i = 0; i < gridSize; i++) {
               if (found[i] == 0) {
                  buttons[i].setText(" ");
                  buttons[i].setFont(new Font("Bold", Font.PLAIN, 24));
                  buttons[i].setEnabled(true);
               }
            }
         }
      });
      timer.setRepeats(false);
      timer.start();
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
            status.setText("STATUS: Match found! \uD83D\uDC4D");
            score_val = score_val+1;
            score.setText("Score: "+score_val);
         }
         else {
            buttons[prevIndex].setEnabled(true);
            buttons[index].setEnabled(true);
            buttons[prevIndex].setText(" ");
            buttons[index].setText(" ");
            selections = 0;
            status.setText("STATUS: Incorrect Match \uD83D\uDC4E");
            score_val = score_val-1;
            score.setText("Score: "+score_val);
         }
      }

      if (allFound()) {
         status.setText("STATUS: Congratulations! You found all matches.");
         try {
            FileWriter writer = new FileWriter("score.txt", true);
            writer.write(score_val + "\n");
            writer.close();
            status.setText("Congratulations.");
         } catch (IOException ex) {
            ex.printStackTrace();
         }
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
