/*
 * Author: Effiea Ponniah
 * Last Modified: March 8th, 2021
 * This class create bank account which maintains 
 * customer balance with a specific account number. 
 */

import javax.swing.JOptionPane;

public class BankAccounts {
	
	private int accountNumber; 
	private double balance; 
	
	// Sets the bank account number and initial account balance 
	public BankAccounts(int accountNumber, double balance)
	{
		this.accountNumber = accountNumber; 
		this.balance = balance; 
	}
	
	// Get user account number
	public int getAccountNumber()
	{
		return accountNumber; 
	}
	
	// Get user balance
	public double getBalance()
	{
		return balance; 
	}

	// Updates the balance according to amount deposited
	public void deposit(double amount)
	{
		balance += amount;
	}

	// Updates the balance according to the amount withdrawal
	public boolean withdraw(double amount)
	{
		// Checks whether withdrawal amount exceeds the balance
		if(amount <= balance)
		{
			balance -= amount;
			return true; 
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Try a smaller amount", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
			return false; 
		}
		
	} 
}
