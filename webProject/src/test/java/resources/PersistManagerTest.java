package resources;



import static org.junit.Assert.*;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.persistence.LockModeType;

import org.junit.Test;

import biz.PersistManager;
import builders.AccountBuilder;
import model.Account;
import model.Address;
import model.Owner;

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
	
	@Test
	public void persistOwner() {
		
		
		String login = "loginTest";
		
		Owner owner = new Owner("wed", "wed", "0512345678", new Date(), login, "test", "lu@lu.fgr", new Address(), false);
		pm.persist(owner);
		//pm.assignRole(login);
	}
	

}
