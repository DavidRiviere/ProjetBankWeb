package resources;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import biz.PersistManager;
import model.AccountType;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/accountType")
public class AccountTypeRS {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	@EJB
	private PersistManager persistManager;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountType get(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM AccountType a WHERE a.id = :id", AccountType.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AccountType> get() throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM AccountType a WHERE a.id = :id", AccountType.class).getResultList();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}



	@DELETE
	public Response deleteAccount(AccountType accountType) {
		persistManager.remove(accountType);
		return Response.ok().build();

	}

	@DELETE
	public Response deleteAccountTypeFromID(Long id) throws AccountDoesNotExistException {

		persistManager.remove(get(id));
		return Response.ok().build();

	}

}
