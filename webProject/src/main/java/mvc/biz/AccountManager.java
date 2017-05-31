package mvc.biz;

import java.util.ArrayList;
import java.util.List;

import mvc.model.Account;
import mvc.model.AccountAlreadyExistException;
import mvc.model.AccountDoesNotExistException;
import mvc.model.Amount;

@Deprecated
public class AccountManager {
	private static AccountManager instance = new AccountManager();
	List<Account> accounts = new ArrayList<>();

	private AccountManager() {
	}

	public static AccountManager getInstance() {
		return instance;
	}

	public synchronized Account save(String accountName, String accountNumber, Amount amount)
			throws AccountAlreadyExistException {
		for (Account account : accounts) {
			if (account.getNumber().equals(accountNumber)) {
				throw new AccountAlreadyExistException();
			}
		}
		Account account = new Account(accountName, accountNumber, amount);

		accounts.add(account);
		return account;

	}

	public synchronized Account getByNumber(String accountNumber) throws AccountDoesNotExistException {
		for (Account account : accounts) {
			if (account.getNumber().equals(accountNumber)) {
				return account;
			}
		}
		throw new AccountDoesNotExistException();
	}

}
