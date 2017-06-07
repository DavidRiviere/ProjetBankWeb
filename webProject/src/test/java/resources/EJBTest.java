package resources;

import javax.annotation.Resource;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class EJBTest {

	private static EJBContainer container;
	@PersistenceContext(unitName = "bankProjectWeb")
	protected EntityManager em;
	@Resource
	protected UserTransaction tx;

	@BeforeClass
	public static void start() throws NamingException {
	    container = EJBContainer.createEJBContainer();
	}

	@AfterClass
	public static void shutdown() {
	    if (container != null) {
	        container.close();
	    }
	}

	public EJBTest() {
		super();
	}

	@Before
	public void inject() throws Exception {
	    container.getContext().bind("inject", this);
	    tx.begin();
	    truncateSchema();
	    tx.commit();
	}

	private void truncateSchema() {
		String string = "TRUNCATE SCHEMA PUBLIC AND COMMIT NO CHECK";
		em.createNativeQuery(string).executeUpdate();
	}

	@After
	public void reset() throws NamingException {
	    container.getContext().unbind("inject");
	}

}