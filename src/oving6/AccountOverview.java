package oving6;

import java.util.ArrayList;

public class AccountOverview {
	ArrayList<Account> accounts;
	public AccountOverview() {
		accounts = new ArrayList<Account>();
	}
	public int getAccountCount() {
		return accounts.size();
	}
	public Account getAccount(int index){
		if(index < getAccountCount()) return accounts.get(index);
		return null;
	}
	public void addAccount(Account newAccount) {
		accounts.add(newAccount);
	}
	public int getTotalBalance() {
		int totBalance = 0;
		for (Account account : accounts) {
			totBalance +=  account.getBalance();
		}
		return totBalance;
	}
	public int getTotalCredit() {
		int totCredit = 0;
		for (Account account : accounts) {
			totCredit +=  account.getCredit();
		}
		return totCredit;
	}
	public int getTotalFees() {
		int totFees = 0;
		for (Account account : accounts) {
			//if(account instanceof CreditAccount)
			totFees += account.getFees();
		}
		return totFees;
	}
}
