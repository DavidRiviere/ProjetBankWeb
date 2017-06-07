package resources;



import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.junit.Test;

import biz.PersistManager;

@ManagedBean
public class PersistManagerTest extends EJBTest{
	
	@EJB PersistManager pm;
	

	@Test
	public void test() {
		
	}

}
