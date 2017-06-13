package resources;

import java.util.List;

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
import javax.ws.rs.core.MediaType;

import biz.WrongIdException;
import model.Credential;
import model.Owner;
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
	
	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> get(@PathParam("class") String pathClass) throws AccountDoesNotExistException {
		return super.get("owner");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Owner authenticate( Credential cred ) throws WrongIdException{
		System.out.println(cred.getLogin());
		Owner singleResult = entityManager.createQuery("SELECT a FROM Owner a WHERE a.login = :login", Owner.class)
				.setParameter("login", cred.getLogin()).getSingleResult();
		
		if(! cred.getPswd().equals(singleResult.getPswd())){
			throw new WrongIdException();
		}
		return singleResult;
	}
	
	

}
