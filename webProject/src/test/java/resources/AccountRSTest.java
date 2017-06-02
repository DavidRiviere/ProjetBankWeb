package resources;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mvc.model.AccountDoesNotExistException;

public class AccountRSTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		AccountRS accountRS = new AccountRS();
		
		try {
			accountRS.getTransaction(1);
		} catch (AccountDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
