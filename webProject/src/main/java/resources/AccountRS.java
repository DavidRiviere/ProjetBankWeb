package resources;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
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

import builders.AccountBuilder;
import model.Account;
import model.Transaction;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/{class:account}")
public class AccountRS extends Resource{

	public boolean accountAlreadyExist(String accountNumber) {
		return (entityManager.createQuery("SELECT Count(a) FROM Account a WHERE a.number = :accountNumber", Long.class)
				.setParameter("accountNumber", accountNumber).getSingleResult() > 0);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postJson(Account account, @Context UriInfo uriInfo) {

		if (accountAlreadyExist(account.getNumber())) {
			return Response.status(404).build();
		}

		this.persistManager.persist(account);

		return postLocation(account.getId(), uriInfo);

	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response post(MultivaluedMap<String, String> formParams, @Context UriInfo uriInfo) {
		
		Account account = new AccountBuilder().setDescription(formParams.getFirst("accountDescription"))
				.setCreationDate(new Date())
				.setInitialBalance(Double.valueOf(formParams.getFirst("accountInitialBalance")))
				.setNumber(formParams.getFirst("accountNumber"))
				.build();

		if (accountAlreadyExist(account.getNumber())) {
			return Response.status(404).build();
		}

		persistManager.persist(account);

		return postLocation(account.getId(), uriInfo);

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteAccount(Account account) {
		persistManager.remove(account);
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
	
	@GET
	@Path("/{id}/balance")
	public double getBalance(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.find(Account.class, id).calculateBalance();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	

}
