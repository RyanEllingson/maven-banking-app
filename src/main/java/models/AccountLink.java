package models;

public class AccountLink {
	private int id;
	private int userId;
	private int accountId;
	
	public AccountLink() {
		super();
	}

	public AccountLink(int id, int userId, int accountId) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "AccountLink [id=" + id + ", userId=" + userId + ", accountId=" + accountId + "]";
	}
}
