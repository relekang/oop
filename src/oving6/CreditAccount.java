package oving6;


public class CreditAccount implements Account {
	private int balance;
	private int credit;
	private int fee;
	private int fees;
	
	public CreditAccount(int credit) {
		balance = 0;
		this.credit = credit;
		fee = 50;
		fees = 0;
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
		if(theAmount <= 0) return 0;
		if(theAmount <= balance){ balance -= theAmount; }
		else if(theAmount > balance && (theAmount + fee) <= (balance + credit)){
			fees += fee;
			balance -= (theAmount + fee);
		} 
		else return 0;
		return theAmount;
	}
	public int getFees() {
		return fees;
	}
	
}
