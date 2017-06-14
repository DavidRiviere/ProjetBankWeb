package resources;



import static org.junit.Assert.*;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.persistence.LockModeType;

import org.junit.Test;

import biz.PersistManager;
import builders.AccountBuilder;
import model.Account;

@ManagedBean
public class PersistManagerTest extends EJBTest{
	
	@EJB PersistManager pm;
	

	@Test
	public void persistAccount() {
		assertEquals(0L, em.createNativeQuery("Select COUNT(*) FROM account").getSingleResult());
		Account account = new AccountBuilder().build();
		pm.persist(account);
		assertEquals(1L, em.createNativeQuery("Select COUNT(*) FROM account").getSingleResult());
	}
	

}
