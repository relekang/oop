package oving6;

public class SavingsAccount implements Account {
	private int balance;
	private int credit;
	public SavingsAccount(){
		balance = 0;
		credit = 0;
	}
	@Override
	public int getBalance() {
		return balance;
	}

	@Override
	public int getCredit() {
		return credit;
	}

	@Override
	public int deposit(int theAmount) {
		if(theAmount > 0) balance += theAmount;
		return balance;
	}

	@Override
	public int withdraw(int theAmount) {
		if(theAmount > 0 && theAmount <= balance) balance -= theAmount;
		else return 0;
		return theAmount;
	}
	@Override
	public int getFees() {
		return 0;
	}

}
