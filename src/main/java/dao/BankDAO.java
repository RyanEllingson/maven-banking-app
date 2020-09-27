package dao;

import java.util.List;

import models.Account;
import models.AccountLink;
import models.AccountStatus;
import models.AccountType;
import models.Role;
import models.User;

public interface BankDAO {
//	CREATE
	public int insertUser(User user);
	
	public int insertAccount(Account account);
	
	public int insertAccountLink(AccountLink link);
	
//	READ
	public List<Account> getAllAccounts();
	
	public List<Account> getAccountsByUser(int userId);
	
	public List<Account> getAccountsByStatus(int statusId);
	
	public List<Account> getAllSavingsAccounts();
	
	public Account getAccountById(int accountId);
	
	public List<AccountStatus> getAllStatuses();
	
	public List<AccountType> getAllTypes();
	
	public AccountLink getLinkById(int id);
	
	public int getLinkByUserAndAcct(int userId, int accountId);
	
	public List<AccountLink> getLinksByUser(int userId);
	
	public List<AccountLink> getLinksByAccount(int accountId);
	
	public List<Role> getAllRoles();
	
	public List<User> getAllUsers();
	
	public List<User> getUsersByAccount(int accountId);
	
	public User getUserById(int id);
	
	public User getUserByUsername(String username);
	
//	UPDATE
	public void updateUser(User user);
	
	public void updateAccount(Account account);
	
//	DELETE
	public void deleteUserById(int id);
	
	public void deleteAccountById(int id);
	
	public void deleteLinkById(int id);
}
