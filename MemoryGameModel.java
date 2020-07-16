import java.util.ArrayList;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer; 
import java.awt.*;
import java.awt.event.*;


public class MemoryGameModel
{
	public MemoryGameGUI g;
	public ArrayList<String> symbols = new ArrayList<String>();
	public ArrayList<JButton> selected = new ArrayList<JButton>();
	public Map<JButton, String> pairs = new HashMap();
	public double current_time = 0.0;
	public double fastest_time;
	public Timer SimpleTimer;
	public int boxes;
	public int pairs_remain;
	public boolean oneisselected = false;
	private Scorekeeper sc;

	public MemoryGameModel(int rows, int cols)
	{
		boxes = rows*cols;
		g = new MemoryGameGUI(rows, cols);
		pairs_remain = boxes/2;
		g.setVisible(true);
		sc = g.getScorekeeper();
		fastest_time = sc.getHighscore();
		g.getMenu().addActionListener(new ActionListener()
      	{
        	public void actionPerformed(ActionEvent e)
        	{
            new MainMenuModel();
            g.setVisible(false);
        	}
      	});

      SimpleTimer = new Timer(100, new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e) 
         {
            current_time += .1;
            current_time = Math.round(current_time*10)/10.0;
            g.getTimelabel().setText("Current Time: " + current_time);
            if(pairs_remain == 0)
            {
            	g.getUpdatebar().setText("Congratulations!! You Win!!");
            	((Timer)e.getSource()).stop();
            	compareTime(current_time, fastest_time);
            }
         } 
      });

      	g.getRestart().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.getRestart().setText("Restart");
				g.getSquares().clear();
				symbols.clear();
				g.getboardPane().removeAll();
				pairs.clear();
				g.getUpdatebar().setText("");
				g.fillboxes(boxes);
				fillboxes(g.getSquares());
				pairs_remain = boxes/2;
				g.getNumpairs().setText("Pairs Left: " + pairs_remain);
				Thread t2 = new Thread(new Runnable(){
				public void run()
				{
					SimpleTimer.stop();
					start();
					SimpleTimer.start();
				}
			});
			t2.start();
			}
		});
	}

	public void setSymbols(int boxes)
	{
		for(int i = 0; i < 2; i++)
		{
			int j = 0;
			for(char alphabet = 'A'; alphabet <= 'Z'; alphabet++)
			{
				symbols.add(alphabet + "");
				j++;
				if(j == boxes/2)
				{
					break;
				}
			}
		}
	}

	public void compareTime(double current_time, double fastest_time)
	{
      if(current_time <= fastest_time || fastest_time == 0.0)
      {
      	this.fastest_time = current_time;
        g.setHighscore(this.fastest_time); 
        sc.setHighscore(this.fastest_time);
        JOptionPane.showMessageDialog(null, "New Record Alert!!");
      }
   }

	public JButton getObj(int index)
	{
		return selected.get(index);
	}

	public void clearArrayList(ArrayList arr)
	{
		arr.clear();
	}

	public void addButton(JButton b)
	{
		selected.add(b);
	}

	public ArrayList getSelected()
	{
		return selected;
	}

	public String findString(JButton b)
	{
		return pairs.get(b);
	}

	public void clearAll()
	{
		symbols.clear();
		selected.clear();
		pairs.clear();
	}

	public boolean verify(JButton b)
	{
		if(selected.get(0).getText().equals(pairs.get(b)))
		{
			selected.get(0).setEnabled(false);
			b.setEnabled(false);
			return true;
		}
		else
		{
			return false;
		}
	}

	public void updateGUI(boolean a)
	{
      if(a == true)
      {
         g.getUpdatebar().setText("Correct!!");
         g.getUpdatebar().setForeground(Color.GREEN);
         g.getNumpairs().setText("Pairs Left: " + --pairs_remain);
      }
      else
      {
         g.getUpdatebar().setText("Incorrect :(");
         g.getUpdatebar().setForeground(Color.RED);
      }
   }

	public void fillboxes(ArrayList<JButton> buttons)
	{
		setSymbols(boxes);
		Collections.shuffle(symbols);
		int i = 0;
		for(JButton b:buttons)
		{
			pairs.put(b, symbols.get(i));
			b.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(oneisselected)
					{
						if(selected.get(0).equals(b))
						{
							g.getUpdatebar().setText("Please select two different buttons");
							g.getUpdatebar().setForeground(Color.RED);
							return; 
						}
						g.setEnabled(false);
						b.setText(pairs.get(b));
						Thread t1 = new Thread(new Runnable(){ 
							public void run()
							{
								if(selected.get(0).getText().equals(pairs.get(b)))
								{
									pause(500);
									g.getNumpairs().setText("Pairs left: " + --pairs_remain); //reduces the number of pairs remaining in the GUI beforehand
									g.getUpdatebar().setText("Correct!!");
									g.getUpdatebar().setForeground(Color.GREEN);
									b.setEnabled(false);
									selected.get(0).setEnabled(false);
								}
								else
								{
									pause(1000);
									g.getUpdatebar().setText("Incorrect :(");
									g.getUpdatebar().setForeground(Color.RED);
									b.setText("");
									selected.get(0).setText("");
									selected.get(0).setEnabled(true);
								}
								oneisselected = false;
								selected.clear();
								g.setEnabled(true);
							}
						});
						t1.start(); 
					}
					else
					{
						b.setText(pairs.get(b));
						oneisselected = true;
						selected.clear();
						selected.add(b);
					}
				}
			});
			i++;
		}
	}

	public void pause(long millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch(InterruptedException e)
		{}
	}

	public void start()
	{
		g.setEnabled(false);
		for(JButton b : g.getSquares())
		{
			b.setText(pairs.get(b));
		}
		pause(3000);
		for(JButton b : g.getSquares())
		{
			b.setText("");
		}
		g.setEnabled(true);
		current_time = 0.0;
		g.getTimelabel().setText("Current Time: " + current_time);
	}

	public MemoryGameGUI getGUI()
	{
		return g; 
	}

	public String convertTime(double time) 
	{
		String str = "";
		int minute = 83/60;
		double second = time%minute;
		return minute + ":" + second; 
	}

}
