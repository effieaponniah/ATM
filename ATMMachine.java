/*
 * Author: Effiea 
 * Last Modified: March 8th, 2021
 * This is the main program which simulates an ATM Machine 
 * The system reads the customer bank account information 
 * and prompts the customer to press the start button to 
 * begin the transaction until a bank official enters the valid password 
 * to terminate the ATM program.
 * Produces error message for invalid input 
 * 1. If user enters invalid program
 */

import javax.swing.*;

public class ATMMachine {
	
	public static void main(String[] args) {
		
		ImageIcon rbcLogo = new ImageIcon("rbc.png");
		
		CustomerAccounts customer = new CustomerAccounts(); 
		
		final int UNSUCCESSFUL_ATTEMPTS = 3; 
		
		String[] startOption = {"Start"}; 

		int start = 0; 		
		boolean end = false;
		int userAccountNumberInput;
		int arrayLocation;
		
		// If the customer account file is found, continue with transactions
		if(customer.customerAccountFileFound())
		{
			do
			{
				start = JOptionPane.showOptionDialog(null, "Please start to begin", "Welcome to RBC", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, rbcLogo, startOption, startOption[0]);
			
				if(start == 0)
				{
					// Prompt user for valid account number (3 attempts)
					for(int i = 0; i < UNSUCCESSFUL_ATTEMPTS; i++)
					{	
						userAccountNumberInput = ReadLibrary.readInt("Enter your numeric account number");
						arrayLocation = customer.findUserAccountArrayLocation(userAccountNumberInput);
						if(arrayLocation != -1)
						{
							customer.customerTransactions(arrayLocation);
							break; 
						}
					}
				}
				else
				{
					end = secureExit(); 
				}
			}
			while (start != -1 || !end);
		}
	}
	
	// If the user account is not found the ATM machine will repeatedly ask for customer account number
	//	until the admin clicks on the exit [x] button and enters the secret code to terminate the program
	public static boolean secureExit() 
	{
		String password; 
		password = ReadLibrary.readString("Enter password"); 
		if(password.equals("09876"))
		{
			JOptionPane.showMessageDialog(null, "ATM successfully terminated", "Message", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Returning to the start menu", "Wrong Password", JOptionPane.ERROR_MESSAGE);
			return false; 
		}
	}
}