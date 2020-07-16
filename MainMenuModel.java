import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MainMenuModel
{
	private MainMenuGUI g;
	private String instructions;

	public MainMenuModel() 
	{
		instructions = "1. Each difficulty will provide a different grid (easy:4x4, medium:4x6, hard:6x6).\n2. Once you start the game you will be able to see the grid uncovered for 3 seconds (use them wisely).\n3. After the three seconds the letters will hide and the timer will start.\n4. Try to match every letter with its pair as fast as you can.\n5. Have fun!!";
		g = new MainMenuGUI();
		g.getEasy().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new MemoryGameModel(4,4);
				g.setVisible(false);
			}
		});
		g.getMed().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new MemoryGameModel(4,6);
				g.setVisible(false);
			}
		});
		g.getHard().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new MemoryGameModel(6,6);
				g.setVisible(false);
			}
		});

		g.getHowTo().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				g.getInstructions(instructions);
			}
		});
	}

}
