package resources;

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
import model.Agency;
import mvc.model.AccountDoesNotExistException;

@Path("/agency")
public class AgencyRS {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	@EJB
	private PersistManager persistManager;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Agency get(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM Agency a WHERE a.id = :id", Agency.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}



	@DELETE
	public Response deleteAccount(Agency agency) {
		persistManager.remove(agency);
		return Response.ok().build();

	}

	@DELETE
	public Response deleteAgencyFromID(Long id) throws AccountDoesNotExistException {

		persistManager.remove(get(id));
		return Response.ok().build();

	}

}
