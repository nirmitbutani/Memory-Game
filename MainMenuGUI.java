import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MainMenuGUI extends JFrame
{
	private JPanel menu;
	private JButton easy, med, hard, howto;
	private JLabel title;
	private JOptionPane message;

	public MainMenuGUI()
	{
		menu = new JPanel(); 
		title = new JLabel("Memory Game!");
		setContentPane(menu);
		setTitle("Memory Game");
		title.setBounds(170, 50, 450, 200);
		title.setFont(new Font("Times New Roman", Font.BOLD, 26));
		title.setBackground(Color.BLACK);

		easy = new JButton("Easy");
		easy.setBounds(175, 200, 150, 30);

		med = new JButton("Medium");
		med.setBounds(175, 250, 150, 30);

		hard = new JButton("Hard");
		hard.setBounds(175, 300, 150, 30);

		howto = new JButton("How to play");
		howto.setBounds(175, 350, 150, 30);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setVisible(true);
		setLayout(null);

		menu.setLayout(null);
		menu.setBackground(Color.WHITE);
		menu.add(easy);
		menu.add(med);
		menu.add(hard);
		menu.add(howto);
		menu.add(title);
	}

	public JButton getEasy()
	{
		return easy;
	}

	public JButton getMed()
	{
		return med;
	}

	public JButton getHard()
	{
		return hard;
	}

	public JButton getHowTo()
	{
		return howto;
	}

	public void getInstructions(String instructions)
	{
		message.showMessageDialog(null, instructions, "How to Play", JOptionPane.INFORMATION_MESSAGE);
	}
}
