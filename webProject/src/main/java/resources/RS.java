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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import biz.PersistManager;
import model.Agency;
import model.Identifiable;
import mvc.model.AccountDoesNotExistException;

//@Path("/rs/{class:AccountType|CountryCode|Agency}")
public class RS {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;

	@EJB
	private PersistManager persistManager;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Identifiable get(@PathParam("id") long id, @Context UriInfo uriInfo) throws AccountDoesNotExistException {

		List<PathSegment> pathSegments = uriInfo.getPathSegments();
		PathSegment pathSegment = pathSegments.get(1);
		
		try {
			return entityManager.createQuery("SELECT a FROM "+pathSegment.toString()+" a WHERE a.id = :id", Identifiable.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}
	
	public Identifiable getById(long id ,Class myClass) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM "+myClass.getName()+" a WHERE a.id = :id", Identifiable.class)
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

		//persistManager.remove(get(id));
		return Response.ok().build();

	}

}
