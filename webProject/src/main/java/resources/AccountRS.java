package resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import biz.PersistManager;
import builders.AccountBuilder;
import model.Account;
import model.Transaction;
import mvc.model.AccountDoesNotExistException;

//@Path("/{a:rs/account|index.html}")
public class AccountRS {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	@EJB
	private PersistManager persistManager;

	// @Resource
	// private RS rs;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account get(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM Account a WHERE a.id = :id", Account.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}

	public boolean accountAlreadyExist(String accountNumber) {
		return (entityManager.createQuery("SELECT Count(a) FROM Account a WHERE a.number = :accountNumber", Long.class)
				.setParameter("accountNumber", accountNumber).getSingleResult() > 0);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Account account, @Context UriInfo uriInfo) {

		if (accountAlreadyExist(account.getNumber())) {
			return Response.status(404).build();
		}

		persistManager.persist(account);

		URI location = uriInfo.getRequestUriBuilder().path(String.valueOf(account.getId())).build();
		return Response.seeOther(location).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response post(MultivaluedMap<String, String> formParams, @Context UriInfo uriInfo) {

		/*
		 * Account account = new Account(number, description, initialBalance,
		 * 0.0, 0, 0, (CountryCode) rs.getById(1L, CountryCode.class), new
		 * Date(), (Agency) rs.getById(1, Agency.class), (AccountType)
		 * rs.getById(1, AccountType.class));
		 */
		/*
		Account account = new Account();
		account.setNumber(formParams.getFirst("accountNumber"));
		account.setDescription(formParams.getFirst("accountDescription"));
		account.setInitialBalance(Double.valueOf(formParams.getFirst("accountInitialBalance")));
		account.setCreationDate(new Date());
		*/
		
		Account account = new AccountBuilder().setDescription(formParams.getFirst("accountDescription"))
				.setCreationDate(new Date())
				.setInitialBalance(Double.valueOf(formParams.getFirst("accountInitialBalance")))
				.setNumber(formParams.getFirst("accountNumber"))
				.build();

		if (accountAlreadyExist(account.getNumber())) {
			return Response.status(404).build();
		}

		persistManager.persist(account);

		URI location = uriInfo.getRequestUriBuilder().path(String.valueOf(account.getId())).build();
		return Response.seeOther(location).build();

	}

	@DELETE
	public Response deleteAccount(Account account) {
		persistManager.remove(account);
		return Response.ok().build();

	}

	@DELETE
	public Response deleteAccountFromID(Long id) throws AccountDoesNotExistException {

		persistManager.remove(get(id));
		return Response.ok().build();

	}
	
	@GET
	@Path("/{id}/transactions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Transaction> getTransaction(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT t FROM Transaction t JOIN t.account a WHERE a.id = :id", Transaction.class)
					.setParameter("id", id).getResultList();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	

}
