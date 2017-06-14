package resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import biz.WrongIdException;
import model.Account;
import model.Credential;
import model.Owner;
import model.Transaction;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/{class:owner}")
public class OwnerRS extends Resource {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	@GET
	@Path("/{id}")
	@Produces( MediaType.APPLICATION_JSON)
	public Owner get(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM Owner a WHERE a.id = :id", Owner.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	
	@GET
	@Path("/{id}/accounts")
	@Produces( MediaType.APPLICATION_JSON)
	public List<Account> getAccounts(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager
					.createQuery("SELECT a FROM Account a JOIN a.owners o WHERE o.id = :id", Account.class)
					.setParameter("id", id).getResultList();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTransaction(Owner owner, @Context UriInfo uriInfo) {
		this.persistManager.persist(owner);
		return super.postLocation(owner.getId(), uriInfo);
	}
	
	
	

}
