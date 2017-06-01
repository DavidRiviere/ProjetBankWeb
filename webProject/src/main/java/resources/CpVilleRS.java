package resources;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
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

import biz.PersistManager;
import model.CpVille;
import mvc.model.AccountDoesNotExistException;

@Path("/rs/cpville")
public class CpVilleRS {

	@PersistenceContext(unitName = "bankProjectWeb")
	private EntityManager entityManager;
	
	@EJB private PersistManager cpVilleManager;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CpVille get(@PathParam("id") long id) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM CpVille a WHERE a.id = :id", CpVille.class)
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CpVille> get() throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT a FROM CpVille a", CpVille.class).getResultList();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(CpVille cpVille, @Context UriInfo uriInfo) {
		
		cpVilleManager.persist(cpVille);
		
		
		URI location = uriInfo.getRequestUriBuilder()
                .path(String.valueOf(cpVille.getId()))
                .build();
		return Response.seeOther(location).build();
		
	}

}
