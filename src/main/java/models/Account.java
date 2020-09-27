package models;

public class Account {
	private int accountId;
	private double balance;
	private AccountStatus status;
	private AccountType type;
	
	public Account() {
		super();
	}
	
	public Account(int accountId, double balance, AccountStatus status, AccountType type) {
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
	
	public int getAccountId() {
		return this.accountId;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public AccountStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	public AccountType getType() {
		return this.type;
	}
	
	public void setType(AccountType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", status=" + status + ", type=" + type
				+ "]";
	}
}
