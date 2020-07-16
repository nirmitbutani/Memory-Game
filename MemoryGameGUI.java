import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.*;
import javax.swing.Timer; 
import java.lang.Math;

public class MemoryGameGUI extends JFrame 
{
   private JPanel gamePane;
   private JPanel boardPane;
   private JButton restart;
   private JButton menu;
   private JLabel highscore;
   private JLabel time;
   private JLabel num_pairs;
   private JLabel update_bar;
   private boolean oneisselected = false;
   private ArrayList<JButton> squares = new ArrayList<JButton>();
   private Scorekeeper sc;
 
   public MemoryGameGUI(int rows, int cols)
   {
      int boxes = rows*cols;
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(200, 200, 500, 500);
      setName("Memory Game");
      int pairs_remain = boxes/2;

      sc = new Scorekeeper(boxes);
      
      gamePane = new JPanel();  
      setContentPane(gamePane);
      gamePane.setLayout(null);

      num_pairs = new JLabel("Pairs Left: " + pairs_remain);
      num_pairs.setBounds(350, 160, 100, 30);
      
      menu = new JButton("Main Menu");
      menu.setBounds(130, 50, 100, 30);
      
      restart = new JButton("Start");
      restart.setBounds(30, 50, 90, 30);

      update_bar = new JLabel();
      update_bar.setBounds(350, 250, 250, 30);

      highscore = new JLabel("Fastest Time: " + sc.getHighscore());
      highscore.setBounds(350, 120, 150, 30);

      time = new JLabel("Current Time: " + 0.0);
      time.setBounds(350, 200, 150, 30);

      gamePane.add(restart);
      gamePane.add(menu);
      gamePane.add(highscore);
      gamePane.add(num_pairs);
      gamePane.add(time);
      gamePane.add(update_bar);

      boardPane = new JPanel();
      boardPane.setLayout(new GridLayout(rows,cols,0,0));
      boardPane.setBounds(30, 120, 300, 300);
      boardPane.setBackground(Color.BLACK);
      
      gamePane.add(boardPane);
   }

   public void fillboxes(int boxes)
   {
      for(int i = 0; i < boxes; i++)
      {
         JButton b = new JButton("");
         squares.add(b);
         boardPane.add(squares.get(i));
      }
   }

   public void setTime(double ctime)
   {
      time.setText("Current Time: " + ctime);
   }

   public JButton getRestart()
   {
      return restart;
   }

   public JButton getMenu()
   {
      return menu;
   }

   public ArrayList<JButton> getSquares()
   {
      return squares;
   }

   public JLabel getNumpairs()
   {
      return num_pairs;
   }

   public JLabel getTimelabel()
   {
      return time;
   }

   public JLabel getHighscore()
   {
      return highscore;
   }

   public JLabel getUpdatebar()
   {
      return update_bar;
   }

   public JPanel getboardPane()
   {
      return boardPane;
   }

   public void setHighscore(double score)
   {
      highscore.setText("Fastest Time: " + score);
   }

   public Scorekeeper getScorekeeper()
   {
      return sc;
   }

   public String convertTime(double time)
   {
      String str = "";
      double minute = time/60;
      double second = time%minute;
      System.out.println(minute + ":" + second);
      return minute + ":" + second; 
   }
}      
