package biz;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import model.Account;
import model.CpVille;
import mvc.model.AccountDoesNotExistException;

@Stateless
public class AccountManager {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	public Account getByNumber(String accountNumber) throws AccountDoesNotExistException {
		try {
			
			List<CpVille> resultList2 = entityManager
					.createQuery("SELECT a FROM CpVille a", CpVille.class).getResultList();
			for (CpVille testEntity : resultList2) {
				System.out.println(testEntity);
			}
			


			return entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
					.setParameter("number", accountNumber).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
}
