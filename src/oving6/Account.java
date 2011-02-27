package oving6;

public interface Account {
	public int getBalance();
	public int getCredit();
	public int getFees();
	public int deposit(int theAmount);
	public int withdraw(int theAmount);
}
