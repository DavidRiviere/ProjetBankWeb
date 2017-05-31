package resources;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Account;
import mvc.model.AccountDoesNotExistException;

@Path("/accountRS")
public class AccountResource {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Account get(@PathParam("accountNumber") long accountNumber) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class)
					.setParameter("number", accountNumber).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}

}
