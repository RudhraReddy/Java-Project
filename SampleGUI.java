import java.awt.*;
import javax.swing.*;

public class SampleGUI
{
   public static void main(String[] args)
   {
      // JFrame is a top-level container, which can be visible on screen
      JFrame frame = new JFrame("Memory Matcher");
      // operation to do when the window is closed
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // It is convenient to put multiple GUI elements into a JPanel
      // and then add that panel to the top level container
      JLabel label = new JLabel("Select a level");
      JPanel mainPanel = new JPanel();
      label.setAlignmentX(Component.CENTER_ALIGNMENT);
      mainPanel.add(label);
      mainPanel.setPreferredSize(new Dimension(200,100));
      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));  

      JButton button1 = new JButton("Easy");
      JButton button2 = new JButton("Medium");
      JButton button3 = new JButton("Hard");
      button1.setAlignmentX(Component.CENTER_ALIGNMENT);
      button2.setAlignmentX(Component.CENTER_ALIGNMENT);
      button3.setAlignmentX(Component.CENTER_ALIGNMENT);
      mainPanel.add(button1);
      mainPanel.add(button2);
      mainPanel.add(button3);

      frame.add(mainPanel);

      // packs the components within the window based on their preferred size
      frame.pack();
      // make top-level container visible
      frame.setVisible(true);
   }

}