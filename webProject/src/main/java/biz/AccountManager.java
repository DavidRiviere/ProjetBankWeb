package biz;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.Account;
import model.CpVille;
import mvc.model.AccountDoesNotExistException;



@Stateless
public class AccountManager {
	
	@PersistenceContext(unitName="bankProjectWeb")
	private EntityManager entityManager;


	public Account getByNumber(String accountNumber) throws AccountDoesNotExistException {
		try{
			Integer id = Integer.valueOf(accountNumber);
			CpVille singleResult = entityManager.createQuery("SELECT a FROM CpVille a WHERE a.id = :number", CpVille.class)
			.setParameter("number", id)
			.getSingleResult();
			System.out.println(singleResult);
		return entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
				.setParameter("number", accountNumber)
				.getSingleResult();
		}catch(NoResultException e){
			throw new AccountDoesNotExistException();
		}
	}
}
