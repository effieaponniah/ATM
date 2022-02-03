/*
 * Author: Effiea 
 * Last Modified: March 8th, 2021
 * This class will read customer bank account data from a 
 * space delimited text file and store in BankAccount type array. 
 * This class will maintain customer transactions and 
 * update customer bank account balance after a deposit or valid 
 * withdrawal
 */

import java.io.*; 
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CustomerAccounts {
	
	private BankAccounts[] accounts; 
	private boolean fileFound = false;

	// Read customer bank account data
	// and store in a BankAccount type array. 
	public CustomerAccounts()
	{
		StringTokenizer st; 
		String line; 

		try {
			BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
			accounts = new BankAccounts[Integer.parseInt(br.readLine())];
			for(int i = 0; i < accounts.length; i++)
			{
				line = br.readLine(); 
				st = new StringTokenizer(line); 
				
				accounts[i] = new BankAccounts(Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()));
			}
			fileFound = true; 
			br.close();
		}
		catch(IOException io)
		{
			JOptionPane.showMessageDialog(null, "Customer Account Data File Not Found", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public boolean customerAccountFileFound()
	{
		return fileFound; 
	}
	
	// Get an array location of customer with a valid account number input
	public int findUserAccountArrayLocation(int accountNumber)
	{
		for(int arrayAccountLocation = 0; arrayAccountLocation < accounts.length; arrayAccountLocation++)
		{
			if(accountNumber == accounts[arrayAccountLocation].getAccountNumber())
			{
				return arrayAccountLocation; 
			}
		}
		return -1; 
	}
	
	// Gives customer options to deposit, withdrawal and inquire balance of a customer account
	// or given an option to exit
	public void customerTransactions(int arrayAccountLocation2)
	{
		ImageIcon moneyImage =  new ImageIcon("money.png");
		String[] transactionOption = {"Deposit", "Withdraw", "Check Balance", "Exit"}; 		
		int userTransaction = 0; 
		double amount; 
		do 
		{
			userTransaction = JOptionPane.showOptionDialog(null, "Please select your transaction", "Transactions", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, moneyImage, transactionOption, transactionOption[0]);
			
			// Transaction based on customer selection
			switch(userTransaction)
			{
				case 0: // Deposits
					amount = ReadLibrary.readDouble("Enter the amount");
					accounts[arrayAccountLocation2].deposit(amount); 
					updateAccountsFile(); 
					break;
				case 1: // Withdrawals
					amount = ReadLibrary.readDouble("Enter the amount");
					
					// Only make changes to file if withdrawal is valid
					if (accounts[arrayAccountLocation2].withdraw(amount))
					{
						updateAccountsFile(); 
					}
					break;
				case 2: // Check balance
					NumberFormat money = NumberFormat.getCurrencyInstance(Locale.CANADA);
					double balanceAmount = accounts[arrayAccountLocation2].getBalance();
					JOptionPane.showMessageDialog(null, "Your balance is " + money.format(balanceAmount), "Message", JOptionPane.INFORMATION_MESSAGE);
					break;
			}
		}
		while(userTransaction != 3); // If customer chooses exit, program will exit while loop
	}
	
	// Update customer bank account balance after a transaction
	public void updateAccountsFile()
	{
		String updatedAccount = (accounts.length + "\n"); 
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("accounts.txt")); 
			for(int i = 0; i < accounts.length; i++)
			{
				updatedAccount += (accounts[i].getAccountNumber() + " " + accounts[i].getBalance() + "\n");
			}
			pw.print(updatedAccount);
			pw.close();
		}
		catch(IOException io)
		{
			JOptionPane.showMessageDialog(null, "Customer Account Data File Not Found", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}