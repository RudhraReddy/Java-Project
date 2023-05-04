package FindMatch.model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class MatchValidator extends JFrame implements ActionListener {
   public JButton[] buttons;
   private JPanel mainPanel;
   private JFrame mainFrame;
   private JPanel buttonPanel;
   private JButton startButton;
   private JButton flipButton;

   private int[] values;
   private int[] found;
   private int selections;
   private int prevIndex;
   public JLabel status;
   private JLabel score;
   private int gridSize;
   private double gridDimension;
   public int score_val = 0;
   private String scoreFilePath = "scores.txt";
   private String line ="";
   private JLabel highscorelabel;


   public MatchValidator() {
   }

   public MatchValidator(int gridSize) {
      this.gridSize = gridSize;
      this.gridDimension = Math.sqrt(gridSize);

      mainFrame = new JFrame("Find a Match");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setPreferredSize(new Dimension(700, 700));

      mainPanel = new JPanel();
      mainPanel.setLayout(null);

      buttonPanel = new JPanel(new GridLayout((int) gridDimension, (int) gridDimension));
      buttonPanel.setPreferredSize(new Dimension(500, 500));
      buttonPanel.setMaximumSize(new Dimension(500, 500));
      buttonPanel.setBounds(125, 100, 400, 400);
      buttonPanel.setEnabled(false);

      buttons = new JButton[gridSize];
      values = new int[gridSize];
      found = new int[gridSize];

      startButton = new JButton("Start Game");
      startButton.setBounds(290, 30, 100, 40);
      startButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            showButtonsFor2Seconds();
            mainPanel.add(buttonPanel);
            flipButton.setEnabled(true);
         }
      });

      flipButton = new JButton("Flip for Help");
      flipButton.setBounds(560, 490, 120, 40);
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
      for (int i = 0; i < gridSize / 2; i++) {
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
      status.setBounds(240, 360, 700, 500);
      status.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
      mainPanel.add(status);

      score = new JLabel("SCORE: 0");
      score.setBounds(300, 480, 100, 100);
      score.setFont(new Font("Arial", Font.BOLD, 20));
      mainPanel.add(score);
	  
	  highscorelabel = new JLabel("");
	  highscorelabel.setFont(new Font("Arial", Font.BOLD, 20));
	  highscorelabel.setBounds(270, 480, 170, 170);
     mainPanel.add(highscorelabel);
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
      } else {
         if (values[index] == values[prevIndex]) {
            found[index] = 1;
            found[prevIndex] = 1;
            selections = 0;
            status.setText("Match found! \uD83D\uDC4D");
            score_val = score_val + 1;
            score.setText("Score: " + score_val);
         } else {
            buttons[prevIndex].setEnabled(true);
            buttons[index].setEnabled(true);
            buttons[prevIndex].setText(" ");
            buttons[index].setText(" ");
            selections = 0;
            status.setText("Incorrect Match \uD83D\uDC4E");
            score_val = score_val - 1;
            score.setText("Score: " + score_val);
         }
      }
     
      if (allFound()) {
         status.setText("Congratulations! You Won.");
         try {
			 int level=0;
			 int highScore=0;
          FileReader fileReader = new FileReader("score.txt");
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          String line;
          StringBuilder stringBuilder = new StringBuilder();
          while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
         }
         bufferedReader.close();
         String fileContents = stringBuilder.toString();
         String[] lines = fileContents.split("\n");
         for (String l : lines) {
            int colonIndex = l.indexOf(":");
            String value = l.substring(colonIndex + 1).trim();
            if(gridSize ==4 && l.contains("Easy")){
               level=0;
               highScore = Integer.parseInt(value);
            }
            else if(gridSize ==16 && l.contains("Medium"))
            {
               level=1;
               highScore = Integer.parseInt(value);
            }
            else if(gridSize ==36 && l.contains("Medium"))
            {
               level=2;
               highScore = Integer.parseInt(value);
            }
         }
         if (score_val > highScore) {
            highscorelabel.setText("High Score: " + score_val);
            String updated_score = lines[level].
                                   substring(0, lines[level].length() - 1) + score_val;
            lines[level] = updated_score;
            // Write the new score_val to the file
            FileWriter writer = new FileWriter("score.txt", false);
            for(int i = 0; i < lines.length; i++) {
               writer.write(String.valueOf(lines[0]));
               writer.write(System.lineSeparator()); // add newline
            }
            writer.close();
        }
        else
			highscorelabel.setText("High Score: " + highScore);	    
	}
   catch (IOException ex) {
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
