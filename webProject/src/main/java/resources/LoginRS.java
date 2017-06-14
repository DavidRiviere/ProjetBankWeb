package resources;

import java.net.URI;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import biz.PersistManager;
import biz.WrongIdException;
import model.Credential;
import model.Owner;

@Path("/")
public class LoginRS {
	
	@PersistenceContext(unitName = "bankProjectWeb")
	EntityManager entityManager;
	@EJB
	PersistManager persistManager;
	
	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Owner authenticate( Credential cred ) throws WrongIdException{
		
		Owner singleResult;
		try {
			singleResult = entityManager.createQuery("SELECT a FROM Owner a WHERE a.login = :login", Owner.class)
					.setParameter("login", cred.getLogin()).getSingleResult();
		} catch (Exception e) {
			throw new WrongIdException();
		}
		
		if(! cred.getPswd().equals(singleResult.getPswd())){
			throw new WrongIdException();
		}
		return singleResult;
	}
	
	@Path("register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postTransaction(Owner owner, @Context UriInfo uriInfo) {
		this.persistManager.persist(owner);
		return Response.ok().build();
		//return postLocation(owner.getId(), uriInfo);
	}
	
	private Response postLocation(Long id, UriInfo uriInfo) {
		URI location = uriInfo.getRequestUriBuilder()
                .path(String.valueOf(id))
                .build();
		return Response.seeOther(location).build();
	}
}
