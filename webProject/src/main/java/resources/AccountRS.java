package resources;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.Account;
import model.CpVille;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/account")
public class AccountRS {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

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

	/*@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postForm(@FormParam("name") String description, @Context UriInfo uriInfo)
			throws AccountDoesNotExistException {
		Account account = new Account();
		
		//URI location = uriInfo.getRequestUriBuilder().path(account.getDescription()).build();

		return Response.ok().build();
	}
	*/
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Account account, @Context UriInfo uriInfo) {
		entityManager.persist(account);
		
		URI location = uriInfo.getRequestUriBuilder()
                .path(String.valueOf(account.getId()))
                .build();
		System.out.println(location);
		return Response.seeOther(location).build();
		
	}

}
