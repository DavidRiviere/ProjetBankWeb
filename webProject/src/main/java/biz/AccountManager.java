package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.Account;
import mvc.model.AccountDoesNotExistException;

@Stateless
public class AccountManager {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	public Account getByNumber(String accountNumber) throws AccountDoesNotExistException {
		try {

			return entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
					.setParameter("number", accountNumber).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	
}
