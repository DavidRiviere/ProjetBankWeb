package mvc.biz;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mvc.model.Account;
import mvc.model.AccountAlreadyExistException;
import mvc.model.AccountDoesNotExistException;
import mvc.model.Amount;

@Stateless
public class AccountManagerEJB {
	
	@PersistenceContext(unitName="bankProjectWeb")
	private EntityManager entityManager;


	public Account save(String accountName, String accountNumber, Amount amount)
			throws AccountAlreadyExistException {
		
		try {
			getByNumber(accountNumber);
			throw new AccountAlreadyExistException();
		} catch (AccountDoesNotExistException e) {
			Account account = new Account(accountName, accountNumber, amount);
			entityManager.persist(account);
			return account;
		}
	}

	public Account getByNumber(String accountNumber) throws AccountDoesNotExistException{
		try{
		return entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
				.setParameter("number", accountNumber)
				.getSingleResult();
		}catch(NoResultException e){
			throw new AccountDoesNotExistException();
		}
	}
}
