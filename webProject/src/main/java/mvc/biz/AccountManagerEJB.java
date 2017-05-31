package mvc.biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mvc.model.AccountMVC;
import mvc.model.AccountAlreadyExistException;
import mvc.model.AccountDoesNotExistException;
import mvc.model.Amount;

@Stateless
public class AccountManagerEJB {
	
	@PersistenceContext(unitName="AccountPersistenceUnit")
	private EntityManager entityManager;


	public AccountMVC save(String accountName, String accountNumber, Amount amount)
			throws AccountAlreadyExistException {
		
		try {
			getByNumber(accountNumber);
			throw new AccountAlreadyExistException();
		} catch (AccountDoesNotExistException e) {
			AccountMVC account = new AccountMVC(accountName, accountNumber, amount);
			entityManager.persist(account);
			return account;
		}
	}

	public AccountMVC getByNumber(String accountNumber) throws AccountDoesNotExistException{
		try{
		return entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", AccountMVC.class)
				.setParameter("number", accountNumber)
				.getSingleResult();
		}catch(NoResultException e){
			throw new AccountDoesNotExistException();
		}
	}
}
