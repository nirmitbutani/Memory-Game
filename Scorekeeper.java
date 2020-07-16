import java.util.Scanner;
import java.io.*;

public class Scorekeeper
{
	private double high_score;
	private String filename;

	public Scorekeeper(int boxes)
	{
		switch(boxes)
		{
			case 16:
				filename = "easyHS.txt";
				break;
			case 24:
				filename = "medHS.txt";
				break;
			case 36:
				filename = "hardHS.txt";
				break;
		}
		try
		{
			Scanner scanner = new Scanner(new File(filename));
			if(scanner.hasNext())
			{
				high_score = scanner.nextDouble();
			}
			else
			{
				high_score = 0.0;
			}
		}	
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
	}

	public Scorekeeper(String filename)
	{
		try
		{
			Scanner scanner = new Scanner(new File(filename));
			if(scanner.hasNext())
			{
				high_score = scanner.nextDouble();
			}
			else
			{
				high_score = 0.0;
			}
		}	
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
	}

	public double getHighscore()
	{
		return high_score;
	}

	public void setHighscore(double score)
	{
		this.high_score = score;
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write(""+ score);
			bw.close();
		}
		catch(IOException ex)
		{
			System.out.println("There is an error");
		}
	}

}
