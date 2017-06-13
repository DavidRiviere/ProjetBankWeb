package resources;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import biz.WrongIdException;
import model.Credential;
import model.Owner;

@Path("/login")
public class LoginRS {
	
	@PersistenceContext(unitName = "bankProjectWeb")
	EntityManager entityManager;

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
}
