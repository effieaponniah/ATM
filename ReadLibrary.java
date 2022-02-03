import javax.swing.*; 

public class ReadLibrary {
	
	public static String readString(String prompt)
	{
		String userInput;
		userInput = JOptionPane.showInputDialog(prompt);
		
		while(userInput == null || userInput.equals(""))
		{
			JOptionPane.showMessageDialog(null, "You didn't enter the input", "Error", JOptionPane.INFORMATION_MESSAGE);
			userInput = JOptionPane.showInputDialog(prompt);
		}
		return userInput; 
	}
	public static int readInt(String prompt)
	{
		int input = 0; 
		boolean exception = true; 
		
		while(exception)
		{
			//readString(prompt);
			try 
			{
				input = Integer.parseInt(readString(prompt));
				exception = false;
			}
			catch(NumberFormatException nf)
			{
				JOptionPane.showMessageDialog(null, "Invalid");
			}

		}
		return input; 
	}
	public static double readDouble(String prompt)
	{
		double input = 0; 
		boolean exception = true; 
		
		while(exception)
		{
			try 
			{
				input = Double.parseDouble(readString(prompt));
				exception = false;
			}
			catch(NumberFormatException nf)
			{
				JOptionPane.showMessageDialog(null, "Invalid");
			}
		}
		return input; 
	}
}
